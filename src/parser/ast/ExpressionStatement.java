package parser.ast;

import java.util.ArrayList;

public class ExpressionStatement extends Statement
{
     private Expression expression;

     public ExpressionStatement(Expression expression)
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
          return nodeList;
     }
}
