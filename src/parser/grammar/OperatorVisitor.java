package parser.grammar;

import parser.Parser;
import ast.Operator;
import tokens.TokenKind;

public class OperatorVisitor implements GrammarVisitor<Operator> {
    @Override
    public Operator visit(Parser parser) {
        var opTokenSpelling = parser.getCurrentToken().spelling;
        parser.accept(TokenKind.OPERATOR);
        return new Operator(opTokenSpelling);
    }
}
