package ast;

import java.util.ArrayList;

public class Statements extends AbstractSyntaxTree
{
     private ArrayList<Statement> statements;

     public Statements()
     {
          statements = new ArrayList<>();
     }

     public void addStatement(Statement statement)
     {
          statements.add(statement);
     }

     public ArrayList<Statement> getStatements()
     {
          return statements;
     }

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return null;
     }
}
