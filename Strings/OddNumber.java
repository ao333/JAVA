public class OddNumber {

    public static void main(String[] args) {
        int k = 10;
        oddNumber(k);
    }

    static void oddNumber(int k) {
        int i = 0;
        while (i <= k) {
            i = (i % 2 == 0 ? i + 1: i);
            System.out.print(i+" ");
            i = i+2;
        }
    }
}
