package parser;

import tokens.TokenKind;

public class ProgramVisitor implements GrammarVisitor
{
     @Override
     public void visit(Parser parser)
     {
          parser.accept(new BlockVisitor());
          parser.accept(TokenKind.EOF);
     }
}
