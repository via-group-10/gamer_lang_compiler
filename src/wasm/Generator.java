package wasm;

import ast.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static wasm.Encoding.*;
import static wasm.Encoding.concat;

public class Generator implements AbstractSyntaxTreeVisitor {
    public int[] generate(Program program) {
        return (int[]) program.visit(this, null);
    }

    @Override
    public Object visit(Program program, Object arg) {
        int[] functionBody = (int[]) program.getBlock().visit(this, null);

        int[] codeSection = createSection(
                codeSectionType,
                encodeVector(1, functionBody)
        );

        return concat(
                magicModuleHeader,
                moduleVersion,
                typeSection,
                importSection,
                functionSection,
                exportSection,
                codeSection
        );
    }

    @Override
    public Object visit(Block block, Object arg) {
        int[] statements = (int[]) block.getStatements().visit(this, null);
        int[] declarations = (int[]) block.getDeclarations().visit(this, null);

        int[] functionBody = encodeVector(
                concat(
                        declarations,
                        statements,
                        new int[]{end} // end of function
                )
        );

        return functionBody;
    }

    @Override
    public Object visit(Declarations declarations, Object arg) {
//        System.out.println(declarations.getAllDeclarations());
        for (Declaration declaration: declarations.getAllDeclarations()) {
            String variableName = declaration.getIdentifier().getName();
            // todo add support for mana
            // System.out.println(declaration.getType());
            localSymbolIndex(variableName);
        }
        int[] locals = new int[]{emptyArray};
        if(symbols.size()>0){
            locals = encodeVector(1, encodeLocal(symbols.size(), f32));
        }
        return locals;
    }

    @Override
    public Object visit(Statements statements, Object arg) {
        ArrayList<int[]> allStatements = new ArrayList<>();

        for (Statement statement: statements.getStatements()){
//            System.out.println("STATEMENT    " + statement);
            int[] oneStatement = (int[]) statement.visit(this, null);
            if(oneStatement != null)
                allStatements.add(oneStatement);
        }
        return toIntArray(allStatements);
    }

    @Override
    public Object visit(UnaryExpression unaryExpression, Object arg) {
        return null;
    }

    @Override
    public Object visit(BinaryExpression binaryExpression, Object arg) {
        // variable assignment
//        System.out.println(binaryExpression.getOperator());
        if(binaryExpression.getOperator().getName().equals("=")){
            int[] right = (int[]) binaryExpression.getExpressionRight().visit(this, null);
            VariableExpression variableExpression = (VariableExpression) binaryExpression.getExpressionLeft();
            String variableName = variableExpression.getIdentifier().getName();
//            System.out.println("variable name: " + variableName);
            return concat(
                    right,
                    new int[]{set_local},
                    unsignedLeb128(localSymbolIndex(variableName))
            );
        }

        // normal expression
        else {
            int[] left = (int[]) binaryExpression.getExpressionLeft().visit(this, null);
            int[] right = (int[]) binaryExpression.getExpressionRight().visit(this, null);
            String sign = binaryExpression.getOperator().getName();
            return concat(
                    left,
                    right,
                    signToOperator(sign)
            );
        }
    }

    @Override
    public Object visit(CharacterLiteral characterLiteral, Object arg) {
        return null;
    }

    @Override
    public Object visit(CharacterLiteralExpression characterLiteralExpression, Object arg) {
        return null;
    }

    @Override
    public Object visit(ChatStatement chatStatement, Object arg) {

        // todo only print integer chat statements
        if(chatStatement.getExpression().getType() == Type.HP){
//            System.out.println(chatStatement.getExpression());
            int[] integerLiteralExpression = (int[]) chatStatement.getExpression().visit(this, null);
            return concat(
                    integerLiteralExpression,
                    new int[]{call},
                    unsignedLeb128(0)
            );
        }
        return null;
    }

    @Override
    public Object visit(ExpressionStatement expressionStatement, Object arg) {
        //todo expression statement doesn't really add any information
        return expressionStatement.getExpression().visit(this, null);
    }

    @Override
    public Object visit(FeedStatement feedStatement, Object arg) {
        return null;
    }

    @Override
    public Object visit(FunctionDeclaration functionDeclaration, Object arg) {
        return null;
    }

    @Override
    public Object visit(FunctionExpression functionExpression, Object arg) {
        return null;
    }

    @Override
    public Object visit(IntegerLiteral integerLiteral, Object arg) {
        return Float.valueOf(integerLiteral.getName()).floatValue();
    }

    @Override
    public Object visit(IntegerLiteralExpression integerLiteralExpression, Object arg) {
        float value = (float) integerLiteralExpression.getIntegerLiteral().visit(this, null);
//        System.out.println(value);
        return concat(
                new int[]{f32_const},
                encodeFloat(value)
        );
    }

    @Override
    public Object visit(Operator operator, Object arg) {
        return null;
    }

    @Override
    public Object visit(OpStatement opStatement, Object arg) {
        return null;
    }

    @Override
    public Object visit(PatchStatement patchStatement, Object arg) {
        return null;
    }

    @Override
    public Object visit(VariableDeclaration variableDeclaration, Object arg) {
        // no need to visit, identifier and type already in Declarations
        return null;
    }

