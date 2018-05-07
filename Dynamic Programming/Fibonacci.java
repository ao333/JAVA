public class Fibonacci {

    static int[] mem = new int[51]; // memoization array, java defaults to 0

    public static void main(String[] args) {
        System.out.println(fibMem(8));
        System.out.println(fibIt(8));
    }

    static int fibMem(int n) {
        if (n <= 1) return n;
        if (mem[n] != 0) return mem[n]; // if array not empty, then it's calculated. Return memorized value.
        mem[n] = fibMem(n-2) + fibMem(n-1); // Initial performance boost as n-2 is computed before n-1
        return mem[n]; // 2^N for regular, almost O(N) time-wise, but takes more memory than It
    }

    static int fibIt(int n) {
        mem[1]=1;
        for (int i=2; i<=n; i++) mem[i] = mem[i-1] + mem[i-2]; // No boost as both are initially memorized
        return mem[n]; // Only O(N)
    }
}
