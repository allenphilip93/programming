public class Heap <T extends Number & Comparable<? super T>> extends BinaryTree<T>{

    T[] array;
    int capacity;
    int size;
    boolean isMinHeap = true;

    public Heap(T[] source, T[] data, boolean isMinHeap) {
        this.array = source;
        this.capacity = source.length;
        size = 0;
        if (data != null && data.length > 0) {
            for (int idx=0; idx < data.length; idx++) {
                array[idx] = data[idx];
                size++;
            }
        }
        this.isMinHeap = isMinHeap;
    }

    private int heapCondition(int index1, int index2) {
        if ((index1 >= size && index2 >= size) || (index1 < 0 && index2 < 0))
            return -1;
        if (index1 < 0 || index1 >= size)
            return index2;
        if (index2 < 0 || index2 >= size)
            return index1;
        if (array[index1] == null || array[index2] == null)
            return -1;
        if (isMinHeap)
            return (array[index1].compareTo(array[index2]) <= 0) ? index1 : index2;
        else
            return (array[index1].compareTo(array[index2]) >= 0) ? index1 : index2;
    }

    private boolean heapCondition(T leftelem, T rightelem) {
        if (isMinHeap)
            return leftelem.compareTo(rightelem) <= 0;
        else
            return leftelem.compareTo(rightelem) >= 0;
    }

    public void enqueue(T elem) {
        if (size == capacity) {
            System.out.println("Heap is at max memory!");
        } else if (size == 0) {
            array[0] = elem;
            size++;
        } else {
            array[size] = elem;
            size++;
            percolateUp(size-1);
        }
    }

    private void percolateUp(int pos) {
        while (pos > 0 && size > 1) {
            int idx = (pos-1)/2;
            if (heapCondition(pos, idx) == pos) {
                T elem = array[pos];
                array[pos] = array[idx];
                array[idx] = elem;
                pos = idx;
            } else {
                break;
            }
        }
    }

    public T peek() {
        if (size > 0) {
            return array[0];
        }
        return null;
    }

    public T dequeue() {
        T res = null;
        if (size >= 1) {
            res = array[0];
            size--;
            array[0] = array[size];
            array[size] = null;
            if (size > 1) {
                percolateDown(0);
            }
            // System.out.println("Dequeuing element of value : " + res);
        } else if (size == 0) {
            System.out.println("Heap is empty!");
        }
        return res;
    }

    private void percolateDown(int pos) {
        while (pos < size && size > 1) {
            int idx = heapCondition(2*pos+1, 2*pos+2);
            if (idx != -1 && heapCondition(pos, idx) == idx) {
                T elem = array[pos];
                array[pos] = array[idx];
                array[idx] = elem;
                pos = idx;
            } else {
                break;
            }
        }
    }

    public void print() {
        if (size > 0) {
            String s = "";
            root = null;
            for (int idx=0; idx < size; idx++) {
                addNode(array[idx]);
                s = s + array[idx] + " ";
            }
            printTree();
            System.out.println("Array : " + s);
        } else {
            System.out.println("Heap is empty!");
        }
    }

    public int findElem(T elem) {
        if (size == 0) {
            System.out.println("Heap is empty!");
            return -1;
        } else {
            int idx = findElem(0, elem);
            if (idx == -1)
                System.out.println("Element of value " + elem + " not found!");
            else
                System.out.println("Element of value " + elem + " found at index " + idx);
            return idx;
        }
    }

    private int findElem(int pos, T elem) {
        int res = -1;
        if (pos >= 0 && pos < size) {
            if (array[pos].compareTo(elem) == 0)
                return pos;
            if (heapCondition(array[pos], elem)) {
                res = findElem(2*pos+1, elem);
                if (res == -1)
                    res = findElem(2*pos+2, elem);
            }
        }
        return res;
    }

    public void delete(int pos) {
        if (size == 0) {
            System.out.println("Heap is empty!");
        } else if (pos >= size) {
            System.out.println("Invalid position!");
        } else {
            size--;
            T elem = array[pos];
            array[pos] = array[size];
            array[size] = null;
            if (size > 1) {
                percolateDown(pos);
            }
            System.out.println("Element of value " + elem + " has been removed!");
        }
    }

    public void heapify() {
        int size = 0;
        // Build the size of the input array
        for (; size < capacity; size++) {
            if (array[size] == null)
                break;
        }
        this.size = size;

        // find the last non-leaf node pos
        int lastPos = (size-1)/2;
        for (; lastPos >= 0; lastPos--) {
            percolateDown(lastPos);
        }
    }

    // in place heapsort
    public T[] sort(T[] input) {
        // Build the heap params
        this.array = input;
        this.size = 0;
        this.capacity = input.length;

        isMinHeap = !isMinHeap;
        heapify();
        for (int idx=0; idx < input.length; idx++) {
            T elem = dequeue();
            array[size] = elem;
        }
        isMinHeap = !isMinHeap;

        String s = "";
        for (int idx=0; idx < input.length; idx++) {
            s = s + array[idx] + " ";
        }
        System.out.println("Sorted Array : " + s);
        return array;
    }

    public T findMax() {
        T elem = null;
        if (size == 0) {
            System.out.println("Heap is empty");
        } else {
            int leafIdx = (size + 1)/2;
            elem = array[leafIdx];
            for (; leafIdx < size; leafIdx++) {
                if (elem.compareTo(array[leafIdx]) < 0)
                    elem = array[leafIdx];
            }
            System.out.println("Max element in heap is : " + elem);
        }
        return elem;
    }

    public void findElemRange(T elem) {
        if (size == 0) {
            System.out.println("Heap is empty!");
        } else {
            String s = "";
            s = findElemRange(0, elem);
            System.out.println("Elements that fit the heap condition for " + elem + " : " + s);
        }
    }

    private String findElemRange(int pos, T elem) {
        if (pos >=0 && pos < size && heapCondition(array[pos], elem)) {
            return array[pos] + " " + findElemRange(2*pos+1, elem) + findElemRange(2*pos+2, elem);
        }
        return "";
    }

    public void findKthElem(int k, Heap<T> auxHeap) {
        if (size == 0) {
            System.out.println("Heap is empty!");
        } else if (auxHeap.size != 0) {
            System.out.println("Auxillary heap is not empty!");
        } else if (k > size) {
            System.out.println("Invalid k value passed!");
        } else {
            // we can just delete k times from heap and do it at O(klogn)
            // alternatively we can use an auxHeap and do at O(klogk) time 
            // and O(k) space
            T elem = null;
            auxHeap.enqueue(peek());
            for (int count=0; count < k; count++) {
                elem = auxHeap.dequeue();
                int pos = findElem(0, elem);
                pos = (pos == -1) ? 0 : pos;
                if ((2*pos+1) < size)
                    auxHeap.enqueue(array[2*pos + 1]);
                if ((2*pos+2) < size)                
                    auxHeap.enqueue(array[2*pos + 2]);
            }
            System.out.println(k + "th element of the heap is : " + elem);
            auxHeap.size = 0;
        }
    }

    public void merge(Heap<T> auxheap) {
        if (size == 0) {
            System.out.println("Heap is empty!");
        } else if (auxheap.size == 0) {
            System.out.println("Auxillary heap is empty so no change!");
        } else {
            for (int idx=0; idx < auxheap.size; idx++) {
                array[size] = auxheap.array[idx];
                size++;
            }
            heapify();
            print();
        }
    }


    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(new Integer[100], new Integer[]{1, 6, 5, 7, 11, 9, 8, 2}, false);
        Heap<Integer> auxHeap = new Heap<>(new Integer[100], new Integer[] {4, 3, 14, 10, 12, 11, 0}, false);
        // heap.sort(new Integer[]{1, 6, 5, 7, 11, 9, 8, 2});
        heap.heapify();
        auxHeap.heapify();
        heap.print();
        auxHeap.print();
        heap.merge(auxHeap);
        // heap.findMax();
        // heap.findElemRange(7);
        // heap.findKthElem(1, auxHeap);
        // heap.findKthElem(2, auxHeap);
        // heap.findKthElem(3, auxHeap);
        // heap.findKthElem(4, auxHeap);
        // heap.findKthElem(5, auxHeap);
        // heap.findKthElem(6, auxHeap);
        // heap.findKthElem(7, auxHeap);
        // heap.findKthElem(8, auxHeap);
        // heap.findKthElem(9, auxHeap);
        // Heap<Integer> heap = new Heap<>(new Integer[100], true);
        // heap.enqueue(1);
        // heap.enqueue(6);
        // heap.enqueue(5);
        // heap.enqueue(7);
        // heap.enqueue(11);
        // heap.enqueue(9);
        // heap.enqueue(8);
        // heap.enqueue(2);
        // heap.print();
        // heap.delete(heap.findElem(11));
        // heap.print();
        // heap.delete(heap.findElem(1));
        // heap.print();
        // heap.delete(heap.findElem(8));
        // heap.print();
        // heap.findElem(7);
        // heap.findElem(2);
        // heap.findElem(11);
        // heap.findElem(8);
        // heap.findElem(1);
        // heap.findElem(10);

        // heap.dequeue();
        // heap.dequeue();
        // heap.dequeue();
        // heap.dequeue();
        // heap.dequeue();
        // heap.dequeue();
        // heap.dequeue();
        // heap.dequeue();
        // heap.dequeue();
    }
}