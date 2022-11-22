package ast;

public class Operator extends Terminal
{
     public Operator(String operator)
     {
          super(operator);
     }

     @Override
     public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }

}
