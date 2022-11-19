package parser.ast;

import java.util.ArrayList;

public class ChatStatement extends Statement
{
     private Expression expression;

     public ChatStatement(Expression expression)
     {
          this.expression = expression;
     }

     public Expression getExpression()
     {
          return expression;
     }

     @Override
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          nodeList.add(getExpression());
          return  nodeList;
     }
}
