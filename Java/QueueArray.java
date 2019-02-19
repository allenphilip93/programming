public class QueueArray {
    int[] arr;
    int defaultSize = 5;
    int front = 0;
    int rear = -1;
    
    public QueueArray() {
        arr = new int[defaultSize];
    }

    public QueueArray(int size) {
        arr = new int[size];
    }

    public void enqueue(int elem) {
        if (rear == arr.length - 1) {
            System.out.println("Queue is full!");
            return;
        }
        rear++;
        arr[rear] = elem;
    }

    public int dequeue() {
        int elem = -1;
        if (rear < 0) {
            System.out.println("Queue is empty!");
        } else {
            elem = arr[front];
            // shift all elems by 1 to left
            for (int i=1; i <= rear; i++) {
                arr[i-1] = arr[i];
            }
            rear--;
        }
        return elem;
    }

    public void print() {
        if (rear < 0) {
            System.out.println("Queue is empty!");
        } else {
            String s = "Front => ";
            for (int i = front; i <= rear; i++) {
                s = s + arr[i] + " => ";
            }
            s = s + "Tail";
            System.out.println(s);
        }
    }

    public int peek() {
        if (rear == -1) {
            System.out.println("Queue is empty!");
            return -1;
        } else {
            return arr[front];
        }
    }

    public static void main(String[] args) {
        QueueArray queue = new QueueArray();
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