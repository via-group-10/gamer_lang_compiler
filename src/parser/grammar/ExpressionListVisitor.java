package parser.grammar;

import parser.Parser;
import tokens.TokenKind;
import ast.*;

public class ExpressionListVisitor implements GrammarVisitor<ExpressionList>
{
     @Override
     public ExpressionList visit(Parser parser)
     {
          ExpressionList expressionList = new ExpressionList();
          do {
               if (parser.isCurrentTokenOfKind(TokenKind.COMMA))
                    parser.next();
               Expression expression = parser.accept(new ExpressionVisitor());
               expressionList.addExpression(expression);
          } while (parser.isCurrentTokenOfKind(TokenKind.COMMA));
          return expressionList;
     }
}
