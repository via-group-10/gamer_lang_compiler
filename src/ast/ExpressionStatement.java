package ast;

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
     public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }
}
