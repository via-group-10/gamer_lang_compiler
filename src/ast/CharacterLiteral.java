package ast;

public class CharacterLiteral extends Terminal
{
    public CharacterLiteral(String character)
    {
        super(character);
    }

    @Override
    public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
        return v.visit(this, arg);
    }
}
