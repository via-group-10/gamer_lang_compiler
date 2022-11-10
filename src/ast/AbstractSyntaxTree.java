package ast;

public abstract class AbstractSyntaxTree
{
    public abstract Object accept( AbstractSyntaxTreeVisitor v, Object arg );

}
