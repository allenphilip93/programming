import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ConvertExp {

    public static void infixToPostfix(String exp) {
        System.out.println("Attempting to covert infix exp to postfix");
        LinkedListStack.Stack<Character> stack = new LinkedListStack.Stack<>();
        String expFinal = "";
        for (int idx=0; idx < exp.length(); idx++) {
            if (exp.charAt(idx) == '+' || exp.charAt(idx) == '-' ||
                exp.charAt(idx) == '*' || exp.charAt(idx) == '/' || 
                exp.charAt(idx) == '(') {
                // if operator, then
                    Character c = stack.pop();
                    if (c == null) {
                        // stack empty so add it
                        stack.push(exp.charAt(idx));
                    } else if (exp.charAt(idx) == '+' || exp.charAt(idx) == '-') {
                        // check for priority
                        while (c == '+' || c == '-' || c == '*' || c == '/') {
                            expFinal = expFinal + c + ' ';
                            c = stack.pop();
                            if (c == null)
                                break;
                        }
                        if (c != null)
                            stack.push(c);
                        stack.push(exp.charAt(idx));
                    } else if (exp.charAt(idx) == '*' || exp.charAt(idx) == '/') {
                        while (c == '*' || c == '/') {
                            expFinal = expFinal + c + ' ';
                            c = stack.pop();
                            if (c == null)
                                break;
                        }
                        if (c != null)
                            stack.push(c);
                        stack.push(exp.charAt(idx));
                    } else {
                        // push if start bracket
                        stack.push(c);
                        stack.push(exp.charAt(idx));
                    }
            } else if (exp.charAt(idx) == ')') {
                // pop until end bracket
                Character c = stack.pop();
                while (c != '(') {
                    expFinal = expFinal + c + ' ';
                    c = stack.pop();
                    if (c == null)
                        break;
                }
            } else if (exp.charAt(idx) == ' ') {
                continue;
            } else {
                // just add to exp if variable
                expFinal = expFinal + exp.charAt(idx) + ' ';
            }
        }
        // if stack is not empty, dump it to the final exp
        Character c = stack.pop();
        while (c != null) {
            expFinal = expFinal + c + ' ';
            c = stack.pop();
        }
        System.out.println("----------------------------");
        System.out.println(expFinal);
        System.out.println("----------------------------");
    }

    public static void infixToPrefix(String exp) {
        System.out.println("Attempting to covert infix exp to prefix");
        LinkedListStack.Stack<Character> stack = new LinkedListStack.Stack<>();
        String expFinal = "";
        for (int idx=exp.length()-1; idx >=0; idx--) {
            if (exp.charAt(idx) == '+' || exp.charAt(idx) == '-' ||
                exp.charAt(idx) == '*' || exp.charAt(idx) == '/' || 
                exp.charAt(idx) == ')') {
                // if operator, then
                    Character c = stack.pop();
                    if (c == null) {
                        // stack empty so add it
                        stack.push(exp.charAt(idx));
                    } else if (exp.charAt(idx) == '+' || exp.charAt(idx) == '-') {
                        // check for priority
                        while (c == '+' || c == '-' || c == '*' || c == '/') {
                            expFinal = c + " " + expFinal;
                            c = stack.pop();
                            if (c == null)
                                break;
                        }
                        if (c != null)
                            stack.push(c);
                        stack.push(exp.charAt(idx));
                    } else if (exp.charAt(idx) == '*' || exp.charAt(idx) == '/') {
                        while (c == '*' || c == '/') {
                            expFinal = c + " " + expFinal;
                            c = stack.pop();
                            if (c == null)
                                break;
                        }
                        if (c != null)
                            stack.push(c);
                        stack.push(exp.charAt(idx));
                    } else {
                        // push if start bracket
                        stack.push(c);
                        stack.push(exp.charAt(idx));
                    }
            } else if (exp.charAt(idx) == '(') {
                // pop until end bracket
                Character c = stack.pop();
                while (c != ')') {
                    expFinal = c + " " + expFinal;
                    c = stack.pop();
                    if (c == null)
                        break;
                }
            } else if (exp.charAt(idx) == ' ') {
                continue;
            } else {
                // just add to exp if variable
                expFinal = exp.charAt(idx) + " " + expFinal;
            }
        }
        // if stack is not empty, dump it to the final exp
        Character c = stack.pop();
        while (c != null) {
            expFinal = c + " " + expFinal;
            c = stack.pop();
        }
        System.out.println("----------------------------");
        System.out.println(expFinal);
        System.out.println("----------------------------");
    }

    public static void prefixToInfix(String exp) {
        System.out.println("Attempting to convert prefix to infix");
        LinkedListStack.Stack<String> stack = new LinkedListStack.Stack<>();
        for (int idx = exp.length()-1; idx >= 0; idx--) {
            if (exp.charAt(idx) == '+' || exp.charAt(idx) == '-' ||
                exp.charAt(idx) == '*' || exp.charAt(idx) == '/') {
                String c = stack.pop();
                c = "(" + c + " " + exp.charAt(idx) + " " + stack.pop() + ")";
                stack.push(c);
            } else if (exp.charAt(idx) != ' ') {
                // if its a variable, add to stack
                stack.push(exp.charAt(idx) + "");
            }
        }
        System.out.println("----------------------------");
        System.out.println(stack.pop());
        System.out.println("----------------------------");
    }

    public static void postfixToInfix(String exp) {
        System.out.println("Attempting to covert postfix to infix");
        LinkedListStack.Stack<String> stack = new LinkedListStack.Stack<>();
        for (int idx = 0; idx < exp.length(); idx++) {
            if (exp.charAt(idx) == '+' || exp.charAt(idx) == '-' ||
                exp.charAt(idx) == '*' || exp.charAt(idx) == '/') {
                String c = stack.pop();
                c = "(" + stack.pop() + " " + exp.charAt(idx) + " " + c + ")";
                stack.push(c);
            } else if (exp.charAt(idx) != ' ') {
                // if its a variable, add to stack
                stack.push(exp.charAt(idx) + "");
            }
        }
        System.out.println("----------------------------");
        System.out.println(stack.pop());
        System.out.println("----------------------------");
    }
    public static void main(String[] args) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter the expression : ");
            String input = buff.readLine();
            while (true) {
                try {
                    System.out.println("===========");
                    System.out.println("Select mode");
                    System.out.println("============");
                    System.out.println("1. Infix to Postfix");
                    System.out.println("2. Infix to Prefix");
                    System.out.println("3. Postfix to Infix");
                    System.out.println("4. Prefix to Infix");
                    System.out.println("5. Change Expression");
                    System.out.println("0. Exit");
                    System.out.print("Enter the mode : ");
                    int mode = Integer.valueOf(buff.readLine());
                    switch (mode) {
                        case 1:
                            infixToPostfix(input);
                            break;
                        case 2:
                            infixToPrefix(input);
                            break;
                        case 3:
                            postfixToInfix(input);
                            break;
                        case 4:
                            prefixToInfix(input);
                            break;
                        case 5:
                            System.out.print("Enter the expression : ");
                            input = buff.readLine();
                            break;
                        case 0 :
                            buff.close();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid, try again!");
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid value, try again!");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception occurred!");
        }
    }
}