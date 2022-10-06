package parser.grammar;

import parser.Parser;
import parser.ast.*;
import tokens.Token;
import tokens.TokenKind;

public class OneDeclarationVisitor implements GrammarVisitor<Declaration>
{
     @Override
     public Declaration visit(Parser parser)
     {
          Declaration declaration = null;
          if (parser.isCurrentTokenOfKind(TokenKind.HP, TokenKind.MANA))
          {
               Token typeToken = parser.getCurrentToken();
               parser.accept(TokenKind.HP,TokenKind.MANA);
               Token identifier =  parser.getCurrentToken();
               parser.accept(TokenKind.IDENTIFIER);
               parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               declaration = new VariableDeclaration(typeToken.spelling, identifier.spelling);
          }
          else if (parser.isCurrentTokenOfKind(TokenKind.NPC))
          {
               parser.accept(TokenKind.NPC);

               Token tokenType = parser.getCurrentToken();
               parser.accept(TokenKind.HP, TokenKind.MANA);
               Token identifier = parser.getCurrentToken();
               parser.accept(TokenKind.IDENTIFIER);
               FunctionDeclaration funcDeclaration = new FunctionDeclaration(tokenType.spelling, identifier.spelling);

               if (parser.isCurrentTokenOfKind(TokenKind.LEFTPAREN))
               {
                    parser.next();
                    if (parser.isCurrentTokenOfKind(TokenKind.HP, TokenKind.MANA))
                    {
                         Declarations declarations = parser.accept(new ArgListVisitor());
                         funcDeclaration.setArguments(declarations);
                    }
                    parser.accept(TokenKind.RIGHTPAREN);

                    Block block = parser.accept(new BlockVisitor());
                    funcDeclaration.setBlock(block);

                    parser.accept(TokenKind.MVP);

                    Expression expression = parser.accept(new ExpressionVisitor());
                    funcDeclaration.setMvpExpression(expression);
               }
               parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               declaration = funcDeclaration;
          }
          return declaration;
     }
}
