package parser;

import exceptions.TokenMismatchException;
import ast.*;
import parser.grammar.GrammarVisitor;
import parser.grammar.ProgramVisitor;
import scanner.Scanner;
import tokens.Token;
import tokens.TokenKind;

import java.util.Arrays;

public class Parser
{
     private final Scanner scanner;
     private Token currentToken;
     private long currentPosition;


     public Parser(Scanner scanner)
     {
          this.scanner = scanner;
          currentToken = scanner.scan();
          currentPosition = 0;
     }

     public Program parseProgram()
     {
          try
          {
               return accept(new ProgramVisitor());
          }
          catch (TokenMismatchException tme)
          {
               System.out.println(tme.getMessage());
               return null;
          }
     }

     ///MUST BE THAT
     public <T extends AbstractSyntaxTree> T accept(GrammarVisitor<T> visitor)
     {
          return visitor.visit(this);
     }

     ///MUST BE OF TOKEN and moves to next
     public void accept(TokenKind... tokenKinds) throws TokenMismatchException
     {
          if (isCurrentTokenOfKind(tokenKinds))
               next();
          else
               throw new TokenMismatchException(currentPosition, currentToken.kind, tokenKinds);
     }

     public Token getCurrentToken()
     {
          return currentToken;
     }

     public long getCurrentPosition() {
          return currentPosition;
     }

     //checks if token is of the kind
     public boolean isCurrentTokenOfKind(TokenKind... tokenKinds)
     {
          return Arrays.stream(tokenKinds).anyMatch(t -> t == currentToken.kind);
     }

     public void next()
     {
          currentToken = scanner.scan();
          currentPosition++;
     }
}
