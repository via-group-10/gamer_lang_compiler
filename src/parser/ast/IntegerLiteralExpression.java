package parser.ast;

public class IntegerLiteralExpression extends Expression
{
     private IntegerLiteral integer;

     public IntegerLiteralExpression(IntegerLiteral integer)
     {

          this.integer = integer;
     }

     public IntegerLiteral getIntegerLiteral()
     {
          return integer;
     }
}
