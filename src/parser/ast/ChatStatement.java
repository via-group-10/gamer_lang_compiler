package parser.ast;

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
}
