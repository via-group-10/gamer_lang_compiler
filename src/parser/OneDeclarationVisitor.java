package parser;

import tokens.TokenKind;

public class OneDeclarationVisitor implements GrammarVisitor
{
     @Override
     public void visit(Parser parser)
     {
          parser.accept(TokenKind.HP, TokenKind.MANA, TokenKind.NPC);
          parser.accept(TokenKind.IDENTIFIER);
          if (parser.isCurrentTokenOfKind(TokenKind.LEFTPAREN)) {
               parser.next();
               if (parser.isCurrentTokenOfKind(TokenKind.HP, TokenKind.MANA))
                    parser.accept(new ArgList());
               parser.accept(TokenKind.RIGHTPAREN);
               parser.accept(new BlockVisitor());
               parser.accept(TokenKind.MVP);
               parser.accept(new ExpressionVisitor());
          }
          parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
     }
}
