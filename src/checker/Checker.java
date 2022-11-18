package checker;

import ast.*;
import exceptions.ContextualException;
import tokens.TokenKind;

import java.util.Vector;
import java.util.function.Function;

public class Checker implements AbstractSyntaxTreeVisitor {
    private IdentificationTable identificationTable = new IdentificationTable();

    public void check(Program p) {
        p.accept(this, null);
    }

    @Override
    public Object visit(Program program, Object arg) {
        identificationTable.openScope();
        program.getBlock().accept(this, arg);
        identificationTable.closeScope();
        return null;
    }

    @Override
    public Object visit(Block block, Object arg) {
        if (block.hasDeclarations())
            block.getDeclarations().accept(this, null);
        block.getStatements().accept(this, null);
        return null;
    }

    @Override
    public Object visit(Declarations declarations, Object arg) {
        for (Declaration declaration : declarations.getAllDeclarations())
            declaration.accept(this, null);

        return null;
    }

    @Override
    public Object visit(VariableDeclaration variableDeclaration, Object arg) {
        String identifierName = (String) variableDeclaration.getIdentifier().accept(this, null);
        identificationTable.enter(identifierName, variableDeclaration);
        return null;
    }

    @Override
    public Object visit(FunctionDeclaration functionDeclaration, Object arg) {
        String id = (String) functionDeclaration.getIdentifier().accept(this, null);

        identificationTable.enter(id, functionDeclaration);
        identificationTable.openScope();

        functionDeclaration.getArguments().accept(this, null);
        functionDeclaration.getBlock().accept(this, null);
        Type mvpType = (Type) functionDeclaration.getMvpExpression().accept(this, null);

        identificationTable.closeScope();

        if (functionDeclaration.getType() != mvpType)
            throw new ContextualException("Type mismatch in function declaration. Expected: " + functionDeclaration.getType().getSpelling() + ". Found: " + mvpType.getSpelling());

        return null;
    }

    @Override
    public Object visit(Statements statements, Object arg) {
        for (Statement statement : statements.getStatements())
            statement.accept(this, null);
        return null;
    }
    @Override
    public Object visit(ChatStatement chatStatement, Object arg) {
        chatStatement.getExpression().accept(this, null);
        return null;
    }

    @Override
    //tiez neviem ci dobre
    public Object visit(FeedStatement feedStatement, Object arg) {
        String identifierName = (String) feedStatement.getIdentifier().accept(this, null);
        Declaration declaration = identificationTable.retrieve(identifierName);
        feedStatement.getIdentifier().setDeclaration(declaration);
        return null;
    }

    @Override
    public Object visit(OpStatement opStatement, Object arg) {
        opStatement.getComparisonExpression().accept(this, null);
        opStatement.getPvpStatements().accept(this, null);
        opStatement.getPveStatements().accept(this, null);
        return null;
    }

    @Override
    public Object visit(PatchStatement patchStatement, Object arg) {
        patchStatement.getExpression().accept(this, null);
        patchStatement.getStatements().accept(this, null);

        return null;
    }

    @Override
    public Object visit(ExpressionStatement expressionStatement, Object arg) {
        expressionStatement.getExpression().accept(this, null);
        return null;
    }

    @Override
    public Object visit(ExpressionList expressionList, Object arg) {
        Vector<Type> types = new Vector<>();
        for (Expression expr : expressionList.getAllExpressions()) {
            types.add((Type)expr.accept(this, null));
        }
        return types;
    }

