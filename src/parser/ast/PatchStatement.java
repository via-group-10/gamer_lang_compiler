package parser.ast;

import java.util.ArrayList;

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
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          nodeList.add(getStatements());
          nodeList.add(getExpression());
          return nodeList;
     }
}
