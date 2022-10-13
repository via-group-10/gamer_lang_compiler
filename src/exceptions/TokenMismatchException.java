package exceptions;

import tokens.TokenKind;

public class TokenMismatchException extends RuntimeException
{
     private String expectedTokenKind;
     private String actualTokenKind;
     private long position;

     public TokenMismatchException(long position, TokenKind actualTokenKind, TokenKind... expectedTokenKind)
     {
          this.position = position;
          this.expectedTokenKind = expectedTokenKindsToString(expectedTokenKind);
          this.actualTokenKind = actualTokenKind.toString();
     }

     public TokenMismatchException(long position, String actualTokenKind, String expectedTokenKind)
     {
          this.position = position;
          this.actualTokenKind = actualTokenKind;
          this.expectedTokenKind = expectedTokenKind;
     }

     @Override
     public String getMessage()
     {
          return "Unexpected token: " + actualTokenKind + " at position " + position + ". Expected: " + expectedTokenKind;
     }

     private String expectedTokenKindsToString(TokenKind[] expectedTokenKind)
     {
          StringBuilder resultStr = new StringBuilder();
          for (TokenKind tk : expectedTokenKind)
          {
               resultStr.append(tk.toString()).append(" ");
          }
          return resultStr.toString();
     }
}
