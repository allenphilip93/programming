public class QueueCircularArray {
    int[] arr;
    int defaultSize = 5;
    int front = -1;
    int rear = -1;

    public QueueCircularArray() {
        arr = new int[defaultSize];
    }

    public QueueCircularArray(int size) {
        arr = new int[size];
    }

    public void enqueue(int elem) {
        if (rear == -1 && front == -1) {
            front++;
            rear++;
            arr[rear] = elem;
        } else if (rear >= front) {
            if ((rear - front) == (arr.length - 1)) {
                System.out.println("Queue is full!");
            } else {
                rear++;
                rear = (rear == arr.length) ? 0 : rear;
                arr[rear] = elem;
            }
        } else if (rear < front) {
            if ((front - rear) == 1) {
                System.out.println("Queue is full");
            } else {
                rear++;
                arr[rear] = elem;
            }
        }
    }

    public int dequeue() {
        int elem = -1;
        if (rear == -1 && front == -1) {
            System.out.println("Queue is empty!");
        } else if (front == rear) {
            elem = arr[front];
            front = -1;
            rear = -1;
        } else if (front < rear) {
            elem = arr[front];
            front++;
        } else if (front > rear) {
            elem = arr[front];
            front++;
            front = (front == arr.length) ? 0 : front;
        }
        return elem;
    }

    public int peek() {
        if (front == -1 && rear == -1) {
            System.out.println("Queue is empty!");
            return -1;
        } else {
            return arr[front];
        }
    }

    public void print() {
        if (front == -1 && rear == -1) {
            System.out.println("Queue is empty!");
        } else {
            String s = "Front => ";
            int idx = front;
            while (idx != rear) {
                s = s + arr[idx] + " => ";
                idx++;
                if (idx == arr.length) {
                    idx = 0;
                } 
            }
            s = s + arr[rear] + " => Tail";
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        QueueCircularArray queue = new QueueCircularArray(7);
        queue.enqueue(1);
        queue.print();
        queue.enqueue(2);
        queue.print();
        queue.enqueue(3);
        queue.print();
        queue.enqueue(4);
        queue.print();
        queue.enqueue(5);
        queue.print();
        queue.enqueue(6);
        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.print();
        queue.dequeue();
        queue.print();
        queue.enqueue(1);
        queue.print();
        queue.enqueue(2);
        queue.print();
        queue.enqueue(3);
        queue.print();
        queue.enqueue(4);
        queue.print();
        queue.enqueue(5);
        queue.print();
        queue.enqueue(6);
    }
}