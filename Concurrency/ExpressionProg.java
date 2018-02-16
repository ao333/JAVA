import java.util.*;

public class ExpressionProg {
    private static final int SIZE=5;

    public static void main(String[] args){
        WholeNumber first = new WholeNumber(10);
        WholeNumber second = new WholeNumber(5);
        String equal = first.equals(second)?"equal":"unequal";
        System.out.printf("first number is %s\nsecond number is %s\nfirst is %s to second\n", first, second, equal);

        Expression[] whole = new Expression[SIZE];
        for (int i=0; i<whole.length; i++)
            whole[i] = new WholeNumber((int) (Math.random() * 100));
        System.out.printf("My array is %s\n", Arrays.toString(whole));

        Expression sum = new Sum(first,second);
        System.out.printf("sum is %s\n", sum);

        Expression product = new Product(first,second);
        System.out.printf("product is %s\n", product);

        Expression quotient = new Product(first,second);
        System.out.printf("quotient is %s\n", quotient);

        Arrays.sort(whole);
        System.out.printf("After sorting, the numbers are %s\n", Arrays.toString(whole));

        List<Expression> nums = new LinkedList<Expression>();
        do { nums.add(randomExpression(4));
        } while (Math.random() < 0.95);

        System.out.println("The numbers are:");
        Iterator<Expression> I = nums.iterator();
        while(I.hasNext()) {
            Expression e = I.next();
            System.out.println(e + " = " + e.evaluate());
        }

        Map<Integer,List<Expression>> m = new HashMap<Integer,List<Expression>>();
        for (Expression e : nums) {
            List<Expression> exps = m.get(e.evaluate() % 10);
            if (exps == null) exps = new LinkedList<Expression>();
            exps.add(e);
            m.put(e.evaluate() % 10, exps);
        }

        for (Integer key : m.keySet()) {
            System.out.println("Key:" + key);
            for (Expression ex : m.get(key)) System.out.println(ex + " = " + ex.evaluate());
        }
    }

    static Expression randomExpression(int maxDepth) {

        if (maxDepth <= 0) return null;

        Random r = new java.util.Random();
        if (maxDepth == 1) return new WholeNumber(r.nextInt(100));

        switch(r.nextInt(4)) {
            case 0: return new WholeNumber(r.nextInt(100));
            case 1: return new Sum(randomExpression(maxDepth-1), randomExpression(maxDepth-1));
            case 2: return new Product(randomExpression(maxDepth-1), randomExpression(maxDepth-1));
            case 3:
                try { return new Division(randomExpression(maxDepth-1), randomExpression(maxDepth-1));
                } catch (WholeDivisionUndefined e) { return randomExpression(maxDepth-1); }
        }
        return null;
    }
}
