public class OddNumber {

    public static void main(String[] args) {
        int k = 10;
        oddNumber(k);
    }

    // print every odd number from 0 to k
    private static void oddNumber(int k) {
        int i = 0;
        i = (i % 2 == 0? i + 1: i);
        while (i <= k) {
            System.out.print(i+" ");
            i = i+2;
        }
    }
}
