public class DynamicArrayStack {

    public static class Stack {
        int[] array;
        int size;
        int defaultSize = 1;
        int top = -1;

        public Stack() {
            array = new int[defaultSize];
            size = defaultSize;
        }

        private void expand() {
            if (top == (size - 1)) {
                System.out.println("Top : " + top + " | Old Size : " + size + " | New Size : " + size*2);
                size = size * 2;
                int[] newArray = new int[size];
                for (int idx = 0; idx < array.length; idx++) {
                    newArray[idx] = array[idx];
                }
                array = newArray;
            } else {
                System.out.println("No need to expand the array!");
            }
        }

        private void shrink() {
            if (top < size/2 && size > 1) {
                System.out.println("Top : " + top + " | Old Size : " + size + " | New Size : " + size/2);
                size = size/2;
                int[] newArray = new int[size];
                for (int idx = 0; idx < newArray.length; idx++) {
                    newArray[idx] = array[idx];
                }
                array = newArray;
            } else {
                System.out.println("No need to shrink the array!");
            }
        }

        public void pop() {
            if (top < 0) {
                System.out.println("Stack is empty!");
            } else {
                int elem = array[top];
                top = top - 1;
                System.out.println("Element of value " + elem + " has been popped!");
                if (top < size/2) {
                    shrink();
                }
            }
        }

        public void push(int elem) {
            if (top < (size - 1)) {
                top = top + 1;
                array[top] = elem;
                System.out.println("Element of value " + elem + " has been added!");
            } else {
                System.out.println("Stack is full, attempting to expand");
                expand();
                push(elem);
            }
        }

        public void print() {
            if (top < 0) {
                System.out.println("Stack is empty!");
            } else {
                int idx = 0;
                String s = "TOP => ";
                while (idx <= top) {
                    s = s + array[idx] + " => ";
                    idx = idx + 1;
                }
                s = s + "END";
                System.out.println(s);
            }
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.print();
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.print();
        stack.push(8);
        stack.push(9);
        stack.print();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.print();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.print();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.print();
    }


}