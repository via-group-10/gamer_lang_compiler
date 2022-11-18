package ast;

public enum Type
{
    HP("hp"),
    MANA("mana");

    private String spelling;

    Type(String spelling) {
        this.spelling = spelling;
    }

    public String getSpelling() {
        return spelling;
    }

    public boolean equals(String other){
        return other.equals(this.spelling);
    }

    public static Type getType(String spelling)
    {
        if (spelling.equals(HP.spelling))
            return HP;
        else if (spelling.equals(MANA.spelling))
            return MANA;
        else
            throw new RuntimeException("Unknown type");
    }
}