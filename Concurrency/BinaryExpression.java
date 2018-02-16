public abstract class BinaryExpression implements Expression {
    protected Expression left, right;

    public BinaryExpression (Expression l, Expression r) {
        left = l;
        right = r;
    }
}