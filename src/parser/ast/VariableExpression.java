package parser.ast;

public class VariableExpression extends Expression
{
     private Identifier identifier;

     public VariableExpression(String identifier)
     {
          this.identifier = new Identifier(null, identifier);
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }
}
