package ast;

public class VariableDeclaration extends Declaration
{
     public VariableDeclaration(Identifier identifier, Type type)
     {
          super(identifier, type);
     }

     @Override
     public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }
}
