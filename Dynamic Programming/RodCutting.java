public class RodCutting {

    public static void main(String args[]) {
        int p[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        int n = 4;
        System.out.println(rodCutting(p, n));
    }
    // Iterative Memoization
    private static int rodCutting(int p[], int n) {
        int v[] = new int[n+1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++)
                max = Math.max(max, p[j] + v[i-j-1]);
            v[i] = max;
        }
        return v[n];
    }
    // Naive Recursive (Step through Debugger to attest)
    private static int rodCuttingR(int p[], int n) {
        if (n <= 0) return 0;
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, p[i] + rodCuttingR(p, n-i-1));
        return max;
    }
}
