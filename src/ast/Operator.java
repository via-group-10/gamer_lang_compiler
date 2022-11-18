package ast;

public class Operator extends Terminal
{
     private OperatorDeclaration operatorDeclaration;
     public Operator(String operator)
     {
          super(operator);
     }

     public OperatorDeclaration getOperatorDeclaration() {
          return operatorDeclaration;
     }

     public void setOperatorDeclaration(OperatorDeclaration operatorDeclaration) {
          this.operatorDeclaration = operatorDeclaration;
     }

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return null;
     }

     private class OperatorDeclaration {
     }
}
