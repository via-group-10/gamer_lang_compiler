package ast;

import java.util.ArrayList;

public class Declarations extends AbstractSyntaxTree
{
     private ArrayList<Declaration> allDeclarations;

     public Declarations()
     {
          this.allDeclarations = new ArrayList<>();
     }

     public ArrayList<Declaration> getAllDeclarations()
     {
          return allDeclarations;
     }

     public void addDeclaration(Declaration declaration)
     {
          allDeclarations.add(declaration);
     }

     @Override
     public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
          return null;
     }
}
