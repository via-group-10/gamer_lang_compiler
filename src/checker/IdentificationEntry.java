package checker;

import ast.Declaration;

public class IdentificationEntry {
    public int level;
    public String id;
    public Declaration attr;

    public IdentificationEntry(int level, String id, Declaration attr )
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

