public class Insertion {

    public static void main(String[] args) {
        int[] a = {5, 3, 1, 9, 7};
        sort(a);
        for (int i:a) System.out.print(i+",");
    }

    static void sort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++)
            for (int j = i; j > 0; j--)
                if (a[j] < a[j - 1]) {
                    int swap = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = swap;
                }
    }
}
