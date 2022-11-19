package parser.ast;

import java.util.ArrayList;

public class VariableExpression extends Expression
{
     private Identifier identifier;

     public VariableExpression(Identifier identifier)
     {
          this.identifier = identifier;
     }

     public Identifier getIdentifier()
     {
          return identifier;
     }


     @Override
     public ArrayList<AbstractSyntaxTree> getNodes() {
          return null;
     }
}
