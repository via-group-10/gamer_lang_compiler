package parser.ast;

public class FunctionExpression extends Expression
{
     private Identifier identifier;
     private ExpressionList arguments;

     public FunctionExpression(String identifier, ExpressionList arguments)
     {
          this.identifier = new Identifier(null, identifier);
          this.arguments = arguments;
     }

     public FunctionExpression(String identifier)
     {
          this.identifier = new Identifier(null, identifier);
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }

     public ExpressionList getArguments()
     {
          return arguments;
     }
}
