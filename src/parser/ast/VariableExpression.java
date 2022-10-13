package parser.ast;

public class VariableExpression extends Expression
{
     private Identifier identifier;

     public VariableExpression(Identifier identifier)
     {
          this.identifier = identifier;
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }
}
