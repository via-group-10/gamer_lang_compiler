package parser.grammar;

import ast.*;
import parser.Parser;
import tokens.Token;
import tokens.TokenKind;

public class ArgListVisitor implements GrammarVisitor<Declarations>
{
     @Override
     public Declarations visit(Parser parser)
     {
          Declarations argList = new Declarations();
          do {
               if (parser.isCurrentTokenOfKind(TokenKind.COMMA))
                    parser.next();
               Token typeToken = parser.getCurrentToken();
               Type type = Type.getType(typeToken.spelling);
               parser.accept(TokenKind.HP,TokenKind.MANA);
               Identifier identifier = parser.accept(new IdentifierVisitor());
               Declaration declaration = new VariableDeclaration(identifier, type);
               argList.addDeclaration(declaration);
          } while (parser.isCurrentTokenOfKind(TokenKind.COMMA));

          return argList;
     }
}
