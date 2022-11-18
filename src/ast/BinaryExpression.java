package ast;

public class BinaryExpression extends Expression
{
    private Expression expressionLeft;
    private Operator operator;
    private Expression expressionRight;

    public BinaryExpression(Expression expressionLeft, Operator operator, Expression expressionRight) {
        this.expressionLeft = expressionLeft;
        this.operator = operator;
        this.expressionRight = expressionRight;
    }

    public Expression getExpressionLeft() {
        return expressionLeft;
    }

    public Operator getOperator() {
        return operator;
    }

    public Expression getExpressionRight() {
        return expressionRight;
    }

    @Override
    public Object accept(AbstractSyntaxTreeVisitor v, Object arg) {
        return v.visit(this, arg);
    }
}
