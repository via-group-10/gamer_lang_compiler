package parser.grammar;

import parser.Parser;
import parser.ast.*;
import tokens.Token;
import tokens.TokenKind;

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
               case IDENTIFIER -> {
                    String identifierName = current.spelling;
                    parser.next();

                    ExpressionList expressionList;
                    if (parser.isCurrentTokenOfKind(TokenKind.LEFTPAREN)) {
                         parser.next();
                         if (parser.isCurrentTokenOfKind(TokenKind.IDENTIFIER,
                              TokenKind.INTEGERLITERAL, TokenKind.CHARACTERLITERAL,
                              TokenKind.LEFTPAREN, TokenKind.BUFF)) {
                              expressionList = parser.accept(new ExpressionListVisitor());
                              expression = new FunctionExpression(identifierName, expressionList);
                         }
                         else {
                              expression = new FunctionExpression(identifierName);
                         }
                         parser.accept(TokenKind.RIGHTPAREN);
                    }
                    else {
                         expression = new VariableExpression(identifierName);
                    }
               }
               case INTEGERLITERAL -> {
                    new IntegerLiteralExpression(current.spelling);
                    parser.next();
               }
               case CHARACTERLITERAL -> {
                    new CharacterLiteralExpression(current.spelling);
                    parser.next();
               }
               case LEFTPAREN -> parser.accept(new ExpressionVisitor());
          }
          return expression;
     }
}
