package ast;

public class Identifier extends Terminal
{
     private String type;

     public Identifier(String type, String name)
     {
          super(name);
          this.type = type;
     }

     public String getType()
     {
          return type;
     }

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return null;
     }
}
