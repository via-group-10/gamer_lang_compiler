package parser.grammar;

import ast.Declaration;
import parser.Parser;
import tokens.Token;
import tokens.TokenKind;
import ast.*;

public class OneDeclarationVisitor implements GrammarVisitor<Declaration>
{
     @Override
     public Declaration visit(Parser parser)
     {
          Declaration declaration = null;
          if (parser.isCurrentTokenOfKind(TokenKind.HP, TokenKind.MANA))
          {
               Token typeToken = parser.getCurrentToken();
               Type type = Type.getType(typeToken.spelling);
               parser.accept(TokenKind.HP,TokenKind.MANA);
               Identifier identifier =  parser.accept(new IdentifierVisitor());
               parser.accept(TokenKind.TRIPLEEXCLAMATIONMARK);
               declaration = new VariableDeclaration(identifier, type);
          }
          else if (parser.isCurrentTokenOfKind(TokenKind.NPC))
          {
               parser.accept(TokenKind.NPC);

               Token typeToken = parser.getCurrentToken();
               Type type = Type.getType(typeToken.spelling);
               parser.accept(TokenKind.HP,TokenKind.MANA);
               Identifier identifier =  parser.accept(new IdentifierVisitor());

               FunctionDeclaration funcDeclaration = new FunctionDeclaration(identifier, type);

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
