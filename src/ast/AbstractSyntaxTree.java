package ast;

public abstract class AbstractSyntaxTree
{
    public abstract Object visit(AbstractSyntaxTreeVisitor v, Object arg );

}
