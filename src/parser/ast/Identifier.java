package parser.ast;

public class Identifier extends Terminal
{
     private String type;
     private String identifier;

     public Identifier(String type, String identifier)
     {
          this.type = type;
          this.identifier = identifier;
     }

     public Identifier(String identifier)
     {
          this.identifier = identifier;
     }

     public String getType()
     {
          return type;
     }

     public String getIdentifier()
     {
          return identifier;
     }
}
