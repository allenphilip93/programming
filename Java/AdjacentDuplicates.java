import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AdjacentDuplicates {

    public static void wrongRemoveAdjacentDuplicates(String input) {
        String output = "";
        LinkedListStack.Stack<Character> stack = new LinkedListStack.Stack<>();
        int idx = 0;
        boolean similar = false;
        while(idx < input.length()) {
            if (stack.top == null) {
                stack.push(input.charAt(idx));
                similar = false;
                idx++;
            } else if (stack.top.data == input.charAt(idx)) {
                similar = true;
                idx++;
            } else if (stack.top.data != input.charAt(idx) && similar) {
                stack.pop();
                similar = false;
            } else {
                stack.push(input.charAt(idx));
                idx++;
            }
            stack.print();
        }
        output = getReverseString(stack.top);
        System.out.println("Input after removing adjacent duplicates : " + output);
        System.out.println("========================================");
    }

    private static String getReverseString(LinkedListStack.Node<Character> node) {
        if (node == null)
            return "";
        Character c = node.data;
        String s = getReverseString(node.next);
        return s + c;
    }

    public static void correctRemoveAdjacentDuplicates(String input) {
        boolean hasAdj = true;
        String output = "";
        while (hasAdj) {
            int idx = 0;
            hasAdj = false;
            output = "";
            while(idx < input.length()) {
                if (!checkChar(input, idx)) {
                    output = output + input.charAt(idx);
                } else {
                    hasAdj = true;
                }
                idx++;
            }
            input = output;
        }
        System.out.println("Result : " + output);
    }

    private static boolean checkChar(String input, int index) {
        if (input.length() <= 1)
            return false;
        if (index == 0) {
            return input.charAt(index) == input.charAt(index+1);
        } else if (index == input.length()-1) {
            return input.charAt(index-1) == input.charAt(index);
        } else {
            return input.charAt(index-1) == input.charAt(index) ||
                    input.charAt(index+1) == input.charAt(index);
        }
    }

    public static void main(String[] args) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                System.out.print("Enter a string : ");
                String input = buff.readLine();
                if ("0".equals(input))
                    System.exit(1);
                // wrongRemoveAdjacentDuplicates(input);
                correctRemoveAdjacentDuplicates(input);
            }
        } catch (Exception e) {
            System.out.println("Error!!");
        }
    }
}