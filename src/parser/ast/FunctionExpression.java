package parser.ast;

public class FunctionExpression extends Expression
{
     private Identifier identifier;
     private ExpressionList arguments;

     public FunctionExpression(Identifier identifier, ExpressionList arguments)
     {
          this.identifier = identifier;
          this.arguments = arguments;
     }

     public FunctionExpression(Identifier identifier)
     {
          this.identifier = identifier;
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
