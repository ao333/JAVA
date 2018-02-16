public class WholeNumber implements Expression {
    private int value;

    public WholeNumber(int v) { value = v; }

    public String toString() { return String.valueOf(value); }

    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (!(ob instanceof WholeNumber)) return false;

        return this.value == ((WholeNumber) ob).value;
    }

    public int evaluate() { return this.value; }
}