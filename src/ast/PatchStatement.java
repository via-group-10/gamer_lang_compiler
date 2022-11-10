package ast;

public class PatchStatement extends Statement
{
     private Expression expression;
     private Statements statements;

     public PatchStatement(Expression expression, Statements statements)
     {
          this.expression = expression;
          this.statements = statements;
     }

     public Expression getExpression()
     {
          return expression;
     }

     public Statements getStatements()
     {
          return statements;
     }

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return null;
     }
}