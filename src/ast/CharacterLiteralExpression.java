package ast;

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

     @Override
     public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }
}
