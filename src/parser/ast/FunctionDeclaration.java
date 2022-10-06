package parser.ast;

public class FunctionDeclaration extends Declaration
{
     private Declarations arguments;
     private Block block;
     private Expression mvpExpression;

     public FunctionDeclaration(String type, String identifier)
     {
          super(type, identifier);
     }

     public Declarations getArguments()
     {
          return arguments;
     }

     public void setArguments(Declarations arguments)
     {
          this.arguments = arguments;
     }

     public void setBlock(Block block)
     {
          this.block = block;
     }

     public Block getBlock()
     {
          return block;
     }

     public void setMvpExpression(Expression mvpExpression)
     {
          this.mvpExpression = mvpExpression;
     }

     public Expression getMvpExpression()
     {
          return mvpExpression;
     }
}
