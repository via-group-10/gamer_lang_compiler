package parser.ast;

import java.util.ArrayList;

public class FunctionExpression extends Expression
{
     private Identifier identifier;
     private ExpressionList arguments;

     public FunctionExpression(Identifier identifier, ExpressionList arguments)
     {
          this.identifier = identifier;
          this.arguments = arguments;
     }

     public FunctionExpression(Identifier identifier)
     {
          this.identifier = identifier;
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }

     public ExpressionList getArguments()
     {
          return arguments;
     }

     @Override
     public ArrayList<AbstractSyntaxTree> getNodes(){
          ArrayList<AbstractSyntaxTree> nodeList = new ArrayList<>();
          nodeList.add(getArguments());
          nodeList.add(getIdentifier());
          return nodeList;
     }
}
