package parser.ast;


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
}
