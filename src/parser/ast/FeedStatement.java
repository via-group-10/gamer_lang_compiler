package parser.ast;

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
}
