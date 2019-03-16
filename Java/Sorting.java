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

    public void quickSort(Integer[] array) {
        quickSort(array, 0, array.length - 1);
        System.out.println("Quick sorted array : " + Arrays.toString(array));
    }

    private void quickSort(Integer[] array, int startIdx, int endIdx) {
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

    public void swap(T[] array, int index1, int index2) {
        T elem = array[index1];
        array[index1] = array[index2];
        array[index2] = elem;
    }

    public void swap(Integer[] array, int index1, int index2) {
        Integer elem = array[index1];
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

    // Check if more than n/2 occur in an unsorted array in logn time
    public void hasMajority(T[] array) {
        int mid = array.length/2 + array.length%2;
        T elem = array[mid];
        int left = mid, leftMax = 0;
        while (left > leftMax) {
            int leftMid = (left + leftMax)/2;
            if (array[leftMid].compareTo(elem) != 0) {
                leftMax = leftMid + 1;
            } else {
                left = leftMid;
            }
        }
        if (array[leftMax].compareTo(elem) != 0) {
            left++;
        }
        int right = left + array.length/2;
        if (right < array.length && array[right].compareTo(elem) == 0) {
            System.out.println("LEFT : " + left + " | RIGHT : " + right + " | LENGTH : " + array.length);
            System.out.println("Majority element : " + elem);
        } else {
            System.out.println("No majority element exists!");
        }
    }

    public void findMaxOccuringElem(T[] array) {
        // nlogn
        quickSort(array);
        T elem = array[0];
        int maxCount = 1, count = 1, index = 0;
        do {
            if (array[index].compareTo(array[index+1]) == 0) {
                count++;
            } else {
                count = 1;
            }
            if (maxCount < count || index == array.length-2) {
                maxCount = count;
                elem = array[index];
            }
            index++;
        } while (index < array.length-1);
        System.out.println("Max Element : " + elem);
        // Alternatively we can use counting sort algorithm as well at O(n) time and O(1) space
    }

    // all elements in array, 0 <= array[i] < k <= n
    // Works fine must handle the collisions carefully though
    public void findMaxOccuringElem(Integer[] array, Integer k) {
        for(int idx=0; idx < array.length; idx++) {
            array[array[idx]%k] += k;
        }
        int max = array[0];
        for (int idx=1; idx < k; idx++) {
            if (max < array[idx])
                max = idx;
        }
        int numReps = array[max]/k;
        for (int idx=1; idx < k; idx++) {
            if (array[idx]/k == numReps)
                System.out.println("Element : " + idx + " | Number of repetitions : " + array[idx]/k);
        }
        // restoring the array
        for (int idx=0; idx < k; idx++) {
            array[idx] %= k;
        }
    }

    /*  If its like only 2 duplicates present
        - We can adding up 1,2,.. N and subtract from sum of elems in array,
          similarly do pdt of all elems / N! to get two equations in two
          variables - Solves in O(1) space and O(n) time
        - Alternatively we can use XOR of 1,2,.. N and all the elements in array
          to get X xor Y, then we can find the kth but they differ and repeat the
          process to find X and Y individually
        - Simplest approach is by modifying the array, which is done below
    */
    public void findDuplicates(Integer[] array) {
        String s = "";
        /*
        // Fails when there are > 2 reps since it will print repeated results
        for (int idx=0; idx < array.length; idx++) {
            if (array[Math.abs(array[idx])] > 0) {
                array[Math.abs(array[idx])] *= -1;
            } else {
                s = s + Math.abs(array[idx]) + " ";
            }
        }
        */
        int N = array.length;
        for (int idx=0; idx < N; idx++) {
            array[array[idx]%N] += N;
        }
        for (int idx=0; idx < N; idx++) {
            if (array[idx]/N > 1) {
                s = s + idx + " ";
            }
            array[idx] = array[idx] % N;
        }
        System.out.println("Duplicate Elements : " + s);
        // Restoring is simple, just take abs of all elements
    }

    public void mergeInplace(T[] arr1, T[] arr2) {

    }

    public void countFrequencies(Integer[] array) {
        int n = array.length;
        for (int idx=0; idx < n; idx++) {
            array[array[idx]%n] += n;
        }
        for (int idx=0; idx < n; idx++) {
            if (array[idx]/n > 1)
                System.out.println("Element : " + idx + " | Frequency : " + array[idx]/n);
            array[idx] %= n;
        }
    }

    // Divide and conquer soln - Fails for odd multiples of 2 greater than 3
    public void alternateElemShuffleDC(T[] array) {
        int n = array.length;
        alternateElemShuffleDC(array, 0, n-1);
        System.out.println("Shuffled Array : " + Arrays.toString(array));
    }

    private void alternateElemShuffleDC(T[] array, int start, int end) {
        System.out.println("-----------------------------------");
        System.out.println("START : " + start + " | END : " + end);
        // try {Thread.sleep(1000);} catch (Exception e) { }
        if ((end - start) <= 1)
            return;
        int mid = (end + start) >>> 1;
        int diff = (end - start)/4 + (end-start)%2;
        int Rstart = mid+1;
        int Lstart = start + diff;
        int Rend = mid + diff;
        int Lend = mid;
        System.out.println("Lend : " + Lend + " | Rend : " + Rend + " | Mid : " + mid + " | Diff : " + diff);
        while (Rstart <= Rend) {
            System.out.println("Swapping a[" + Lstart + "] and a[" + Rstart + "]");
            swap(array, Lstart, Rstart);
            if (Lstart > Lend)
                mid++;
            Rstart++;
            Lstart++;
        }
        System.out.println("Branching to (" + start + ", " + mid + ") and (" + (mid+1) + ", " + end + ")");
        System.out.println("ARRAY : " + Arrays.toString(array));
        alternateElemShuffleDC(array, start, mid);
        alternateElemShuffleDC(array, mid+1, end);
    }

    public void alternateElemShuffle(Integer[] array) {
        int rightMin = array.length/2;
        int rightStart = array.length - 2;
        while (rightStart >= rightMin) {
            int index = rightStart;
            int jumpIndex = (index < rightMin) ? 2*index : 2*index - array.length + 1;
            int val = array[index], count = 0;
            do {
                System.out.println("Assinging a[" + index + "] : " + val + " to a[" + jumpIndex + "] : " + array[jumpIndex]);
                int temp = array[jumpIndex];
                array[jumpIndex] = val;
                val = temp;
                index = jumpIndex;
                jumpIndex = (index < rightMin) ? 2*index : 2*index - array.length + 1;
                if (index >= rightMin)
                    count++;
            } while (index != rightStart);
            rightStart = rightStart - count;
            System.out.println("RIGHTSTART : " + rightStart + " | RIGHTMIN : " + rightMin);
        }
        System.out.println("Shuffled Array : " + Arrays.toString(array));
    }

    // Quicksort and alternate
    public void reArrangePositiveNegative(T[] array, T pivotElem) {
        int pivotIdx = 0;
        for (int index = 0; index < array.length; index++) {
            if (array[index].compareTo(pivotElem) < 0 ) {
                swap(array, pivotIdx, index);
                pivotIdx++;
            }
        }
        int posStart = pivotIdx, neg = 0;
        while (posStart < array.length && neg < array.length) {
            swap(array, neg, posStart);
            posStart++;
            neg+=2;
        }
        System.out.println("Array : " + Arrays.toString(array));
    }

    public void maxBitonicSubarray(T[] array) {
        int index = 0, startIndex = 0, nextIndex = 0, maxLength = 0;
        while (index < array.length -1) {
            // Ascent
            while (index < array.length-1 && array[index].compareTo(array[index+1]) <= 0)
                index++;
            // Descent
            while (index < array.length-1 && array[index].compareTo(array[index+1]) >= 0) {
                if (array[index].compareTo(array[index+1]) != 0)
                    nextIndex = index + 1;
                index++;
            }
            if ((index - startIndex + 1) > maxLength)
                maxLength = (index - startIndex + 1);
            startIndex = nextIndex;
        }
        if ((index - startIndex + 1) > maxLength)
                maxLength = (index - startIndex + 1);
        System.out.println("Max Bitonic Subarray Length : " + maxLength);
    }

    // Graph approach
    public void minSwapsToSort(Integer[] array) {
        int len = array.length;
        int count = 0;
        int i = 0;

        while (i < len) {
            // already sorted
            if (array[i] == i + 1) {
                i++;
                continue;
            }

            int swapPosition = array[i] - 1;
            int temp = array[i];
            array[i] = array[swapPosition];
            array[swapPosition] = temp;
            count++;
        }
        System.out.println("Minimum Swaps : " + count);
    }

    // array[i] -> array[array[i]] and all elements 0 <= elems < n
    public void convertToPosIndex(Integer[] array) {
        int n = array.length;
        for (int idx=0; idx < n; idx++) {
            array[idx] = array[idx] + (array[array[idx]] % n) * n;
        }
        for (int idx=0; idx < n; idx++) {
            array[idx] = array[idx]/n;
        }
        System.out.println("Coverted Array : " + Arrays.toString(array));
    }

    // arr1 is of size m+n with m elements and arr2 is array of size n with n elements
    public void merge(Integer[] arr1, Integer[] arr2) {
        int m = arr1.length, n = arr2.length;
        Integer[] arr = new Integer[m+n];
        for (int idx=0; idx < m; idx++) {
            arr[idx] = arr1[idx];
        }
        arr1 = arr;
        int end1 = m - 1, end2 = n - 1, end = m + n - 1;
        while (end1 >= 0 && end2 >= 0) {
            if (arr1[end1] > arr2[end2]) {
                arr1[end] = arr1[end1];
                end1--;
            } else {
                arr1[end] = arr2[end2];
                end2--;
            }
            end--;
        }
        while (end2 >= 0) {
            arr1[end] = arr2[end2];
            end--;
            end2--;
        }
        System.out.println("Merged Array : " + Arrays.toString(arr1));
    }

    // Rearrange to A < B > C < D > E < F > G < ..
    public void rearrangeToPeaks(T[] array) {
        quickSort(array);
        for (int index=2; index < array.length; index+=2) {
            swap(array, index-1, index);
        }
        System.out.println("Rearranged Array : " + Arrays.toString(array));
    }

    public static void main(String[] args) {
        Sorting<Integer> sorter = new Sorting<>();
        // Integer[] array = new Integer[] {-1, 2, -3, 4, 5, 6, -7, -8, 1, 2, 3};
        // Integer[] array = new Integer[] {3, 9, 2, 3, 9, 2, 5, 9, 8, 3};
        // Integer[] array = new Integer[] {6, 2, 2, 3, 1, 7, 4, 9, 5, 3, 6, 4, 9, 9};
        // Integer[] array = new Integer[] {30, 10, 23, 35, 29, 9};
        Integer[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        // Integer[] array = new Integer[] {20, 4, 1, 2, 3, 4, 2, 10};
        // Integer[] array = new Integer[] {12, 4, 4, 4, 45, 23, 12};
        // sorter.bubbleSort(array);
        // sorter.selectionSort(array);
        // sorter.insertionSort(array);
        // sorter.mergeSortTopDown(array, new Integer[array.length]);
        // sorter.quickSort(array);
        // sorter.hasRepeatedNums(array);
        // sorter.countRepetitions(array);
        // sorter.countRepetitions(array, new int[10]);
        // sorter.radixSort(array, 2);
        // sorter.twoSum(array, 10);
        // sorter.threeSum(array, 10);
        // sorter.hasMajority(array);
        // sorter.findMaxOccuringElem(array);
        // sorter.findMaxOccuringElem(array, 10);
        // sorter.findDuplicates(array);
        // sorter.countFrequencies(array);
        // sorter.alternateElemShuffleDC(array);
        // sorter.alternateElemShuffle(array);
        // sorter.reArrangePositiveNegative(array, 0);
        // sorter.minSwapsToSort(array);
        // sorter.convertToPosIndex(array);
        // sorter.maxBitonicSubarray(array);
        // sorter.merge(new Integer[] {3, 6}, new Integer[] {7, 8, 10});
        sorter.rearrangeToPeaks(array);
    }
}
