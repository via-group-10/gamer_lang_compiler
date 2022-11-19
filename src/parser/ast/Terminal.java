package parser.ast;

import java.util.ArrayList;

public abstract class Terminal extends AbstractSyntaxTree
{
    private String name;

    protected Terminal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public ArrayList<AbstractSyntaxTree> getNodes(){
        ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
        return null;
    }
}
