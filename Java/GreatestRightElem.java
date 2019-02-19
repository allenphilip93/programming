import java.util.Arrays;

public class GreatestRightElem {

    public static void greaterRightElem(int[] arr) {
        LinkedListStack.Stack<Integer> stack = new LinkedListStack.Stack<>();
        int[] greatestArr = new int[arr.length];
        int idx = arr.length -1;
        while(idx >= 0) {
            if (stack.size == 0) {
                stack.push(arr[idx]);
                greatestArr[idx] = -1;
            } else if (stack.peek() >= arr[idx]) {
                greatestArr[idx] = stack.peek();
                stack.push(arr[idx]);
            } else {
                while (stack.size > 0 && stack.peek() < arr[idx]) {
                    stack.pop();
                }
                if (stack.size == 0) {
                    greatestArr[idx] = -1;
                } else {
                    greatestArr[idx] = stack.peek();
                }
                stack.push(arr[idx]);
            }
            idx--;
        }
        System.out.println("Outputs the nearest element greater than the ith index");
        System.out.println("Output Array : " + Arrays.toString(greatestArr));
    }

    public static void greatestRightElem(int[] arr) {
        int[] greatestArr = new int[arr.length];
        Integer max = -1;
        for (int idx=arr.length-1; idx >= 0; idx--) {
            if (arr[idx] > max) {
                max = arr[idx];
            }
            greatestArr[idx] = max;
        }
        System.out.println("Outputs the greatest element on the right");
        System.out.println("Output Array : " + Arrays.toString(greatestArr));
    }

    public static void main(String[] args) {
        int[] arr = {1,2,9,6,2,7,4,3};
        System.out.println("Input Array : " + Arrays.toString(arr));
        greaterRightElem(arr);
        greatestRightElem(arr);
    }
}