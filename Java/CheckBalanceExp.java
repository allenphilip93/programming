import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CheckBalanceExp {

    public static void check(String input){
        LinkedListStack.Stack<String> stack = new LinkedListStack.Stack<>();
        for (int idx=0; idx < input.length(); idx++) {
            if (input.charAt(idx) == '{' || input.charAt(idx) == '(' || input.charAt(idx) == '[') {
                stack.push(input.charAt(idx) + "");
            } else if (input.charAt(idx) == '}' || input.charAt(idx) == ')' || input.charAt(idx) == ']') {
                String elem = stack.pop();
                if ((elem.charAt(0) == '{' && input.charAt(idx) == '}') || 
                    (elem.charAt(0) == '[' && input.charAt(idx) == ']') || 
                    (elem.charAt(0) == '(' && input.charAt(idx) == ')')) {
                    continue;
                } else {
                    System.out.println("Expression is not balanced!");
                    return;
                }
            }
        }
        System.out.println("Expression is balanced!");
    }

    public static void main(String[] args) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the expression : ");
        try {
            String input = buff.readLine();
            check(input);
        } catch (IOException e) {
            System.out.println("Exception occurred!");
        }
    }
}