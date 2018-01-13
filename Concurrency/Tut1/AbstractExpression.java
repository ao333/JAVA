package intro;

public abstract class AbstractExpression 
implements Expression {

  public int compareTo(Expression that) {
    return this.evaluate() - that.evaluate();
  }
  
}
