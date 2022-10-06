package parser.grammar;

import parser.Parser;
import parser.ast.*;
import tokens.Token;
import tokens.TokenKind;

public class OneStatementVisitor implements GrammarVisitor<Statement>
{
     @Override
     public Statement visit(Parser parser)
     {
          Statement statement = null;
          switch (parser.getCurrentToken().kind) {
               case OP -> {
                    parser.next();
                    Expression expression = parser.accept(new ExpressionVisitor());
                    parser.accept(TokenKind.PVP);
                    Statements pvpStatements = parser.accept(new StatementsVisitor());
                    Statements pveStatements = null;
                    if (parser.isCurrentTokenOfKind(TokenKind.PVE))
                    {
                         parser.next();
                         pveStatements = parser.accept(new StatementsVisitor());
                         statement = new OpStatement(expression, pvpStatements, pveStatements);
                    }
                    else
                    {
                         statement = new OpStatement(expression, pvpStatements);
                    }
                    parser.accept(TokenKind.COOLDOWN);
                    parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               }
               case PATCH -> {
                    parser.next();
                    Expression expression = parser.accept(new ExpressionVisitor());
                    parser.accept(TokenKind.GLHF);
                    Statements statements = parser.accept(new StatementsVisitor());
                    statement = new PatchStatement(expression, statements);
                    parser.accept(TokenKind.RAGEQUIT);
                    parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               }
               case CHAT -> {
                    parser.next();
                    Expression expression = parser.accept(new ExpressionVisitor());
                    statement = new ChatStatement(expression);
                    parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               }
               case FEED -> {
                    parser.next();
                    Token token = parser.getCurrentToken();
                    parser.accept(TokenKind.IDENTIFIER);
                    statement = new FeedStatement(token.spelling);
                    parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               }
               case BUFF, IDENTIFIER, INTEGERLITERAL, CHARACTERLITERAL, LEFTPAREN, OPERATOR -> {
                    Expression expression = parser.accept(new ExpressionVisitor());
                    statement = new ExpressionStatement(expression);
                    parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               }
          }
          return statement;
     }
}
