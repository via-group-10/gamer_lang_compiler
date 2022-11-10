package ast;

public class FunctionExpression extends Expression
{
     private Identifier identifier;
     private ExpressionList arguments;

     public FunctionDeclaration decl;


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

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return null;
     }
}
