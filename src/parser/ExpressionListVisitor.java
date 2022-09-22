package parser;

import tokens.TokenKind;

public class ExpressionListVisitor implements GrammarVisitor
{
     private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();
     @Override
     public void visit(Parser parser)
     {
          parser.accept(expressionVisitor);
          while (parser.isCurrentTokenOfKind(TokenKind.COMMA))
          {
               parser.next();
               parser.accept(expressionVisitor);
          }
     }
}
