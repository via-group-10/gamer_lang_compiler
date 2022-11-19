package parser.ast;

import java.util.ArrayList;

public abstract class AbstractSyntaxTree
{
    public abstract ArrayList<AbstractSyntaxTree> getNodes();
}
