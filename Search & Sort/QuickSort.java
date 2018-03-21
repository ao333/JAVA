/* public class QuickSort {

    public static void main(String[] args) {
        int[] a = {5,3,1,9,7};
        System.out.print(QuickSort(a,a[0],a.length-1,9));
    }

    public static int countPairs(int k, vector<int> a) {
        int count = 0;
        if(a.size()==0) return 0;

        quick_sort(a, 0, static_cast<int>(a.size()-1));
        int left_arrow = 0;
        int right_arrow = static_cast<int>(a.size()-1);
        do {
        while(a[left_arrow]+a[right_arrow]!=k) {
        if(right_arrow == left_arrow)
        break;
        right_arrow--;
        }
        if(right_arrow == left_arrow)
        break;
        if(a[left_arrow]==a[right_arrow]) {
        if(right_arrow-left_arrow ==1)
        count+=1;
        else
        count+=(right_arrow-left_arrow+1)*(right_arrow-left_arrow)/2;
        break;
        }
        count++;
        left_arrow++;
        }
        while (right_arrow != left_arrow);
        return count;
        }

        long countSteps(int n) {
        long n_1 =1, n_2 =2, n_3 =4, n_4 = 0;
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        for (int index = 4; index <= n; index++) {
        n_4 = n_1 + n_2 + n_3;
        n_1 = n_2;
        n_2 = n_3;
        n_3 = n_4;
        }
        return n_3;
        }
        }
        */