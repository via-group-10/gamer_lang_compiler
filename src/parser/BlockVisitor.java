package parser;

import tokens.TokenKind;

public class BlockVisitor implements GrammarVisitor
{
     @Override
     public void visit(Parser parser)
     {
          if (parser.isCurrentTokenOfKind(TokenKind.INVENTORY))
          {
               parser.next();
               parser.accept(new DeclarationsVisitor());
          }
          parser.accept(TokenKind.GLHF);
          parser.accept(new StatementsVisitor());
          parser.accept(TokenKind.RAGEQUIT);
     }
}
