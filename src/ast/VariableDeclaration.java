package ast;

public class VariableDeclaration extends Declaration
{
     public VariableDeclaration(String type, String identifier)
     {
          super(type, identifier);
     }

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return null;
     }
}
