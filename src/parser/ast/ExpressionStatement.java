package parser.ast;

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
}
