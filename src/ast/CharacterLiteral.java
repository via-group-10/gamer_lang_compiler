package ast;

public class CharacterLiteral extends Terminal
{
    public CharacterLiteral(String character)
    {
        super(character);
    }

    @Override
    public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
        return v.visit(this, arg);
    }
}
