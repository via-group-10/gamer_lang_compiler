package ast;

public class OpStatement extends Statement
{
     private Expression comparisonExpression;
     private Statements pvpStatements;
     private Statements pveStatements;

     public OpStatement(Expression comparisonExpression, Statements pvpStatements,
          Statements pveStatements)
     {
          this.comparisonExpression = comparisonExpression;
          this.pvpStatements = pvpStatements;
          this.pveStatements = pveStatements;
     }

     public OpStatement(Expression comparisonExpression, Statements pvpStatements)
     {
          this.comparisonExpression = comparisonExpression;
          this.pvpStatements = pvpStatements;
     }

     public Expression getComparisonExpression()
     {
          return comparisonExpression;
     }

     public Statements getPvpStatements()
     {
          return pvpStatements;
     }

     public Statements getPveStatements()
     {
          return pveStatements;
     }

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }
}
