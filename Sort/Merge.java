public class Merge {

    public static void main(String[] args) {
        int[] a = {5,3,1,9,7};
        sort(a,0,a.length-1);
        for (int i:a) System.out.print(i+",");
    }

    static void sort(int a[], int l, int r) {
        if (l < r) {
            int m = (l+r)/2; // Find the middle point
            sort(a, l, m); // Sort first half
            sort(a , m+1, r); // Sort second half
            merge(a, l, m, r); // Merge the sorted halves
        }
    }

    static void merge(int a[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        // Create temp arrays
        int L[] = new int [n1];
        int R[] = new int [n2];
        // Copy data to temp arrays
        for (int i=0; i<n1; i++)
            L[i] = a[l + i];
        for (int j=0; j<n2; j++)
            R[j] = a[m + 1 + j];
        // Initial indexes of first, second and merged subarrays
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }
        // Copy remaining elements of L[] and R[] if any
        while (i < n1) {
            a[k] = L[i];
            i++; k++;
        }
        while (j < n2) {
            a[k] = R[j];
            j++; k++;
        }
    }
}
