package exceptions;

import parser.ast.AbstractSyntaxTree;

import java.lang.reflect.Type;

public class UnexpectedAbstractSyntaxTreeException extends RuntimeException
{
     private Type expected;
     private Type actual;

     public UnexpectedAbstractSyntaxTreeException(AbstractSyntaxTree actual, Type expected)
     {
          this.actual = actual.getClass();
          this.expected = expected;
     }

     @Override
     public String getMessage()
     {
          return "Unexpected abstract syntax tree node: " + actual.getTypeName() + ". Expected: " + expected.getTypeName();
     }
}
