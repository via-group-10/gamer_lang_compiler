package parser;

import tokens.TokenKind;

public class ArgList implements GrammarVisitor
{
     @Override
     public void visit(Parser parser)
     {
          parser.accept(TokenKind.HP, TokenKind.MANA);
          parser.accept(TokenKind.IDENTIFIER);
          while (parser.isCurrentTokenOfKind(TokenKind.COMMA))
          {
               parser.next();
               parser.accept(TokenKind.HP, TokenKind.MANA);
               parser.accept(TokenKind.IDENTIFIER);
          }
     }
}
