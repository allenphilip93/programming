public class ArrayStack {

    public static class Stack {
        int size = 2;
        int[] array = new int[size];
        int top = -1;

        public Stack (int size) {
            this.size = size;
            array = new int[size];
            System.out.println("Created a stack of size " + size);
            top = -1;
        }

        public void push(int data) {
            // System.out.println("top : " + top + " | size : " + size);
            if (top < size-1) {
                top = top + 1;
                array[top] = data;
                System.out.println("Added element " + data);
            } else {
                System.out.println("Stack is out of memory!");
            }
        }

        public int pop() {
            if (top < 0) {
                System.out.println("Stack is empty!");
                return top;
            }
            int elem = array[top];
            top = top - 1;
            System.out.println("Popping element " + elem);
            print();
            return elem;
        }

        public void print() {
            if (top == -1) {
                System.out.println("Stack is empty!");
            } else {
                int iter = top;
                String s = "TOP => ";
                while (iter != -1) {
                    s = s + array[iter] + " => ";
                    iter = iter - 1;
                }
                s = s + "END";
                System.out.println(s);
            }
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack(2);
        stack.pop();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}