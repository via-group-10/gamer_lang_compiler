package parser;

import scanner.Scanner;
import tokens.Token;

public class Parser
{
     private Scanner scanner;
     private Token currentToken;

     public Parser(Scanner scanner)
     {
          this.scanner = scanner;
          currentToken = scanner.scan();
     }

     public void parseProgram()
     {
     }
}
