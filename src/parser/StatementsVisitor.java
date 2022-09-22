package parser;

import tokens.TokenKind;

public class StatementsVisitor implements GrammarVisitor
{
     @Override
     public void visit(Parser parser)
     {
          while (parser.isCurrentTokenOfKind(TokenKind.BUFF, TokenKind.IDENTIFIER,
               TokenKind.INTEGERLITERAL, TokenKind.CHARACTERLITERAL, TokenKind.LEFTPAREN,
               TokenKind.OP, TokenKind.PATCH, TokenKind.CHAT, TokenKind.FEED))
          {
               parser.accept(new OneStatementVisitor());
          }
     }
}
