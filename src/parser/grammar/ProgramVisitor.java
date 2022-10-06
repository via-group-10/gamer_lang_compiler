package parser.grammar;

import parser.Parser;
import parser.ast.Block;
import parser.ast.Program;
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
