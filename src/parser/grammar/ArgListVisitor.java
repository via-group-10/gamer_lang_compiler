package parser.grammar;

import parser.Parser;
import ast.Declaration;
import ast.Declarations;
import ast.VariableDeclaration;
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
               parser.next();
               Token identifierToken = parser.getCurrentToken();
               parser.accept(TokenKind.IDENTIFIER);
               Declaration declaration = new VariableDeclaration(typeToken.spelling, identifierToken.spelling);
               argList.addDeclaration(declaration);
          } while (parser.isCurrentTokenOfKind(TokenKind.COMMA));

          return argList;
     }
}
