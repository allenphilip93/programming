import java.util.Arrays;

public class Selection<T extends Number & Comparable<? super T>> extends Searching<T> {

    // Minimum number of comparison
    public void findSmallestAndLargest(Integer[] array) {
        int small = 9999999, large = -1;
        for (int index=0; index < array.length-1; index+=2) {
            if (array[index] < array[index+1]) {
                if (array[index] < small)
                    small = array[index];
                if (array[index+1] > large)
                    large = array[index];
            } else {
                if (array[index] > large) 
                    large = array[index];
                if (array[index+1] < small)
                    small = array[index+1];
            }
        }
        System.out.println("Smallest : " + small + " | Largest : " + large);
    }

    // O(n*k) algorithm
    public void kthSmallestBrute(Integer[] array, Integer k) {
        int min = 99999, prevMin = -1;
        for (int order=1; order <= k; order++) {
            min = 99999;
            for (int index=0; index < array.length; index++) {
                if (array[index] < min && array[index] > prevMin) {
                    min = array[index];
                }
            }
            prevMin = min;
            System.out.println("Minimum of order " + order + " : " + min);
        }
    }

    public void kthSmallestTreeSort(Integer[] array, Integer k) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int index=0; index < array.length; index++) {
            if (index < k) {
                bst.addNode(array[index]);
            } else {
                Integer elem = bst.removeLargestNode();    // logk
                if (elem > array[index])
                    bst.addNode(array[index]);  // logk
                else
                    bst.addNode(elem);          // logk
            }
            bst.print();
        }
        bst.print();
    }

    public void kthSmallestPartition(Integer[] array, Integer k) {
        int low = 0, high = array.length-1, pivotIndex = -1;
        while (low < high) {
            pivotIndex = partition(array, low, high);
            if (pivotIndex == (k-1)) {
                System.out.println("kth smallest element : " + array[pivotIndex]);
                return;
            } else if (pivotIndex >= k) {
                high = pivotIndex-1;
            } else {
                low = pivotIndex+1;
            }
        }
        System.out.println("kth smallest element : " + array[low]);
    }

    // returns the pivot index
    public int partition(Integer[] array, Integer startIdx, Integer endIdx) {
        // if (startIdx >= endIdx)
        //     return -1;
        // // int pivot = startIdx + (endIdx - startIdx)/2;
        // int pivot = startIdx;
        // int storeIdx = pivot + 1;
        // for (int index = pivot + 1; index <= endIdx; index++) {
        //     if (array[index].compareTo(array[pivot]) < 0 ) {
        //         swap(array, storeIdx, index);
        //         storeIdx++;
        //     }
        // }
        // swap(array, pivot, storeIdx-1);
        // pivot = storeIdx - 1;
        // return pivot;
        if (startIdx >= endIdx)
            return -1;
        int pivot = startIdx;
        int leftIdx = pivot+1, rightIdx = leftIdx+1;
        while (rightIdx <= endIdx) {
            if (array[rightIdx] > array[pivot]) {
                rightIdx++;
            } else if (array[leftIdx] < array[pivot]) {
                leftIdx++;
            } else {
                swap(array, leftIdx, rightIdx);
                leftIdx++;
                rightIdx++;
            }
        }
        swap(array, pivot, leftIdx-1);
        return leftIdx-1;
    }

    public void kthSmallestMedianOfMedians(Integer[] array, Integer k) {
        // To be implemented
    }

    public void findMedian(Integer[] arr1, Integer[] arr2) {
        int median = findMedian(arr1, 0, arr1.length-1, arr2, 0, arr2.length-1);
        System.out.println("Median : " + median);
    }

    private int findMedian(Integer[] arr1, Integer s1, Integer e1, Integer[] arr2, Integer s2, Integer e2) {
        System.out.println("A1 : (" + s1 + ", " + e1 + ") | A2 : (" + s2 + ", " + e2 + ")");
        try { Thread.sleep(1000); } catch(Exception e) {};
        int mid1 = (s1 + e1) >>> 1;
        int mid2 = (s2 + e2) >>> 1;
        int m1 = arr1[mid1];
        int m2 = arr2[mid2];
        if (m1 == m2) {
            return m1;
        } else if (s1 == e1 && s2 == e2) {
            return (m1+m2) >>> 1;
        } else if (e1 - s1 == 1) {
            return (arr1[s1] + arr2[s2] + arr1[e1] + arr2[e2] - Math.min(arr1[s1], arr2[s2]) - Math.max(arr1[e1], arr2[e2])) >>> 1;
        } else {
            if (m1 > m2) {
                if ((s1 + e1) % 2 == 1)
                    return findMedian(arr1, s1, mid1+1, arr2, mid2, e2);
                return findMedian(arr1, s1, mid1, arr2, mid2, e2);
            } else {
                if ((s2 + e2) % 2 == 1)
                    return findMedian(arr1, mid1, e1, arr2, s2, mid2+1);
                return findMedian(arr1, mid1, e1, arr2, s2, mid2);
            }
        }
    }

    // O(logm + logn)
    public void findMedianInequalArrays(Integer[] arr1, Integer[] arr2) {
        int medianPos = ((arr1.length + arr2.length) >>> 1) + (arr1.length + arr2.length)%2;
        System.out.println("Checking array 1 ...");
        int median = findMinimum(arr1, 0, arr1.length-1, arr2, medianPos);
        if (median == -1) {
            System.out.println("Checking array 2 ...");
            median = findMinimum(arr2, 0, arr2.length-1, arr1, medianPos);
            median = arr2[median];
        } else {
            median = arr1[median];
        }
        System.out.println("Median : " + median);
    }

    private int findMinimum(Integer[] arr1, Integer s1, Integer e1, Integer[] arr2, Integer minPos) {
        int guessIdx = (s1 + e1) >>> 1;
        int checkIdx = (minPos - guessIdx - 2);
        System.out.println("Bounds : (" + s1 + ", " + e1 + ") | Min Pos : " + minPos + " | GuessIdx : " + guessIdx + " | CheckIdx : " + checkIdx);
        try { Thread.sleep(1000); } catch(Exception e) {};
        if (s1 > e1) {
            return -1;
        }
        if (checkIdx >= -1 && checkIdx <= arr2.length-1 
            && (checkIdx == -1 || arr2[checkIdx] < arr1[guessIdx])
            && (checkIdx == arr2.length-1 || arr2[checkIdx+1] > arr1[guessIdx])) {
            return guessIdx;
        } else if (checkIdx < arr2.length-1 && (checkIdx < 0 || arr1[guessIdx] >  arr2[checkIdx])) {
            return findMinimum(arr1, s1, guessIdx-1, arr2, minPos);
        } else {
            return findMinimum(arr1, guessIdx+1, e1, arr2, minPos);
        }
    }

    // O(logm + logn)
    public void kthSmallest(Integer[] arr1, Integer[] arr2, int k) {
        int kthsmallest = findMinimum(arr1, 0, arr1.length-1, arr2, k);
        System.out.println("Checking array 1 ...");
        if (kthsmallest == -1) {
            System.out.println("Checking array 2 ...");
            kthsmallest = findMinimum(arr2, 0, arr2.length-1, arr1, k);
            kthsmallest = arr2[kthsmallest];
        } else {
            kthsmallest = arr1[kthsmallest];
        }
        System.out.println(k + "th smallest element is : " + kthsmallest);
    }

    public static void main(String[] args) {
        Selection<Integer> selector = new Selection<>();
        // Integer[] array = new Integer[] {3, 2, 4, 3, 4, 2, 6, 4, 6, 4, 1};
        // Integer[] array = new Integer[] {4, 6, 1, 5, 1, 7, 1, 3};
        // Integer[] array = new Integer[] {8, 2, 10, 1, 4, 8, 7};
        // Integer[] array = new Integer[] {1, 60, -10, 70, 80, -85, 2};
        // Integer[] array = new Integer[] {-60, -25, 7, 80, 99, 60, 12, 0, -27};
        // Integer[] array = new Integer[] {10};
        // Integer[] array = new Integer[] {9,7,2,8,5,6,3,4};
        // Integer[] array = new Integer[] {1, 4, 1, 6, 1, 1, 1, 6, 6};
        // Integer[] array = new Integer[] {1, 0, 1, 0, 1, 1, 1, 0, 0};
        // Integer[] array = new Integer[] {1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8};
        // Integer[] array = new Integer[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};

        // selector.findSmallestAndLargest(array);
        // selector.kthSmallestPartition(array, 5);
        // selector.kthSmallestTreeSort(array, 5);
        // selector.kthSmallestPartition(array, 5);
        // selector.partition(array, 0, array.length-1);
        // selector.findMedian(new Integer[] {1, 2, 3, 6, 7, 11}, new Integer[] {4, 5, 8, 9, 10, 12});
        // selector.findMedianInequalArrays(new Integer[] {1, 2, 3, 4, 5, 6, 8, 9}, new Integer[] {0, 4, 7, 10, 12, 14, 15});
        // selector.kthSmallest(new Integer[] {1, 2, 3, 4, 5, 6, 8, 9}, new Integer[] {0, 7, 10, 12, 14, 15}, 4);
    }
}