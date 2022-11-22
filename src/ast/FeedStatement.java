package ast;

public class FeedStatement extends Statement
{
     private Identifier identifier;
     public FeedStatement(Identifier identifier)
     {
          this.identifier = identifier;
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }

     @Override
     public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }
}
