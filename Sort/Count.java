public class Count {

    public static void main(String args[]) {
        Count ob = new Count();
        int arr[] = {45, 75, 90, 24, 2, 66};
        ob.sort(arr);
        for (int i:arr) System.out.print(i+" ");
    }

    void sort(int arr[]) {
        int n = arr.length;
        int output[] = new int[n]; // The output sorted array
        int count[] = new int[128]; // Create another array to store the count of every integer
        for (int i:arr) count[i]++;
        // Change count[i] so that count[i] now contains actual position of this character in output array
        for (int i=1; i<128; i++) count[i] += count[i-1];
        // Build the output array
        for (int i:arr) {
            output[count[i]-1] = i;
            count[i]--;
        }
        // Copy the output array to arr, so that arr now contains sorted characters
        for (int i = 0; i<n; i++) arr[i] = output[i];
    }
}