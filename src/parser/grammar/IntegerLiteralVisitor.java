package parser.grammar;

import parser.Parser;
import parser.ast.IntegerLiteral;
import tokens.TokenKind;

public class IntegerLiteralVisitor implements GrammarVisitor<IntegerLiteral> {
    @Override
    public IntegerLiteral visit(Parser parser) {
        var tokenSpelling = parser.getCurrentToken().spelling;
        parser.accept(TokenKind.INTEGERLITERAL);
        return new IntegerLiteral(tokenSpelling);
    }
}
