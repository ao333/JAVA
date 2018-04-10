public class Binary {

    public static void main(String[] args) {
        int[] a = {5,3,1,9,7};
        System.out.print(search(a,0,a.length-1,9));
    }

    static int search(int array[], int left, int right, int target) {
        int mid_index = (left + right) / 2;
        int mid = array[mid_index];
        if (mid == target) return mid_index;
        else if (mid > target && mid_index > left) return search(array, left, mid_index, target);
        else if (mid < target && mid_index < right) return search(array, mid_index + 1, right, target);
        else return -1;
    }
}
