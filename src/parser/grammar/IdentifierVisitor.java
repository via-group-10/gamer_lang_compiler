package parser.grammar;

import parser.Parser;
import ast.Identifier;
import tokens.TokenKind;

public class IdentifierVisitor implements GrammarVisitor<Identifier> {
    @Override
    public Identifier visit(Parser parser) {
        var idTokenSpelling = parser.getCurrentToken().spelling;
        parser.accept(TokenKind.IDENTIFIER);
        return new Identifier(idTokenSpelling);
    }
}
