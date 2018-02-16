public interface Expression extends Comparable<Expression> {
    public int evaluate();

    default public int compareTo(Expression other) {
        if (this.evaluate() > other.evaluate()) { return 1; }
        else if (this.evaluate() < other.evaluate()) { return -1; }
        return 0;
    }
}
