package parser.ast;

public class UnaryExpression extends Expression
{
     private Operator operator;
     private Expression expression;

     public UnaryExpression(String operator, Expression expression)
     {
          this.operator = new Operator(operator);
          this.expression = expression;
     }
}
