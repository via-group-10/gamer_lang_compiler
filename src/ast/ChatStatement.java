package ast;

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
     public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }
}
