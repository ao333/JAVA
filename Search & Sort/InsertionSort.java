public class InsertionSort {

    public static void main(String[] args) {
        int[] a = {5, 3, 1, 9, 7};
        insertionSort(a);
        for (int i:a) System.out.print(i+",");
    }

    private static void insertionSort(int[] a) {
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
