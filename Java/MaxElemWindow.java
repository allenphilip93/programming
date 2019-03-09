import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;

public class MaxElemWindow {

    public static int[] execute(int[] input, int win) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[input.length - win + 1];
        for (int i=0; i<win; i++){
            while (!deque.isEmpty() && input[deque.peekLast()] <= input[i])
                deque.pollLast();
            deque.offerLast(i);
        }
        for (int i=win; i < input.length; i++) {
            res[i-win] = input[deque.peekFirst()];
            if (deque.peekFirst() <= (i - win)) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && input[deque.peekLast()] <= input[i])
                deque.pollLast();
            deque.offerLast(i);
        }
        res[input.length-win] = input[deque.peekFirst()];
        return res;
    }

    private static void print(Deque<Integer> deque) {
        String s = "First - ";
        Iterator<Integer> iter = deque.iterator();
        while (iter.hasNext()) {
            s = s + iter.next() + " - ";
        }
        s = s + "Last";
        System.out.println(s);
    }

    public static void main(String[] args) {
        // int[] input = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int[] input = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        int[] res = execute(input, 4);
        System.out.println("Max values : " + Arrays.toString(res));
    }
}