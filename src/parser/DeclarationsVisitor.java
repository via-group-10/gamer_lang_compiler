package parser;

import tokens.TokenKind;

public class DeclarationsVisitor implements GrammarVisitor
{
     @Override
     public void visit(Parser parser)
     {
          while (parser.isCurrentTokenOfKind(TokenKind.NPC, TokenKind.HP, TokenKind.MANA))
          {
               parser.accept(new OneDeclarationVisitor());
          }
     }
}
