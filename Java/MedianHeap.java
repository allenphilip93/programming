public class MedianHeap<T extends Number & Comparable<? super T>> {
    Double median;
    Heap<T> minheap;
    Heap<T> maxheap;

    public MedianHeap(T[] minsource, T[] maxsource) {
        minheap = new Heap<>(minsource, null, true);
        maxheap = new Heap<>(maxsource, null, false);
    }

    public void enqueue(T elem) {
        if (median == null) {
            minheap.enqueue(elem);
            median = minheap.peek().doubleValue();
        } else {
            if (elem.doubleValue() < median) {
                if (minheap.size - maxheap.size == 0)
                    minheap.enqueue(maxheap.dequeue());
                maxheap.enqueue(elem);
            } else if (elem.doubleValue() >= median) {
                if (minheap.size - maxheap.size == 1) {
                    maxheap.enqueue(minheap.dequeue());
                }
                minheap.enqueue(elem);
            }
            if (minheap.size - maxheap.size == 1) {
                median = minheap.peek().doubleValue();
            } else {
                median = (minheap.peek().doubleValue() + maxheap.peek().doubleValue()) / 2.;
            }
        }
    }

    public void print() {
        System.out.println("Min heap");
        minheap.print();
        System.out.println("Max heap");
        maxheap.print();
        System.out.println("Median : " + median);
        System.out.println("---------------------------------------");
    }

    public static void main(String[] args) {
        MedianHeap<Integer> heap = new MedianHeap<>(new Integer[100], new Integer[100]);
        heap.enqueue(1);
        heap.enqueue(2);
        heap.enqueue(3);
        heap.enqueue(4);
        heap.enqueue(4);
        heap.enqueue(5);
        heap.enqueue(3);
        heap.enqueue(1);
        heap.enqueue(6);
        heap.enqueue(7);
        heap.enqueue(2);
        heap.enqueue(8);
        heap.enqueue(1);
        heap.print();
    }
}