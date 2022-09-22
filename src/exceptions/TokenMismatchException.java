package exceptions;

import tokens.TokenKind;

public class TokenMismatchException extends RuntimeException
{
     private TokenKind[] expectedTokenKind;
     private TokenKind actualTokenKind;
     private long position;

     public TokenMismatchException(long position, TokenKind actualTokenKind, TokenKind... expectedTokenKind)
     {
          this.position = position;
          this.expectedTokenKind = expectedTokenKind;
          this.actualTokenKind = actualTokenKind;
     }

     @Override
     public String getMessage()
     {
          return "Unexpected token: " + actualTokenKind.toString() + " at position " + position + ". Expected: " + expectedTokenKindsToString();
     }

     private String expectedTokenKindsToString()
     {
          StringBuilder resultStr = new StringBuilder();
          for (TokenKind tk : expectedTokenKind)
          {
               resultStr.append(tk.toString()).append(" ");
          }
          return resultStr.toString();
     }
}
