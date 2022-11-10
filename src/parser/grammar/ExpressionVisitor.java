package parser.grammar;

import parser.Parser;
import tokens.TokenKind;
import ast.*;

public class ExpressionVisitor implements GrammarVisitor<Expression> {
    @Override
    public Expression visit(Parser parser) {
        Expression e1 = parser.accept(new QuaternaryVisitor());
        if (parser.isCurrentTokenOfKind(TokenKind.OPERATOR)
                && parser.getCurrentToken().isAssignOperator()) {
            Operator op = parser.accept(new OperatorVisitor());
            Expression e2 = parser.accept(new ExpressionVisitor());
            e1 = new BinaryExpression(e1, op, e2);
        }
        return e1;
    }
}
