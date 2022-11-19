package parser.ast;

import java.util.ArrayList;

public class IntegerLiteralExpression extends Expression
{
     private IntegerLiteral integer;

     public IntegerLiteralExpression(IntegerLiteral integer)
     {

          this.integer = integer;
     }

     public IntegerLiteral getIntegerLiteral()
     {
          return integer;
     }

     @Override
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          nodeList.add(getIntegerLiteral());
          return nodeList;
     }
}
