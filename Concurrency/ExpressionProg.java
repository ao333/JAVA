package intro;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
/**
 * This program creates and evaluates some arithmetic expressions
 */
public class ExpressionProg {     

	public static void main(String[] args) {

		List<Expression> nums = new LinkedList<Expression>();
		do {
			nums.add(randomExpression(4));
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
            if (exps == null)
            		exps = new LinkedList<Expression>();
            exps.add(e);
            	m.put(e.evaluate() % 10, exps);
        }
		
		
        for (Integer key : m.keySet()) {
            System.out.println("Key:" + key);
            for (Expression ex : m.get(key)) {
                System.out.println(ex + " = " + ex.evaluate());
            }
        }

	}
    
	
	static Expression randomExpression(int maxDepth) {

		if (maxDepth <= 0) 
			return null;

		Random r = new java.util.Random();
		
		if (maxDepth == 1)
			return new WholeNumber(r.nextInt(100));

		switch(r.nextInt(4)) {
		case 0:
			return new WholeNumber(r.nextInt(100));
		case 1: 
			return new Sum(randomExpression(maxDepth-1), randomExpression(maxDepth-1));
		case 2:
			return new Product(randomExpression(maxDepth-1), randomExpression(maxDepth-1));
		case 3: 
			try {
				return new Division(randomExpression(maxDepth-1), randomExpression(maxDepth-1));
			} catch (WholeDivisionUndefined e) {
				return randomExpression(maxDepth-1);
			}		
		}
		return null;
	}
}
