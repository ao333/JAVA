import java.util.*;

public class Distinct {
    public static void main(String[] args){
        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        int[] b = {-2, 0, 4, 8, 6, 1, 5, -3};
        System.out.println("Array a Distinct? " + DISTINCT(a));
        System.out.println("Array b Distinct? " + DISTINCT(b));
        System.out.println("Array a Distinct? " + DISTINCT2(a));
        System.out.println("Array b Distinct? " + DISTINCT2(b));
        System.out.println("Array a Distinct? " + DISTINCT3(a));
        System.out.println("Array b Distinct? " + DISTINCT3(b));
    }

    /* 1 a)
    The time complexity of this solution is O(N^2) since there are 2 for-loops of which the second one is nested.
    The second loop increments the entire array for every index the first array increments through.
    Both are proportional to the array size.

    The space complexity is O(1), since no new array is created. Constant space is used only for counters and size.
    */
    private static boolean DISTINCT(int a[]){
        for (int i=0; i<a.length; i++)
            for (int j=0; j<a.length; j++) {
                if (i == j) continue;
                if (a[i] == a[j]) return false;
            }
        return true;
    }

    /* 1 a)
    DISTINCT2 is superior to DISTINCT because it reduces the time complexity by reducing the number of times, the
    nested for-loop increments, since we will assume that the function would have returned false, had a repetition been
    found before j=i+1. However, both loops are still proportional to the array size. Time complexity remains O(N^2).

    No space complexity difference compared to DISTINCT
    */
    private static boolean DISTINCT2(int a[]){
        for(int i=0; i<a.length-1; i++){
            for(int j=i+1; j<a.length; j++){
                if (a[i] == a[j]) return false;
            }
        }
        return true;
    }

    /* 1 b)
    Distinct3 uses a Hash Set, a type of hash table that does not allow duplicate keys. The structure was chosen over
    hash table because of its applicability to this question. The contains method check against a no-duplicate set in
    constant time. The add method builds that set in constant time. However, since we still need to iterate through the
    list to compare and build the list, the overall function is still proportional to the array size, therefore O(N).

    Note that while the single forloop and hashing reduces time complexity, the creation of a new hash set proportional
    to the array size increases its space complexity to O(N). This is the trade-off compared to implementations in 1 a).
    */
    private static boolean DISTINCT3(int a[]) {
        Set<Integer> hs = new HashSet<>();
        for(int i : a) {
            if(hs.contains(i)) return false;
            hs.add(i);
        }
        return true;
    }
}
