package parser.grammar;

import parser.Parser;
import parser.ast.BinaryExpression;
import parser.ast.Expression;
import parser.ast.Operator;
import tokens.TokenKind;

public class TertiaryVisitor implements GrammarVisitor<Expression> {
    @Override
    public Expression visit(Parser parser) {
        Expression e1 = parser.accept(new SecondaryVisitor());

        while (parser.isCurrentTokenOfKind(TokenKind.OPERATOR)
            && parser.getCurrentToken().isMulOperator())
        {
            Operator op = parser.accept(new OperatorVisitor());
            Expression e2 = parser.accept(new SecondaryVisitor());
            e1 = new BinaryExpression(e1, op, e2);
        }
        return e1;
    }
}
