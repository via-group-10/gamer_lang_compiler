package ast;

public abstract class Terminal extends AbstractSyntaxTree
{
    private String name;

    protected Terminal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
