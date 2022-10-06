package parser.grammar;

import parser.Parser;
import parser.ast.AbstractSyntaxTree;
import parser.ast.Block;
import parser.ast.Declarations;
import parser.ast.Statements;
import tokens.TokenKind;

public class BlockVisitor implements GrammarVisitor<Block>
{
     @Override
     public Block visit(Parser parser)
     {
          AbstractSyntaxTree declarations = null;
          AbstractSyntaxTree statements = null;

          if (parser.isCurrentTokenOfKind(TokenKind.INVENTORY))
          {
               parser.next();
               declarations = parser.accept(new DeclarationsVisitor());
          }
          parser.accept(TokenKind.GLHF);
          statements = parser.accept(new StatementsVisitor());
          parser.accept(TokenKind.RAGEQUIT);
          if (declarations != null)
               return new Block(statements, declarations);
          else
               return new Block(statements);
     }
}
