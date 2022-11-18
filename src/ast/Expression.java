package ast;

public abstract class Expression extends AbstractSyntaxTree
{
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
