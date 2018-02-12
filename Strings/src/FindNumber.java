public class FindNumber {

    public static void main(String[] args) {
        int[] a = {1,3,5,7,9};
        int k = 5;
        System.out.print(findNumber(a,k));
    }

    private static String findNumber(int[] a, int k) {
        for(int i=0; i<a.length; i++)
            if (a[i] == k)
                return "Yes";
        return "No";
    }
}
