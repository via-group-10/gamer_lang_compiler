package parser.ast;

public class CharacterLiteralExpression extends Expression
{
     private String character;

     public CharacterLiteralExpression(String character)
     {

          this.character = character;
     }
}
