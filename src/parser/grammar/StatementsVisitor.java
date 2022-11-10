package parser.grammar;

import parser.Parser;
import ast.AbstractSyntaxTree;
import ast.Statements;
import tokens.TokenKind;

public class StatementsVisitor implements GrammarVisitor<Statements>
{
     @Override
     public Statements visit(Parser parser)
     {
          Statements statements = new Statements();
          while (parser.isCurrentTokenOfKind(TokenKind.BUFF, TokenKind.IDENTIFIER,
               TokenKind.INTEGERLITERAL, TokenKind.CHARACTERLITERAL, TokenKind.LEFTPAREN,
               TokenKind.OP, TokenKind.PATCH, TokenKind.CHAT, TokenKind.FEED))
          {
               statements.addStatement(parser.accept(new OneStatementVisitor()));
          }
          return statements;
     }
}
