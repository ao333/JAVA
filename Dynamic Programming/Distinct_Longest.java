import java.util.*;

public class Distinct_Longest {
    public static void main(String[] args) {
        int[] a = {-1, -2, -3, -2, 1, 4, 5, -3};
        int[] b = {-2, 0, 4, 8, 6, 1, 5, -3};
        int[] c = {56, -12, 4, 34, -3, 5, 35};
        System.out.println("Array a Distinct? " + DISTINCT(a));
        System.out.println("Array b Distinct? " + DISTINCT(b));
        System.out.println("Array a Distinct? " + DISTINCT2(a));
        System.out.println("Array b Distinct? " + DISTINCT2(b));
        System.out.println("Array a Distinct? " + DISTINCT3(a));
        System.out.println("Array b Distinct? " + DISTINCT3(b));
        System.out.println("Longest Length of c " + LONGEST(c));
        System.out.println("Longest Length of a " + LONGEST(a));
    }

    /* 1 a) The time complexity of this solution is O(N^2) since there are 2 for-loops of which the second one is nested.
    The second loop increments the entire array for every index the first array increments through.
    Both are proportional to the array size.

    The space complexity is O(1), since no new array is created. */
    private static boolean DISTINCT(int a[]) {
        for (int i=0; i<a.length; i++)
            for (int j=0; j<a.length; j++) {
                if (i == j) continue;
                if (a[i] == a[j]) return false;
            }
        return true;
    }

    /* 1 a) DISTINCT2 is superior to DISTINCT because it reduces the time complexity by reducing the number of times,
    the nested for-loop increments, since we can assume that the function would have returned false, had a repetition
    been found before j=i+1. However, both loops are still proportional to array size so time complexity remains O(N^2).

    No space complexity difference compared to DISTINCT*/
    private static boolean DISTINCT2(int a[]) {
        for (int i=0; i<a.length-1; i++){
            for (int j=i+1; j<a.length; j++){
                if (a[i] == a[j]) return false;
            }
        }
        return true;
    }

    /* 1 b) Distinct3 uses a Hash Set. The structure, which is a hash table that disallows duplicate keys, was chosen
    for its suitability to this question. The contains method check against a no-duplicate set in constant time.
    The add method builds that set in constant time. However, since we still need to iterate through the list
    to compare and build the list, the overall function is still proportional to the array size, therefore O(N).

    While the single for-loop and hashing reduces time complexity, the creation of a new hash set proportional to the
    array size increases its space complexity to O(N). This is the trade-off compared to implementations in 1 a). */
    private static boolean DISTINCT3(int a[]) {
        Set<Integer> hs = new HashSet<>();
        for (int i : a) {
            if (hs.contains(i)) return false;
            hs.add(i);
        }
        return true;
    }

    /* 2 This problem can be split into two problems. The first will be to record the number of integers smaller than
    the number at index i. If subsequent numbers are larger than the record at index i, then we can +1 to the max length
    because a larger subsequent number will always increase the max length given there is no contiguous requirement.

    The time complexity of this problem is O(N^2) since there is a nested for-loop that increments proportionally to the
    array size. The 2 other loops run in parallel, so they do not affect the overall time complexity. The creation of a
    longest[] array holding the longest increasing sequence increases the space complexity to O(N). */
    private static int LONGEST(int a[]) {
        int size = a.length;
        int longest[] = new int[size];
        int max = 0;

        for (int x=0; x<size; x++)
            longest[x] = 1;

        for (int i=1; i<size; i++)
            for (int j=0; j<i; j++)
                if (a[i]>a[j] && longest[i]<longest[j]+1)
                    longest[i] = longest[j] + 1;

        for (int y=0; y<size; y++)
            if (max < longest[y])
                max = longest[y];

        return max;
    }
}
