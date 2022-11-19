package parser.ast;

import java.util.ArrayList;

public class ExpressionList extends AbstractSyntaxTree
{
     private ArrayList<Expression> allExpressions;

     public ExpressionList()
     {
          allExpressions = new ArrayList<>();
     }

     public void addExpression(Expression expression)
     {
          this.allExpressions.add(expression);
     }

     public ArrayList<Expression> getAllExpressions()
     {
          return allExpressions;
     }

     @Override
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          for (Expression expression: getAllExpressions()) {
               nodeList.add((AbstractSyntaxTree) expression);
          }
          return nodeList;
     }
}
