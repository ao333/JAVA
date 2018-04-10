package intro;

public class Product extends BinaryExpression {

  public Product(Expression l, Expression r) {
    super(l,  r);
  }

  public int evaluate() {
    return left.evaluate() * right.evaluate(); 
  }

  @Override
  public String toString() {
    return "(" +left + " * " + right + ")";
  }
}
