public class GreatestSumContinuousSubarray {

    public static void main(String[] args) {
        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(maxSubArraySum(a));
    }

    static int maxSubArraySum(int a[]) {
        int n = a.length;
        int cur_max = a[0], max = a[0];
        for (int i = 1; i < n; i++) {
            cur_max = Math.max(a[i], cur_max+a[i]);
            max = Math.max(max, cur_max);
        }
        return max;
    }
}
