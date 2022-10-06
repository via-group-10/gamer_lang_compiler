package parser.ast;

import exceptions.UnexpectedAbstractSyntaxTreeException;

public class Block extends AbstractSyntaxTree
{
     private Declarations declarations;
     private Statements statements;


     public Block(AbstractSyntaxTree statements)
     {
          if (statements instanceof Statements)
               this.statements = (Statements) statements;
          else
               throw new UnexpectedAbstractSyntaxTreeException(statements, Statements.class);
     }

     public Block(AbstractSyntaxTree statements, AbstractSyntaxTree declarations)
     {
          if (statements instanceof Statements)
               this.statements = (Statements) statements;
          else
               throw new UnexpectedAbstractSyntaxTreeException(statements, Statements.class);

          if (declarations instanceof Declarations)
               this.declarations = (Declarations) declarations;
          else
               throw new UnexpectedAbstractSyntaxTreeException(declarations, Declarations.class);
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
}
