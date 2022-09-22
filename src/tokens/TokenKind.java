package tokens;

public enum TokenKind
{
     IDENTIFIER,
     INTEGERLITERAL,
     CHARACTERLITERAL,
     OPERATOR,

     INVENTORY("inventory"),
     GLHF("glhf"),
     RAGEQUIT("RAGEQUIT"),
     HP("hp"),
     MANA("mana"),
     NPC("npc"),
     OP("op"),
     PVP("pvp"),
     PVE("pve"),
     COOLDOWN("cooldown"),
     PATCH("patch"),
     CHAT("chat"),
     FEED("feed"),

     EOF,

     COMMA( "," ),
     TRIPLEEXCLAMATIONMARK( "!!!" ),
     LEFTPARAN( "(" ),
     RIGHTPARAN( ")" ),


     ERROR;


     private String spelling = null;


     private TokenKind()
     {
     }


     private TokenKind( String spelling )
     {
          this.spelling = spelling;
     }


     public String getSpelling()
     {
          return spelling;
     }
}
