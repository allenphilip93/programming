public class MinimumStack {

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static class Stack {
        Node top;
        int min;

        public void push(int elem) {
            Node node = new Node(elem);
            if (top == null) {
                top = node;
                top.next = null;
                min = top.data;
            } else {
                if (elem < min) {
                    node.data = 2 * elem - min;
                    min = elem;
                }
                node.next = top;
                top = node;
            }
            System.out.println("=======================================");
            System.out.println("Added an element of value " + elem);
            System.out.println("Minimum element of value " + min);
        }

        public int pop() {
            if (top == null) {
                // System.out.println("Stack is empty!");
                return 0;
            } else {
                int elem = top.data;
                if (elem < min) {
                    int res = min;
                    min = 2 * min - elem;
                    elem = res;
                }
                top = top.next;
                System.out.println("=======================================");
                System.out.println("Popped an element of value " + elem);
                System.out.println("Minimum element of value " + min);
                return elem;
            }
        }

        public void print() {
            if (top == null) {
                System.out.println("Stack is empty!");
            } else {
                String s = "TOP => ";
                Node idx = top;
                while (idx != null) {
                    s = s + idx.data + " => ";
                    idx = idx.next;
                }
                s = s + "END";
                System.out.println(s);
            }
        }

        public int getMinimum() {
            if (top == null) {
                return 0;
            } else {
                return min;
            }
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(0);
        stack.print();
        stack.push(-1);
        stack.push(-2);
        stack.push(-3);
        stack.push(-4);
        stack.print();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        
    }
}