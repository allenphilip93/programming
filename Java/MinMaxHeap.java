public class MinMaxHeap <T extends Comparable<? super T>> {
    
    public class Node implements Comparable<Node> {
        T data;
        Node twin;
        public Node(T data) {
            this.data = data;
        }

        public int compareTo(Node node) {
            return data.compareTo(node.data);
        }
    }
    
    Heap<Node> minheap, maxheap;

    public MinMaxHeap(int capacity) {
        minheap = new Heap<Node>((Node[]) new Object[capacity], null, true);
        maxheap = new Heap<Node>((Node[]) new Object[capacity], null, false);
    }

    public void enqueue(T elem) {
        Node minnode = new Node(elem);
        Node maxnode = new Node(elem);
        minnode.twin = maxnode;
        maxnode.twin = minnode;
        minheap.enqueue(minnode);
        maxheap.enqueue(maxnode);
    }

    public T findMin() {
        return minheap.peek().data;
    }

    public T findMax() {
        return maxheap.peek().data;
    }

    public T removeMin() {
        Node minnode = minheap.dequeue();
        minnode.twin.data = maxheap.array[maxheap.size-1].data;
        maxheap.array[maxheap.size-1] = null;
        maxheap.size--;
        return minnode.data;
    }

    public T removeMax() {
        Node maxnode = maxheap.dequeue();
        maxnode.twin.data = minheap.array[minheap.size-1].data;
        minheap.array[minheap.size-1].data = null;
        minheap.size--;
        return maxnode.data;
    }

    public void print() {
        minheap.print();
        maxheap.print();
    }

    public static void main(String[] args) {
        MinMaxHeap<Integer> minmaxheap = new MinMaxHeap<>(100);
        minmaxheap.enqueue(1);
        minmaxheap.enqueue(3);
        minmaxheap.enqueue(5);
        minmaxheap.enqueue(2);
        minmaxheap.enqueue(9);
        minmaxheap.enqueue(7);
        minmaxheap.enqueue(10);
        minmaxheap.enqueue(8);
        minmaxheap.enqueue(4);
        minmaxheap.print();
    }
}