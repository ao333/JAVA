public class RodCutting {

    public static void main(String args[]) {
        int p[] = new int[] {1, 5, 8, 9};
        int n = p.length;
        System.out.println(rodCutting(p, n));
    }

    private static int rodCutting(int p[], int n) {
        if (n <= 0) return 0;
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, p[i] + rodCutting(p, n-i-1));
        return max;
    }
}
