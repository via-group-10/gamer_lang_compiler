package ast;

public class FunctionDeclaration extends Declaration
{
     private Declarations arguments;
     private Block block;
     private Expression mvpExpression;

     public FunctionDeclaration(Identifier identifier, Type type)
     {
          super(identifier, type);
     }

     public boolean hasArguments()
     {
          return arguments != null;
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

     @Override
     public Object visit(AbstractSyntaxTreeVisitor v, Object arg) {
          return v.visit(this,null);
     }
}
