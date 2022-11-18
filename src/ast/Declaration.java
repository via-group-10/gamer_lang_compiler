package ast;

public abstract class Declaration extends AbstractSyntaxTree
{
     private Identifier identifier;
     private Type type;

     public Declaration(Identifier identifier, Type type)
     {
          this.identifier = identifier;
          this.type = type;
     }

     public Type getType() {
          return type;
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }
}
