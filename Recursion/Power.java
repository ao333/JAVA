public class Power {

    public static void main(String[] args) {
        int x = 2, n = 6;
        System.out.print(Pow(x,n));
    }

    static int Pow(int x, int n) {
        int tmp;

        if(n<0) return -1;
        else if(n==0) return 1;
        else if(n==1) return x;

        tmp = Pow(x,n/2);
        if(n%2==0) return tmp*tmp;
        return tmp*tmp*x;
    }
}
