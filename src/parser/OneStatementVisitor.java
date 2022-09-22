package parser;

import tokens.TokenKind;

public class OneStatementVisitor implements GrammarVisitor
{
     @Override
     public void visit(Parser parser)
     {
          switch (parser.getCurrentToken().kind) {
               case OP -> {
                    parser.next();
                    parser.accept(new ExpressionVisitor());
                    parser.accept(TokenKind.PVP);
                    parser.accept(new StatementsVisitor());
                    if (parser.isCurrentTokenOfKind(TokenKind.PVE)) {
                         parser.next();
                         parser.accept(new StatementsVisitor());
                    }
                    parser.accept(TokenKind.COOLDOWN);
                    parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               }
               case PATCH -> {
                    parser.next();
                    parser.accept(new ExpressionVisitor());
                    parser.accept(TokenKind.GLHF);
                    parser.accept(new StatementsVisitor());
                    parser.accept(TokenKind.RAGEQUIT);
                    parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               }
               case CHAT, FEED -> {
                    parser.next();
                    parser.accept(new ExpressionVisitor());
                    parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               }
               case BUFF, IDENTIFIER, INTEGERLITERAL, CHARACTERLITERAL, LEFTPAREN, OPERATOR -> {
                    parser.accept(new ExpressionVisitor());
                    parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               }
          }
     }
}
