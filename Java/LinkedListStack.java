public class LinkedListStack {

    public static class Node <T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static class Stack<T> {
        Node<T> top;
        int size = 0;

        public void push(T elem) {
            Node<T> node = new Node<>(elem);
            if (top == null) {
                top = node;
                top.next = null;
            } else {
                node.next = top;
                top = node;    
            }
            size++;
            // System.out.println("Added an element of value " + elem);
        }

        public T pop() {
            if (top == null) {
                // System.out.println("Stack is empty!");
                return null;
            } else {
                T elem = top.data;
                top = top.next;
                size--;
                // System.out.println("Popped an element of value " + elem);
                return elem;
            }
        }

        public T peek() {
            if (top == null) {
                return null;
            } else {
                return top.data;
            }
        }

        public void print() {
            if (top == null) {
                System.out.println("Stack is empty!");
            } else {
                String s = "TOP => ";
                Node<T> idx = top;
                while (idx != null) {
                    s = s + idx.data.toString() + " => ";
                    idx = idx.next;
                }
                s = s + "END";
                System.out.println(s);
            }
        }

        public void reverse() {
            System.out.println("Before reversing");
            print();
            reverseRecurse();
            System.out.println("After reversing");
            print();
        }

        private void reverseRecurse() {
            T elem = pop();
            if (elem == null)
                return;
            reverseRecurse();
            pushLast(elem);
        }

        private void pushLast(T newElem) {
            T popElem = pop();
            if (popElem == null) {
                push(newElem);
                return;
            }
            pushLast(newElem);
            push(popElem);
        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("0");
        stack.push("1");
        stack.push("2");
        stack.print();
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.push("6");
        stack.push("7");
        stack.reverse();
        // stack.print();
        // stack.push("8");
        // stack.push("9");
        // stack.print();
        // stack.pop();
        // stack.pop();
        // stack.pop();
        // stack.print();
        // stack.pop();
        // stack.pop();
        // stack.pop();
        // stack.pop();
        // stack.pop();
        // stack.print();
        // stack.pop();
        // stack.pop();
        // stack.pop();
        // stack.pop();
        // stack.print();
    }
}