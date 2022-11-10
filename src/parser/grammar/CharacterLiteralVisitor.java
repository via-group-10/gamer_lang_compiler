package parser.grammar;

import parser.Parser;
import ast.CharacterLiteral;
import tokens.TokenKind;

public class CharacterLiteralVisitor implements GrammarVisitor<CharacterLiteral> {
    @Override
    public CharacterLiteral visit(Parser parser) {
        var tokenSpelling = parser.getCurrentToken().spelling;
        parser.accept(TokenKind.CHARACTERLITERAL);
        return new CharacterLiteral(tokenSpelling);
    }
}
