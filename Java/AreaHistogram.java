import java.util.Arrays;

public class AreaHistogram {

    public static int getMaxArea(int[] arr) {
        int maxArea = -1;
        LinkedListStack.Stack<Integer> stack = new LinkedListStack.Stack<>();
        for (int idx=0; idx < arr.length; idx++) {
            if (stack.size == 0) {
                stack.push(idx);
            } else if (arr[stack.peek()] <= arr[idx]) {
                stack.push(idx);
            } else {
                // pop and find max areas
                // right - idx
                // curr - pop
                // left - peek
                while (stack.size > 0 && arr[stack.peek()] > arr[idx]) {
                    int curr = stack.pop();
                    int left = (stack.peek() != null) ? stack.peek() + 1 : 0;
                    int area = (idx - left) * arr[curr];
                    if (area > maxArea)
                        maxArea = area;
                }
                stack.push(idx);
            }
        }
        while (stack.size > 0) {
            int curr = stack.pop();
            int left = (stack.peek() != null) ? stack.peek() + 1 : 0;
            int right = arr.length;
            int area = (right - left) * arr[curr];
            if (area > maxArea)
                maxArea = area;
        }
        System.out.println("Max Area : " + maxArea);
        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = {6, 2, 5, 4, 5, 1, 6};
        System.out.println("Input Array : " + Arrays.toString(arr));
        getMaxArea(arr);
    }
}