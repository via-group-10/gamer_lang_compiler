package parser.grammar;

import parser.Parser;
import parser.ast.BinaryExpression;
import parser.ast.Expression;
import parser.ast.Operator;
import tokens.TokenKind;

public class SecondaryVisitor implements GrammarVisitor<Expression> {
    @Override
    public Expression visit(Parser parser) {
        Expression e1 = parser.accept(new PrimaryVisitor());

        while (parser.isCurrentTokenOfKind(TokenKind.OPERATOR)
                && parser.getCurrentToken().isExpOperator())
        {
            Operator op = parser.accept(new OperatorVisitor());
            Expression e2 = parser.accept(new PrimaryVisitor());
            e1 = new BinaryExpression(e1, op, e2);
        }
        return e1;
    }
}
