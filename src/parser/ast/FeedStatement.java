package parser.ast;

import java.util.ArrayList;

public class FeedStatement extends Statement
{
     private Identifier identifier;
     public FeedStatement(String identifier)
     {
          this.identifier = new Identifier(null, identifier);
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }

     @Override
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          nodeList.add(getIdentifier());
          return nodeList;
     }
}
