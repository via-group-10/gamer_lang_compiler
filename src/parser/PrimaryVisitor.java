package parser;

import tokens.TokenKind;

public class PrimaryVisitor implements GrammarVisitor
{
     @Override
     public void visit(Parser parser)
     {
          switch (parser.getCurrentToken().kind) {
               case IDENTIFIER -> {
                    parser.next();
                    if (parser.isCurrentTokenOfKind(TokenKind.LEFTPAREN)) {
                         parser.next();
                         if (parser.isCurrentTokenOfKind(TokenKind.IDENTIFIER,
                              TokenKind.INTEGERLITERAL, TokenKind.CHARACTERLITERAL,
                              TokenKind.LEFTPAREN, TokenKind.BUFF)) {
                              parser.accept(new ExpressionListVisitor());
                         }
                         parser.accept(TokenKind.RIGHTPAREN);
                    }
               }
               case INTEGERLITERAL, CHARACTERLITERAL -> parser.next();
               case LEFTPAREN -> {
                    parser.next();
                    parser.accept(new ExpressionVisitor());
               }
          }
     }
}
