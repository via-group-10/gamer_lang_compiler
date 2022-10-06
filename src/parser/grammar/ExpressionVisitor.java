package parser.grammar;

import exceptions.UnexpectedAbstractSyntaxTreeException;
import jdk.jshell.spi.ExecutionControl;
import parser.Parser;
import parser.ast.BinaryExpression;
import parser.ast.Expression;
import parser.ast.Operator;
import parser.ast.UnaryExpression;
import tokens.Token;
import tokens.TokenKind;

public class ExpressionVisitor implements GrammarVisitor<Expression>
{
     private PrimaryVisitor primaryVisitor;

     public ExpressionVisitor()
     {
          primaryVisitor = new PrimaryVisitor();
     }

     @Override
     public Expression visit(Parser parser)
     {

          if (parser.isCurrentTokenOfKind(TokenKind.BUFF)) {
               Token tokenBuff = parser.getCurrentToken();
               parser.next();
               Expression expression = parser.accept(primaryVisitor);
               return new UnaryExpression(tokenBuff.spelling, expression);
          }
//          case IDENTIFIER, INTEGERLITERAL, CHARACTERLITERAL, LEFTPAREN, OPERATOR -> {
          else {
               throw new RuntimeException("ExpressionVisitor.visit not implemented");
          }
     }
}
