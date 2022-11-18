package ast;


public class Program extends AbstractSyntaxTree
{
     private Block block;

     public Program(Block block)
     {
          this.block = block;
     }

     public Block getBlock()
     {
          return block;
     }

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this, arg);
     }
}
