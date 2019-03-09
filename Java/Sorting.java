import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Sorting<T extends Number & Comparable<? super T>> {

    /**
     * Num Comparisons : N^2/2
     * Num Swaps : N^2/2
     * Worst Case : O(n^2)
     * Best Case : O(n)
     * Stable : Yes 
     */
    public void bubbleSort(T[] array) {
        boolean hasSwaps = true;
        for (int outerIdx = 0; outerIdx < array.length && hasSwaps; outerIdx++) {
            hasSwaps = false;
            for (int innerIdx = 0; innerIdx < (array.length - outerIdx - 1); innerIdx++) {
                if (array[innerIdx].compareTo(array[innerIdx+1]) > 0) {
                    T elem = array[innerIdx+1];
                    array[innerIdx+1] = array[innerIdx];
                    array[innerIdx] = elem;
                    hasSwaps = true;
                }
            }
        }
        System.out.println("Bubble sorted array : " + Arrays.toString(array));
    }

    /**
     * Num Comparisons : N^2/2
     * Num Swaps : N
     * Worst Case : O(n^2)
     * Best Case : O(n^2)
     * Stable : Yes 
     */
    public void selectionSort(T[] array) {
        for (int outerIdx = 0; outerIdx < array.length; outerIdx++) {
            int minIdx = outerIdx;
            for (int innerIdx = outerIdx+1; innerIdx < array.length; innerIdx++) {
                if (array[minIdx].compareTo(array[innerIdx]) > 0) {
                    minIdx = innerIdx;
                }
            }
            if (minIdx != outerIdx) {
                T elem = array[outerIdx];
                array[outerIdx] = array[minIdx];
                array[minIdx] = elem;
            }
        }
        System.out.println("Selection sorted array : " + Arrays.toString(array));
    }
    
    /**
     * Num Comparisons : N^2/2
     * Num Shifts : N^2
     * Worst Case : O(n^2)
     * Best Case : O(n)
     * Stable : Yes 
     */
    public void insertionSort(T[] array) {
        for (int outerIdx = 0; outerIdx < array.length; outerIdx++) {
            int insertIdx = outerIdx;
            T insertElem = array[insertIdx];
            for (int innerIdx = outerIdx-1; innerIdx >= 0; innerIdx--) {
                if (array[innerIdx].compareTo(insertElem) > 0) {
                    array[innerIdx+1] = array[innerIdx]; 
                    insertIdx = innerIdx;
                } else {
                    break;
                }
            }
            array[insertIdx] = insertElem;
        }
        System.out.println("Insertion sorted array : " + Arrays.toString(array));
    }

    public void mergeSortTopDown(T[] array, T[] res) {
        mergeSortTopDown(array, res, 0, array.length-1);
        System.out.println("Merge sorted array : " + Arrays.toString(array));
    }

    public void mergeSortTopDown(T[] array, T[] res, int startIdx, int endIdx) {
        if (startIdx >= endIdx)
            return;
        int midIdx = startIdx + (endIdx - startIdx)/2;
        mergeSortTopDown(array, res, startIdx, midIdx);
        mergeSortTopDown(array, res, midIdx+1, endIdx);
        int leftIdx = startIdx;
        int rightIdx = midIdx+1;
        int resIdx = startIdx;
        while (leftIdx <= midIdx && rightIdx <= endIdx) {
            if (array[leftIdx].compareTo(array[rightIdx]) <= 0) {
                res[resIdx] = array[leftIdx];
                leftIdx++;
            } else {
                res[resIdx] = array[rightIdx];
                rightIdx++;
            }
            resIdx++;
        }
        while (leftIdx <= midIdx){
            res[resIdx] = array[leftIdx];
            resIdx++;
            leftIdx++;
        }
        while (rightIdx <= endIdx) {
            res[resIdx] = array[rightIdx];
            resIdx++;
            rightIdx++;
        }
        for (int index=startIdx; index <= endIdx; index++) {
            array[index] = res[index];
        }
    }

    public void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
        System.out.println("Quick sorted array : " + Arrays.toString(array));
    }

    private void quickSort(T[] array, int startIdx, int endIdx) {
        if (startIdx >= endIdx)
            return;
        // int pivot = startIdx + (endIdx - startIdx)/2;
        int pivot = startIdx;
        int storeIdx = pivot + 1;
        for (int index = pivot + 1; index <= endIdx; index++) {
            if (array[index].compareTo(array[pivot]) < 0 ) {
                swap(array, storeIdx, index);
                storeIdx++;
            }
        }
        swap(array, pivot, storeIdx-1);
        pivot = storeIdx - 1;
        quickSort(array, startIdx, pivot);
        quickSort(array, pivot + 1, endIdx);
    }

    private void swap(T[] array, int index1, int index2) {
        T elem = array[index1];
        array[index1] = array[index2];
        array[index2] = elem;
    }

    public void hasRepeatedNums(T[] array) {
        quickSort(array);
        boolean hasRepeatedNums = false;
        for (int index=1; index < array.length; index++) {
            if (array[index-1].compareTo(array[index]) == 0) {
                hasRepeatedNums = true;
                break;
            }
        }
        System.out.println("Has Repeated Numbers : " + hasRepeatedNums);
    }

    public void countRepetitions(T[] array) {
        quickSort(array);
        Map<T, Integer> repetitionsMap = new HashMap<>();
        repetitionsMap.put(array[0], 1);
        for (int index=1; index < array.length; index++) {
            if (array[index-1].compareTo(array[index]) == 0) {
                int count = repetitionsMap.get(array[index-1]);
                repetitionsMap.put(array[index-1], count+1);
            } else {
                repetitionsMap.put(array[index], 1);
            }
        }
        repetitionsMap.forEach((key, value) -> System.out.println("Element : " + key + " | Repetitions : " + value));
    }

    public void countRepetitions(T[] array, int[] reps) {
        for (int index=0; index < array.length; index++) {
            reps[array[index].intValue()]++; 
        }
        for (int index=0; index < reps.length; index++) {
            System.out.println("Element : " + index + " | Reps : " + reps[index]);
        }
    }

    public void radixSort(T[] array, int base) {
        Map<Integer, List<T>> radixMap = new TreeMap<>();
        int currBase = 1;
        while (currBase <= base) {
            for (int index=0; index < array.length; index++) {
                int rem = array[index].intValue() % (int) Math.pow(10, currBase);
                rem = rem / (int) Math.pow(10, currBase-1);
                if (!radixMap.containsKey(rem)) {
                    radixMap.put(rem, new ArrayList<>());
                }
                radixMap.get(rem).add(array[index]);
            }
            int index = 0;
            for (Integer key : radixMap.keySet()) {
                List<T> elemList = radixMap.get(key);
                while (elemList.size() > 0) {
                    array[index] = elemList.remove(0);
                    index++;
                }
            }
            radixMap = new TreeMap<>();
            currBase++;
            System.out.println("Curr Array State : " + Arrays.toString(array));
        }
        System.out.println("Radix Sorted Array : " + Arrays.toString(array));
    }

    public void twoSum(T[] array, T sum) {
        Map<T, List<Integer>> hashtable = new HashMap<>();
        for (int index=0; index < array.length; index++) {
            if (!hashtable.containsKey(array[index])) {
                hashtable.put(array[index], new ArrayList<>());
            }
            hashtable.get(array[index]).add(index);
        }
        boolean found = false;
        for (int index=0; index < array.length/2; index++) {
            List<Integer> res = hashtable.get((T) new Integer(sum.intValue()-array[index].intValue()));
            if (res != null && res.size() > 0) {
                for (int resIndex : res) {
                    if (resIndex != index) {
                        found = true;
                        System.out.println("A = " + array[index] + " | B = " + array[resIndex] + " | Sum = " + sum);
                    }
                }
            }
        }
        if (!found)
            System.out.println("No two elements add up to " + sum);
    }

    public void threeSum(T[] array, T sum) {
        Map<T, List<Integer>> hashtable = new HashMap<>();
        for (int index=0; index < array.length; index++) {
            if (!hashtable.containsKey(array[index])) {
                hashtable.put(array[index], new ArrayList<>());
            }
            hashtable.get(array[index]).add(index);
        }
        boolean found = false;
        for (int index=0; index < array.length; index++) {
            Integer subsum = new Integer(sum.intValue()-array[index].intValue());
            for (int innerIdx = index+1; innerIdx < array.length; innerIdx++) {
                List<Integer> res = hashtable.get((T) new Integer(subsum-array[innerIdx].intValue()));
                if (res != null && res.size() > 0 && index != innerIdx) {
                    for (int resIndex : res) {
                        if (resIndex != innerIdx && resIndex != index && resIndex > innerIdx) {
                            found = true;
                            System.out.println("A[" + index + "] = " + array[index] + " | B[" + innerIdx + "] = " + array[innerIdx] + " | C[" + resIndex + "] = " + array[resIndex] + " | Sum = " + sum);
                        }
                    }
                }
            }
        }
        if (!found)
            System.out.println("No three elements add up to " + sum);
    }

    public static void main(String[] args) {
        Sorting<Integer> sorter = new Sorting<>();
        Integer[] array = new Integer[] {6, 2, 2, 3, 1, 7, 4, 9, 5, 3, 6, 4, 9, 9};
        // Integer[] array = new Integer[] {30, 10, 23, 35, 29, 9};
        // Integer[] array = new Integer[] {6, 2, 3, 1};
        // sorter.bubbleSort(array);
        // sorter.selectionSort(array);
        // sorter.insertionSort(array);
        // sorter.mergeSortTopDown(array, new Integer[array.length]);
        // sorter.quickSort(array);
        // sorter.hasRepeatedNums(array);
        // sorter.countRepetitions(array);
        // sorter.countRepetitions(array, new int[10]);
        // sorter.radixSort(array, 2);
        sorter.twoSum(array, 10);
        sorter.threeSum(array, 10);
    }
}