package wasm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static wasm.Encoding.*;

public class Emitter {

    // Sections
    // https://webassembly.github.io/spec/core/binary/modules.html#sections
    int customSectionType = 0;
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
    int f32_const = 0x43;
    int f32_add = 0x80;

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
    private int[] encodeVector(int[]... data){
        int[] flattenData = flatten(data);
        return concat(
                unsignedLeb128(flattenData.length),
                flattenData
        );
    }

    private int[] encodeVector(int size, int[]... data){
        return concat(
                unsignedLeb128(size),
                flatten(data)
        );
    }

    // https://webassembly.github.io/spec/core/binary/modules.html#sections
    // sections are encoded by their type followed by their vector contents
    private int[] createSection(int sectionType, int[] data){
        return concat(
                new int[]{sectionType},
                encodeVector(data));
    }

    // START OF TEST CODE
    ArrayList<Integer> buffer = new ArrayList<>();

    private void emitExpression(){
        buffer.add(f32_const);
        buffer.addAll(toIntegerList(encodeFloat(-3333.765f)));
    }
    private int[] codeFromAst(){
        emitExpression();
        buffer.add(call);
        buffer.addAll(toIntegerList(unsignedLeb128(0)));
        return buffer.stream().mapToInt(Integer::intValue).toArray();
    }
    // END OF TEST CODE

    public int[] emit() {
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

        int[] functionBody = encodeVector(
                concat(
                        new int[]{emptyArray},
                        codeFromAst(),
                        new int[]{end}
                )
        );

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
}