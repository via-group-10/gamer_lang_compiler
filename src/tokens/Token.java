package tokens;


public class Token {
    public TokenKind kind;
    public String spelling;


    public Token(TokenKind kind, String spelling) {
        this.kind = kind;
        this.spelling = spelling;

        if (kind == TokenKind.IDENTIFIER)
            for (TokenKind tk : KEYWORDS)
                if (spelling.equals(tk.getSpelling())) {
                    this.kind = tk;
                    break;
                }
    }


    public boolean isAssignOperator() {
        if (kind == TokenKind.OPERATOR)
            return containsOperator(spelling, ASSIGNOPS);
        else
            return false;
    }

    public boolean isAddOperator() {
        if (kind == TokenKind.OPERATOR)
            return containsOperator(spelling, ADDOPS);
        else
            return false;
    }

    public boolean isMulOperator() {
        if (kind == TokenKind.OPERATOR)
            return containsOperator(spelling, MULOPS);
        else
            return false;
    }

    public boolean isExpOperator()
    {
        if (kind == TokenKind.OPERATOR)
            return containsOperator(spelling, EXPOPS);
        else
            return false;
    }

    public boolean isComOperator() {
        if (kind == TokenKind.OPERATOR)
            return containsOperator(spelling, COMPOPS);
        else
            return false;
    }


    private boolean containsOperator(String spelling, String[] OPS) {
        for (String op : OPS)
            if (spelling.equals(op))
                return true;

        return false;
    }

    private static final TokenKind[] KEYWORDS = {TokenKind.INVENTORY, TokenKind.GLHF, TokenKind.RAGEQUIT, TokenKind.HP, TokenKind.MANA, TokenKind.NPC, TokenKind.OP, TokenKind.PVP, TokenKind.PVE, TokenKind.COOLDOWN, TokenKind.PATCH, TokenKind.CHAT, TokenKind.FEED, TokenKind.MVP, TokenKind.BUFF};


    private static final String ASSIGNOPS[] =
            {
                    "=",
            };


    private static final String ADDOPS[] =
            {
                    "+",
                    "-",
            };


    private static final String MULOPS[] =
            {
                    "*",
                    "/",
                    "%"
            };

    private static final String EXPOPS[] =
            {
                    "^"
            };

    private static final String COMPOPS[] =
            {
                    "==",
                    "<",
                    ">",
                    "<>"
            };
}
