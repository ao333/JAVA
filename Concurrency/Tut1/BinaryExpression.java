package intro;

public abstract class BinaryExpression extends AbstractExpression {
  
  protected Expression left;
  protected Expression right;

  public BinaryExpression(Expression l, Expression r) {
    left = l;
    right = r;
  }

}
