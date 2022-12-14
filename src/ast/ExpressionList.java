package ast;

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
     public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }
}
