package parser.ast;

public class IntegerLiteralExpression extends Expression
{
     private String integer;

     public IntegerLiteralExpression(String integer)
     {

          this.integer = integer;
     }

     public String getSpelling()
     {
          return integer;
     }
}
