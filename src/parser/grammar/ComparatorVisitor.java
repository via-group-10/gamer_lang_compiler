package parser.grammar;

import exceptions.TokenMismatchException;
import parser.Parser;
import ast.BinaryExpression;
import ast.Expression;
import ast.Operator;
import tokens.TokenKind;

public class ComparatorVisitor implements GrammarVisitor<Expression> {
    @Override
    public Expression visit(Parser parser) {
        Expression e1 = parser.accept(new ExpressionVisitor());
        if (parser.isCurrentTokenOfKind(TokenKind.OPERATOR)
            && parser.getCurrentToken().isComOperator())
        {
            Operator op = parser.accept(new OperatorVisitor());
            Expression e2 = parser.accept(new ExpressionVisitor());
            return new BinaryExpression(e1, op, e2);
        }
        else
            throw new TokenMismatchException(parser.getCurrentPosition(), parser.getCurrentToken().spelling, "==, <>, <, >");
    }
}
