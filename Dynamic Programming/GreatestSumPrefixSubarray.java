import java.io.*;

public class GreatestSumPrefixSubarray {
  public static void main(String[] args) {
    int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
    System.out.println("max contiguous sum is "+maxSubArraySum(a));
  }

  static int maxSubArraySum(int a[]){
    int size = a.length;
    int current_max=0, current_sum=0;

    for (int i=0; i<size; i++){
      current_sum = current_sum + a[i];
      if(current_max < current_sum)
        current_max = current_sum;
      if (current_sum < 0)
        current_sum = 0;
    }
    return current_max;
  }
}
