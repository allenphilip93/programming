import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CheckPalindrome {

    public static void checkPalindrome(String input) {
        LinkedListStack.Stack<Character> stack = new LinkedListStack.Stack<>();
        for (int idx = 0; idx < input.length()/2; idx++) {
            stack.push(input.charAt(idx));
        }
        int startIdx = (input.length() % 2 == 0) ? input.length()/2 : input.length()/2 + 1;
        boolean isPalindrome = true;
        for (; startIdx < input.length(); startIdx++) {
            isPalindrome = isPalindrome & (stack.pop() == input.charAt(startIdx));
        }
        if (isPalindrome) {
            System.out.println("Yes, its a palindrome");;
        } else {
            System.out.println("No, its not a palindrome");;
        }
        System.out.println("---------------------------------");
    }
    public static void main(String[] args) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.print("Enter an expression : ");
                String input = buff.readLine();
                if ("exit".equals(input)) {
                    System.exit(0);
                }
                checkPalindrome(input);
            } catch (IOException e) {
                System.out.println("Error!!");
            }
        }
    }
}