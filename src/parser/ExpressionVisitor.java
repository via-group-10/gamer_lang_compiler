package parser;

import tokens.TokenKind;

public class ExpressionVisitor implements GrammarVisitor
{
     private GrammarVisitor primaryVisitor;

     public ExpressionVisitor()
     {
          primaryVisitor = new PrimaryVisitor();
     }

     @Override
     public void visit(Parser parser)
     {
          if (parser.isCurrentTokenOfKind(TokenKind.BUFF))
          {
               parser.next();
               parser.accept(primaryVisitor);
          }
          else
          {
               parser.accept(primaryVisitor);
               while (parser.isCurrentTokenOfKind(TokenKind.OPERATOR))
               {
                    parser.next();
                    parser.accept(primaryVisitor);
               }
          }
     }
}
