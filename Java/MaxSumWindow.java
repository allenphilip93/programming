import java.util.Arrays;

public class MaxSumWindow {

    public static void maxSumWindow(int[] arr, int winSize) {
        int maxSum = 0;
        int sum = 0;
        int leftIdx = 0;
        int rightIdx = 0;
        while (leftIdx < arr.length && rightIdx < arr.length) {
            if (rightIdx < winSize) {
                sum = sum + arr[rightIdx];
                rightIdx++;
            } else if (leftIdx > (arr.length - winSize)) {
                sum = sum - arr[leftIdx];
                leftIdx++;
            } else {
                sum = sum - arr[leftIdx] + arr[rightIdx];
                leftIdx++;
                rightIdx++;
            }
            if (sum > maxSum)
                maxSum = sum;
        }
        System.out.println("Max Sum Window : " + maxSum);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 7, -8};
        System.out.println("Input Array : " + Arrays.toString(arr));
        int winSize = 3;
        maxSumWindow(arr, winSize);
    }
}