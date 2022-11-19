package parser.ast;

import java.util.ArrayList;

public class UnaryExpression extends Expression
{
     private Operator operator;
     private Expression expression;

     public UnaryExpression(Operator operator, Expression expression)
     {
          this.operator = operator;
          this.expression = expression;
     }

     public UnaryExpression(String operator, Expression expression)
     {
          this.operator = new Operator(operator);
          this.expression = expression;
     }

     public Expression getExpression() {
          return expression;
     }

     public Operator getOperator() {
          return operator;
     }

     @Override
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          nodeList.add(getExpression());
          nodeList.add(getOperator());
          return nodeList;
     }
}
