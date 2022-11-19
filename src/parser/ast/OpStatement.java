package parser.ast;

import java.util.ArrayList;

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
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          nodeList.add(getPveStatements());
          nodeList.add(getPvpStatements());
          nodeList.add(getComparisonExpression());
          return nodeList;
     }
}
