package parser.grammar;

import ast.AbstractSyntaxTreeVisitor;
import ast.Block;
import ast.Program;
import parser.Parser;
import ast.Declarations;
import tokens.TokenKind;

public class DeclarationsVisitor implements GrammarVisitor<Declarations>
{
     @Override
     public Declarations visit(Parser parser)
     {
          Declarations declarations = new Declarations();
          while (parser.isCurrentTokenOfKind(TokenKind.NPC, TokenKind.HP, TokenKind.MANA))
          {
               declarations.addDeclaration(parser.accept(new OneDeclarationVisitor()));
          }
          return declarations;
     }


}
