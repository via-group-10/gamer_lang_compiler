package parser.ast;

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
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          for (Statement statement: getStatements()) {
               nodeList.add((AbstractSyntaxTree) statement);
          }
          return nodeList;
     }
}
