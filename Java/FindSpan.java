import java.util.Arrays;

public class FindSpan {

    public static void findSpansWithStack(int[] arr) {
        LinkedListStack.Stack<Integer> stack = new LinkedListStack.Stack<>();
        int[] span = new int[arr.length];
        for (int idx = 0; idx < arr.length; idx++) {
            if (stack.size == 0 || arr[stack.peek()] > arr[idx]) {
                span[idx] = 1;
                stack.push(idx);
            } else {
                while (stack.size > 0 && arr[stack.peek()] <= arr[idx]) {
                    stack.pop();
                }
                int endIdx = (stack.peek() != null) ? stack.peek(): 0;
                span[idx] = idx - endIdx;
                stack.push(idx);
            }
        }
        System.out.println("Span Array : " + Arrays.toString(span));
    }
    public static void main(String[] args) {
        // int[] input = {6, 3, 4, 5, 2};
        int[] input = {100, 80, 60, 70, 60, 75, 85};
        findSpansWithStack(input);
    }
}