    @Override
    public Object visit(VariableExpression variableExpression, Object arg) {
//        System.out.println();
        // todo only works with locals
        // todo type is float only
        return concat(
                new int[]{get_local},
                unsignedLeb128(localSymbolIndex(variableExpression.getIdentifier().getName()))
        );
    }

    @Override
    public Object visit(Identifier identifier, Object arg) {
        return null;
    }

    @Override
    public Object visit(ExpressionList expressionList, Object arg) {
        return null;
    }

    // Sections
    // https://webassembly.github.io/spec/core/binary/modules.html#sections
    static int customSectionType = 0;
    int typeSectionType = 1;
    int importSectionType = 2;
    int functionSectionType = 3;
    int tableSectionType = 4;
    int memorySectionType = 5;
    int globalSectionType = 6;
    int exportSectionType = 7;
    int startSectionType = 8;
    int elementSectionType = 9;
    int codeSectionType = 10;
    int dataSectionType = 11;

    // Value Types
    // https://webassembly.github.io/spec/core/binary/types.html
    int i32 = 0x7f;
    int f32 = 0x7d;

    // Op Codes
    // https://webassembly.github.io/spec/core/binary/instructions.html
    int end = 0x0b;
    int call = 0x10;
    int get_local = 0x20;
    int set_local = 0x21;
    int f32_const = 0x43;
    int f32_eq = 0x5b;
    int f32_lt = 0x5d;
    int f32_gt = 0x5e;
    int i32_and = 0x71;
    int f32_add = 0x92;
    int f32_sub = 0x93;
    int f32_mul = 0x94;
    int f32_div = 0x95;

    // Export Types
    // http://webassembly.github.io/spec/core/binary/modules.html#export-section
    int ExportTypeFunction = 0x00;
    int ExportTypeTable = 0x01;
    int ExportTypeMemory = 0x02;
    int ExportTypeGlobal = 0x03;

    // Function Type
    // http://webassembly.github.io/spec/core/binary/types.html#function-types
    int functionType = 0x60;

    // Empty Array
    int emptyArray = 0x00;

    // Magic Header
    // https://webassembly.github.io/spec/core/binary/modules.html#binary-module
    int[] magicModuleHeader = {0x00, 0x61, 0x73, 0x6d};
    int[] moduleVersion = {0x01, 0x00, 0x00, 0x00};

    // https://webassembly.github.io/spec/core/binary/conventions.html#binary-vec
    // Vectors are encoded with their length followed by their element sequence
    private int[] encodeVector(int[]... data) {
        int[] flattenData = flatten(data);
        return concat(
                unsignedLeb128(flattenData.length),
                flattenData
        );
    }

    private int[] encodeVector(int size, int[]... data) {
        return concat(
                unsignedLeb128(size),
                flatten(data)
        );
    }

    // https://webassembly.github.io/spec/core/binary/modules.html#sections
    // sections are encoded by their type followed by their vector contents
    private int[] createSection(int sectionType, int[] data) {
        return concat(
                new int[]{sectionType},
                encodeVector(data));
    }

    // Function types are vectors of parameters and return types.
    // Currently, WebAssembly only supports single return values
    int[] voidVoidType = new int[]{
            functionType,
            emptyArray,
            emptyArray
    };

    int[] floatVoidType = concat(
            new int[]{functionType},
            encodeVector(new int[]{f32}),
            new int[]{emptyArray}
    );

    // the type section is a vector of function types
    int[] typeSection = createSection(
            typeSectionType,
            encodeVector(2, concat(voidVoidType, floatVoidType))
    );


    // the function section is a vector of type indices that indicate the type of each function
    // in the code section
    int[] functionSection = createSection(
            functionSectionType,
            encodeVector(new int[]{0x00}) //type index
    );

    // the import section is a vector of imported functions
    int[] printFunctionImport = concat(
            encodeString("env"),
            encodeString("print"),
            new int[]{ExportTypeFunction},
            new int[]{0x01} // type index
    );

    int[] importSection = createSection(
            importSectionType,
            encodeVector(1, printFunctionImport)
    );

    int[] exportSection = createSection(
            exportSectionType,
            encodeVector(1,
                    concat(
                            encodeString("run"),
                            new int[]{ExportTypeFunction},
                            new int[]{0x01} // function index
                    )
            )
    );

    private int[] signToOperator(String sign){
        String[] signs = {"+", "-", "*", "/", "==", ">", "<", "&&"};
        int[] operators = {f32_add, f32_sub, f32_mul, f32_div, f32_eq, f32_gt, f32_lt, i32_and};
        for (int i = 0; i < signs.length; i++) {
            if (signs[i].equals(sign))
                return new int[] {operators[i]};
        }
        throw new RuntimeException("operator not found");
    }

    private int[] encodeLocal(int count, int type){
        return concat(
                unsignedLeb128(count),
                new int[]{type}
        );
    }
    private Map<String, Integer> symbols = new HashMap<String, Integer>();
    private int localSymbolIndex(String name){
        if(symbols.get(name) == null){
            symbols.put(name, symbols.size());
        }
        return symbols.get(name);
    }
}
