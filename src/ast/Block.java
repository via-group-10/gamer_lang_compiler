package ast;

import exceptions.UnexpectedAbstractSyntaxTreeException;

public class Block extends AbstractSyntaxTree
{
     private Declarations declarations;
     private Statements statements;


     public Block(Statements statements)
     {
          this.statements = statements;
     }

     public Block(Statements statements, Declarations declarations)
     {
          this.statements = statements;
          this.declarations = declarations;
     }

     public Declarations getDeclarations()
     {
          return declarations;
     }

     public Statements getStatements()
     {
          return statements;
     }

     public boolean hasDeclarations()
     {
          return declarations != null;
     }

     @Override
     public Object visit(AbstractSyntaxTreeVisitor visitor, Object arg) {
          //logika blocku...
          return visitor.visit(this, arg);
     }
}
