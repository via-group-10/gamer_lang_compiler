package ast;

public abstract class Declaration extends AbstractSyntaxTree
{
     private Identifier identifier;

     public Declaration(String type, String identifier)
     {
          this.identifier = new Identifier(type, identifier);
     }

     public String getType()
     {
          return identifier.getType();
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }
}
