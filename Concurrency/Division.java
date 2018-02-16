public class Division extends BinaryExpression {

    public Division(Expression l, Expression r) throws WholeDivisionUndefined {
        super(l,r);
        if (r.evaluate()==0 || l.evaluate() % r.evaluate() != 0)
            throw new WholeDivisionUndefined();
    }

    public int evaluate() { return left.evaluate() / right.evaluate(); }

    @Override
    public String toString() { return "(" + left + " / " + right + ")"; }
}
