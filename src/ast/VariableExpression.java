package ast;

public class VariableExpression extends Expression
{
     private Identifier identifier;
     private VariableDeclaration variableDeclaration;

     public VariableExpression(Identifier identifier)
     {
          this.identifier = identifier;
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }

     public VariableDeclaration getVariableDeclaration() {
          return variableDeclaration;
     }

     public void setVariableDeclaration(VariableDeclaration variableDeclaration) {
          this.variableDeclaration = variableDeclaration;
          this.identifier.setDeclaration(variableDeclaration);
     }

     @Override
     public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }
}
