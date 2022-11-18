package ast;

public class Identifier extends Terminal
{
     private Declaration declaration;

     public Identifier(String name)
     {
          super(name);
     }

     public void setDeclaration(Declaration declaration) {
          this.declaration = declaration;
     }

     public Declaration getDeclaration() {
          return declaration;
     }

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, null);
     }
}
