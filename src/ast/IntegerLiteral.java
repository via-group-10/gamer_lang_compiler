package ast;

public class IntegerLiteral extends Terminal
{
    public IntegerLiteral(String idTokenSpelling) {
        super(idTokenSpelling);
    }

    @Override
    public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
        return v.visit(this, arg);
    }
}
