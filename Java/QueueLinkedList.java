public class QueueLinkedList <T>{
    private class Node {
        T data;
        Node next;
        public Node(T t) {
            this.data = t;
            this.next = null;
        }
    }

    Node front;
    Node tail;
    int size;

    public void enqueue(T elem) {
        Node node = new Node(elem);
        if (front == null && tail == null) {
            front = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public T dequeue() {
        T elem = null;
        if (front == null && tail == null) {
            System.out.println("Queue is empty!");
        } else if (front == tail) {
            elem = front.data;
            front = null;
            tail = null;
            size--;
        } else {
            elem = front.data;
            front = front.next;
            size--;
        }
        return elem;
    }

    public T peek() {
        T elem = null;
        if (front == null && tail == null) {
            System.out.println("Queue is empty!");
        } else {
            elem = front.data;
        }
        return elem;
    }

    public void print() {
        Node parse = front;
        if (front == null && tail == null) {
            System.out.println("Queue is empty!");
        } else {
            String s = "Front => ";
            while (parse != tail) {
                s = s + ((parse.data == null) ? "null" : parse.data.toString()) + " => ";
                parse = parse.next;
            }
            s = s + ((tail.data == null) ? "null" : tail.data.toString()) + " => Tail";
            System.out.println(s);
        }
    }

    public void reverse() {
        if (front == null && tail == null) {
            return;
        }
        T elem = dequeue();
        reverse();
        enqueue(elem);
    }

    public void reverse(int k) {
        LinkedListStack.Stack<T> stack = new LinkedListStack.Stack<>();
        for (int i=0; i < k; i++) {
            stack.push(dequeue());
        }
        while (stack.size > 0) {
            enqueue(stack.pop());
        }
        for (int i=0; i < (size - k); i++) {
            enqueue(dequeue());
        }
    }

    public void fold() {
        LinkedListStack.Stack<T> stack = new LinkedListStack.Stack<>();
        int halfSize = size/2;
        boolean isOdd = (size % 2 == 1);
        for (int i=0; i < halfSize; i++) {
            stack.push(dequeue());
        }
        if (isOdd)
            stack.push(dequeue());
        while (stack.size != 0) {
            enqueue(stack.pop());
        }
        for (int i=0; i < halfSize; i++) {
            enqueue(dequeue());
        }
        for (int i=0; i < halfSize; i++) {
            stack.push(dequeue());
        }
        if (isOdd)
            stack.push(dequeue());
        while (stack.size != 0) {
            enqueue(stack.pop());
            if (stack.size != 0 || !isOdd)
                enqueue(dequeue());
        }
        print();
    }

    public static void main(String[] args) {
        QueueLinkedList<Integer> queue = new QueueLinkedList<>();
        queue.enqueue(1);
        // queue.print();
        queue.enqueue(2);
        // queue.print();
        queue.enqueue(3);
        // queue.print();
        queue.enqueue(4);
        // queue.print();
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.print();
        System.out.println("Folding .... ");
        queue.fold();
        // queue.enqueue(6);
        // queue.print();
        // queue.fold();
        // System.out.println("Reversing.....");
        // queue.reverse();
        // queue.print();
        // queue.reverse(3);
        // queue.print();
        // queue.reverse(2);
        // queue.print();
        // queue.reverse(5);
        // queue.print();


        // queue.dequeue();
        // queue.print();
        // queue.dequeue();
        // queue.print();
        // queue.dequeue();
        // queue.print();
        // queue.dequeue();
        // queue.print();
        // queue.dequeue();
        // queue.print();
        // queue.dequeue();
        // queue.print();
        // queue.dequeue();
        // queue.print();
        // queue.enqueue(1);
        // queue.print();
        // queue.enqueue(2);
        // queue.print();
        // queue.enqueue(3);
        // queue.print();
        // queue.enqueue(4);
        // queue.print();
        // queue.enqueue(5);
        // queue.print();
        // queue.enqueue(6);
    }
}