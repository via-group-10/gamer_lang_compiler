package ast;

public interface AbstractSyntaxTreeVisitor
{
    Object visit( Program program, Object arg );

    Object visit( Block block, Object arg );

    Object visit( Declarations declarations, Object arg );

    Object visit( Statements statements, Object arg);
    Object visit(UnaryExpression unaryExpression, Object arg);

    Object visit(BinaryExpression binaryExpression, Object arg);

    Object visit(CharacterLiteral characterLiteral, Object arg);

    Object visit(CharacterLiteralExpression characterLiteralExpression, Object arg);

    Object visit(ChatStatement chatStatement, Object arg);

    Object visit(ExpressionStatement expressionStatement, Object arg);

    Object visit(FeedStatement feedStatement, Object arg);

    Object visit(FunctionDeclaration functionDeclaration, Object arg);

    Object visit(FunctionExpression functionExpression, Object arg);

    Object visit(IntegerLiteral integerLiteral, Object arg);

    Object visit(IntegerLiteralExpression integerLiteralExpression, Object arg);

    Object visit(Operator operator, Object arg);

    Object visit(OpStatement opStatement, Object arg);

    Object visit(PatchStatement patchStatement, Object arg);

    Object visit(VariableDeclaration variableDeclaration, Object arg);

    Object visit(VariableExpression variableExpression, Object arg);

    Object visit(Identifier identifier, Object arg);

    Object visit(ExpressionList expressionList, Object arg);
}


