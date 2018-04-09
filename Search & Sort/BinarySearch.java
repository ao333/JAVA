public class BinarySearch {

    public static void main(String[] args) {
        int[] a = {5,3,1,9,7};
        System.out.print(binarySearch(a,0,a.length-1,9));
    }

    static int binarySearch(int array[], int left, int right, int target) {
        int mid_index = (left + right) / 2;
        int mid = array[mid_index];
        if (mid == target) return mid_index;
        else if (mid > target && mid_index > left) return binarySearch(array, left, mid_index, target);
        else if (mid < target && mid_index < right) return binarySearch(array, mid_index + 1, right, target);
        else return -1;
    }
}
