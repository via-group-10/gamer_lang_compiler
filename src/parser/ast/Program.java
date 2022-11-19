package parser.ast;


import java.util.ArrayList;

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
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          nodeList.add(getBlock());
          return nodeList;
     }
}
