package parser.ast;

import java.util.ArrayList;

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
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          nodeList.add(getCharacterLiteral());
          return  nodeList;
     }
}
