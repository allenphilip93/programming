public class QueueWithStacks<T> {
    
    public static class Queue<T> {
        LinkedListStack.Stack<T> stack = new LinkedListStack.Stack<T>();

        public void enqueue(T elem) {
            // add last
            pushLast(elem);
            // System.out.println("Pushed in an element of value " + elem.toString());
            // print();
        }

        private void pushLast(T elem) {
            T stackElem = stack.pop();
            if (stackElem == null) {
                stack.push(elem);
                return;
            }
            pushLast(elem);
            stack.push(stackElem);
        }

        public T dequeue() {
            T elem = stack.pop();
            if (elem != null) {
                // System.out.println("Dequeued an element of value " + elem.toString());
                return elem;
            } else {
                // System.out.println("Queue is empty!");
                return null;
            }
        }

        public void print() {
            // print as always
            LinkedListStack.Node<T> node = stack.top;
            String s = "TOP";
            while (node != null) {
                s = s + " => " + node.data.toString();
                node = node.next;
            }
            s = s + " => " + "END";
            System.out.println(s);
        }
    }

    public static class QueueAlt<T> {
        LinkedListStack.Stack<T> inbox = new LinkedListStack.Stack<T>();
        LinkedListStack.Stack<T> outbox = new LinkedListStack.Stack<T>();

        public void enqueue(T elem) {
            // dump into inbox
            inbox.push(elem);
            System.out.println("Pushed in a element of value " + elem.toString());
        }

        public T dequeue() {
            T elem = outbox.pop();
            if (elem == null) {
                // maybe elements in inbox
                elem = inbox.pop();
                if (elem != null) {
                    // move inbox to outbox
                    while (elem != null) {
                        outbox.push(elem);
                        elem = inbox.pop();
                    }
                    elem = outbox.pop();
                } else {
                    System.out.println("Queue is empty!");
                    return null;
                }
            }
            System.out.println("Popped an element of value " + elem.toString());
            return elem;
        }

        public void print() {
            // print as always
            LinkedListStack.Node<T> node = outbox.top;
            String s = "START";
            // normal print outbox
            while (node != null) {
                s = s + " => " + node.data.toString();
                node = node.next;
            }
            // reverse print inbox
            node = inbox.top;
            s = s + reversePrint(node) + " => " + "END";
            System.out.println(s);
        }

        private String reversePrint(LinkedListStack.Node<T> node) {
            if (node == null)
                return "";
            T elem = node.data;
            String s = reversePrint(node.next);
            s = s + " => " + elem.toString();
            return s;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.print();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        QueueAlt<Integer> queue2 = new QueueAlt<>();
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue2.dequeue();
        queue2.enqueue(4);
        queue2.enqueue(5);
        queue2.enqueue(7);
        queue2.enqueue(6);
        queue2.dequeue();
        queue2.print();
        queue2.dequeue();
        queue2.dequeue();
        queue2.enqueue(5);
        queue2.enqueue(5);
        queue2.enqueue(5);
        queue2.print();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();
        queue2.dequeue();

    }
}