package ast;

public interface AbstractSyntaxTreeVisitor
{
    Object visit( Program astNode, Object arg );

    Object visit( Block astNode, Object arg );

    Object visit(BinaryExpression dec, Object arg);

    Object visit(CharacterLiteral dec, Object arg);

    Object visit(CharacterLiteralExpression dec, Object arg);

    Object visit(ChatStatement dec, Object arg);

    Object visit(ExpressionStatement dec, Object arg);

    Object visit(FeedStatement dec, Object arg);

    Object visit(FunctionDeclaration dec, Object arg);

    Object visit(FunctionExpression dec, Object arg);

    Object visit(IntegerLiteral dec, Object arg);

    Object visit(IntegerLiteralExpression dec, Object arg);

    Object visit(Operator dec, Object arg);

    Object visit(OpStatement dec, Object arg);

    Object visit(PatchStatement dec, Object arg);

    Object visit(VariableDeclaration dec, Object arg);

    Object visit(VariableExpression dec, Object arg);













}


