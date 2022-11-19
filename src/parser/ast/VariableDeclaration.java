package parser.ast;

import java.util.ArrayList;

public class VariableDeclaration extends Declaration
{
     public VariableDeclaration(String type, String identifier)
     {
          super(type, identifier);
     }

     @Override
     public ArrayList<AbstractSyntaxTree> getNodes() {
          return null;
     }
}