    @Override
    public Object visit(FunctionExpression functionExpression, Object arg) {
        String identifierName = (String) functionExpression.getIdentifier().accept(this, null);
        Vector<Type> t = (Vector<Type>) functionExpression.getArguments().accept(this, null);

        Declaration d = identificationTable.retrieve(identifierName);
        if (d == null) {
            throw new ContextualException(identifierName + " is not declared");
        }
        else if (d instanceof FunctionDeclaration f) {
            functionExpression.setFunctionDeclaration(f);

            if (f.getArguments().getAllDeclarations().size() != t.size())
                System.out.println("Incorrect number of arguments in call to " + identifierName);

            // Check input expression list types match declared types
            for (int i = 0; i < t.size(); i++) {
                Declaration dec = f.getArguments().getAllDeclarations().get(i);
                if (dec.getType() == t.get(i))
                    throw new ContextualException("Function parameter: " + dec.getIdentifier().getName() + ", does not match type: " + t.get(i).getSpelling());
            }
        }
        else {
            throw new ContextualException(identifierName + " is not a function");
        }

        return functionExpression.getFunctionDeclaration().getType();
    }

    @Override
    public Object visit(VariableExpression variableExpression, Object arg) {
        String identifierName = (String) variableExpression.getIdentifier().accept(this, null);
        Declaration  declaration = identificationTable.retrieve(identifierName);
        if( declaration == null ) {
            throw new ContextualException(identifierName + " is not declared");
        }
        else if (declaration instanceof VariableDeclaration variableDeclaration) {
            variableExpression.setVariableDeclaration(variableDeclaration);
        }
        else {
            throw new ContextualException(identifierName + " is not a function");
        }
        variableExpression.setType(declaration.getType());
        return declaration.getType();
    }

    @Override
    public Object visit(UnaryExpression unaryExpression, Object arg) {
        Type t = (Type) unaryExpression.getExpression().accept(this, null);
        String operator = (String) unaryExpression.getOperator().accept(this, null);

        if (operator.equals("buff") && t == Type.HP) {
            unaryExpression.setType(t);
            return unaryExpression.getType();
        }
        else {
            throw new ContextualException("Operator: " + operator + " is not a valid unary operator");
        }
    }

    //TODO Chyba logika, ale to uz nebola predtym. -Paco 2k22
    @Override
    public Object visit(BinaryExpression binaryExpression, Object arg) {
        Type t1 = (Type) binaryExpression.getExpressionLeft().accept(this, null);
        Type t2 = (Type) binaryExpression.getExpressionRight().accept(this, null);

        String operator = (String) binaryExpression.getOperator().accept(this, null);

        if (!t1.equals(t2)) {
            throw new ContextualException("Left side expression type(" + t1.getSpelling() + ") does not match type on the right(" + t2.getSpelling() + ").");
        }
        if (operator.equals("=")) {
            if (!(binaryExpression.getExpressionLeft() instanceof VariableExpression))
                throw new ContextualException("Left-hand side of = must be a variable");
        }
        else if (operator.equals("==") || operator.equals("<>") || operator.equals(">") || operator.equals("<")) {
            // rules to comply to when this case happens
            // no rules in our language
        }
        else {
            // for all other cases we can only apply HP types
            if (t1 != Type.HP)
                throw new ContextualException("Operator " + operator + " is only usable with type HP");
        }
        binaryExpression.setType(t1);
        return binaryExpression.getType();
    }

    @Override
    //neznam ci uplne dobre
    public Object visit(CharacterLiteralExpression characterLiteralExpression, Object arg) {
        characterLiteralExpression.setType(Type.MANA);
        return characterLiteralExpression.getType();
    }

    @Override
    public Object visit(IntegerLiteralExpression integerLiteralExpression, Object arg) {
        integerLiteralExpression.setType(Type.HP);
        return integerLiteralExpression.getType();
    }

    @Override
    //toto neznam co je
    public Object visit(CharacterLiteral characterLiteral, Object arg) {
        return Type.MANA;
    }

    @Override
    // nema byt nic
    public Object visit(IntegerLiteral integerLiteral, Object arg) {
        return Type.HP;
    }

    @Override
    public Object visit(Operator operator, Object arg) {
        return operator.getName();
    }

    @Override
    public Object visit(Identifier identifier, Object arg) {
        return identifier.getName();
    }
}
