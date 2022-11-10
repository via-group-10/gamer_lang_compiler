package checker;

import ast.Declaration;

public class IdentifierId {
    public int level;
    public String id;
    public Declaration attr;

    public IdentifierId( int level, String id, Declaration attr )
    {
        this.level = level;
        this.id = id;
        this.attr = attr;
    }


    public String toString()
    {
        return level + "," + id;
    }
}

