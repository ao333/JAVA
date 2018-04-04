public class HeapSort {
    
    public static void main(String[] args) {
        int[] a = {5,3,1,9,7};
        heapSort(a);
        for (int i:a) System.out.print(i + ",");
    }
    
    private static void heapSort(int a[]) {
        int n = a.length;
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) heapify(a, n, i);
        // One by one extract an element from heap and move current root to end
        for (int i = n - 1; i >= 0; i--) {
            int swap = a[0];
            a[0] = a[i];
            a[i] = swap;
            heapify(a, i, 0); // call max heapify on the reduced heap
        }
    }
    // Heapify a subtree rooted with node i which is an index in a[]. n is size of heap
    private static void heapify(int a[], int n, int i) {
        int max = i; // Initialize max as root
        int l = 2*i + 1, r = 2*i + 2;
        // If left child is larger than root
        if (l < n && a[l] > a[max]) max = l;
        // If right child is larger than max so far
        if (r < n && a[r] > a[max]) max = r;
        // If max is not root
        if (max != i) {
            int swap = a[i];
            a[i] = a[max];
            a[max] = swap;
            heapify(a, n, max); // Recursively heapify the affected sub-tree
        }
    }
}
