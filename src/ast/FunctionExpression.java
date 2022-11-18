package ast;

public class FunctionExpression extends Expression
{
     private Identifier identifier;
     private ExpressionList arguments;
     private FunctionDeclaration functionDeclaration;


     public FunctionExpression(Identifier identifier, ExpressionList arguments)
     {
          this.identifier = identifier;
          this.arguments = arguments;
     }

     public FunctionExpression(Identifier identifier)
     {
          this.identifier = identifier;
     }

     public FunctionDeclaration getFunctionDeclaration() {
          return functionDeclaration;
     }

     public void setFunctionDeclaration(FunctionDeclaration functionDeclaration) {
          this.functionDeclaration = functionDeclaration;
          this.identifier.setDeclaration(functionDeclaration);
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
          return v.visit(this, arg);
     }
}
