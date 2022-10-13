package parser.ast;

public class CharacterLiteralExpression extends Expression
{
     private CharacterLiteral characterLiteral;

     public CharacterLiteralExpression(CharacterLiteral characterLiteral)
     {

          this.characterLiteral = characterLiteral;
     }

     public CharacterLiteral getCharacterLiteral() {
          return characterLiteral;
     }
}
