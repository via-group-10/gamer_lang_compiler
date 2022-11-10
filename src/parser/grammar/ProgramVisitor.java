package parser.grammar;

import parser.Parser;
import ast.Block;
import ast.Program;
import tokens.TokenKind;

public class ProgramVisitor implements GrammarVisitor<Program>
{
     @Override
     public Program visit(Parser parser)
     {
          Block block = parser.accept(new BlockVisitor());
          parser.accept(TokenKind.EOF);

          return new Program(block);
     }
}
