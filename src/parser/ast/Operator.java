package parser.ast;

public class Operator extends Terminal
{
     private String operator;

     public Operator(String operator)
     {
          this.operator = operator;
     }

     public String getOperator()
     {
          return operator;
     }
}
