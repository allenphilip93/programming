public class StackWithQueues {
    public static class Stack<T> {
        QueueWithStacks.Queue<T> queue = new QueueWithStacks.Queue<>();

        public void push(T elem) {
            queue.enqueue(elem);
            System.out.println("Pushed an element of value " + elem.toString());
        }

        public T pop() {
            reverse();
            T elem = queue.dequeue();
            if (elem != null)
                System.out.println("Popped an element of value " + elem.toString());
            else 
                System.out.println("Stack is empty!");
            reverse();
            return elem;
        }

        private void reverse() {
            T elem = queue.dequeue();
            if (elem == null)
                return;
            reverse();
            queue.enqueue(elem);
        }

        public void print() {
            reverse();
            queue.print();
            reverse();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.print();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }
}