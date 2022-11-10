package parser.grammar;

import parser.Parser;
import tokens.Token;
import tokens.TokenKind;
import ast.*;

public class PrimaryVisitor implements GrammarVisitor<Expression>
{
     private int depth;
     private static final int MAX_DEPTH = 50;

     public PrimaryVisitor()
     {
          depth = 0;
     }

     @Override
     public Expression visit(Parser parser)
     {
          if (depth > MAX_DEPTH)
               throw new RuntimeException("Primary has reached its maximal depth: " + MAX_DEPTH);
          depth++;

          Expression expression = null;
          Token current = parser.getCurrentToken();
          switch (current.kind) {
               case BUFF -> {
                    String buffSpelling = parser.getCurrentToken().spelling;
                    parser.accept(TokenKind.BUFF);
                    expression = parser.accept(new PrimaryVisitor());
                    expression = new UnaryExpression(buffSpelling, expression);
               }
               case IDENTIFIER -> {
                    Identifier identifier = parser.accept(new IdentifierVisitor());

                    ExpressionList expressionList;
                    if (parser.isCurrentTokenOfKind(TokenKind.LEFTPAREN)) {
                         parser.next();
                         if (parser.isCurrentTokenOfKind(TokenKind.IDENTIFIER,
                              TokenKind.INTEGERLITERAL, TokenKind.CHARACTERLITERAL,
                              TokenKind.LEFTPAREN, TokenKind.BUFF)) {
                              expressionList = parser.accept(new ExpressionListVisitor());
                              expression = new FunctionExpression(identifier, expressionList);
                         }
                         else {
                              expression = new FunctionExpression(identifier);
                         }
                         parser.accept(TokenKind.RIGHTPAREN);
                    }
                    else {
                         expression = new VariableExpression(identifier);
                    }
               }
               case INTEGERLITERAL -> {
                    expression = new IntegerLiteralExpression(parser.accept(new IntegerLiteralVisitor()));
               }
               case CHARACTERLITERAL -> {
                    expression = new CharacterLiteralExpression(parser.accept(new CharacterLiteralVisitor()));
               }
               case LEFTPAREN -> {
                    parser.accept(TokenKind.LEFTPAREN);
                    expression = parser.accept(new ExpressionVisitor());
                    parser.accept(TokenKind.RIGHTPAREN);
               }
          }
          return expression;
     }
}
