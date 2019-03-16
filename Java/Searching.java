import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Searching<T extends Number & Comparable<? super T>> extends Sorting<T> {
    
    public void firstRepeatedElement(Integer[] array) {
        Set<Integer> set = new HashSet<>();
        int min = -1;
        for (int index=array.length-1; index >= 0; index--) {
            if (set.contains(array[index])) {
                min = index;
            } else {
                set.add(array[index]);
            }
        }
        if (min == -1)
            System.out.println("No element has been repeated!");
        else 
            System.out.println("First repeated element : " + array[min]);
    }

    public void findMissingNumber(Integer[] array) {
        int n = array.length + 1;
        Integer missingNum = null;
        for (int index=1; index < n; index++) {
            if (missingNum == null) {
                missingNum = index ^ array[index-1];
            } else {
                missingNum = missingNum ^ index ^ array[index-1];
            }
        }
        missingNum = missingNum ^ n;
        System.out.println("Missing number : " + missingNum);
    }

    public void findOddOccurringNum(Integer[] array) {
        Integer oddelem = null;
        for (Integer elem : array) {
            if (oddelem == null) {
                oddelem = elem;
            } else {
                oddelem = oddelem ^ elem;
            }
        }
        System.out.println("Odd occurring number : " + oddelem);
    }

    public void threeSumSquares(Integer[] array) {
        quickSort(array);
        Set<Integer> set = new HashSet<>();
        for (int idx=0; idx < array.length; idx++) {
            array[idx] = array[idx] * array[idx];
            set.add(array[idx]);
        }
        for (int outer=array.length-1; outer >= 0; outer--) {
            int sum = array[outer];
            for (int inner = 0; inner < outer; inner++) {
                if (set.contains(sum - array[inner])) {
                    System.out.println(array[inner] + " + " + (sum - array[inner]) + " = " + sum);
                }
            }
        }
    }

    public void almostZeroTwoSum(Integer[] array) {
        quickSort(array);
        int front = 0, end = array.length -1, minsum = 9999999;
        while (front < end) {
            int sum = array[front] + array[end];
            if (Math.abs(sum) < minsum) {
                minsum = sum;
            }
            if (sum > 0) {
                end--;
            } else if (sum < 0) {
                front++;
            } else {
                break;
            }
        }
        System.out.println("Almost Zero Two Sum : " + minsum);
    }

    public void almostZeroThreeSum(Integer[] array) {
        quickSort(array);
        Integer minThreeSum = null;
        for (int curr=0; curr < array.length; curr++) {
            int front = 0, end = array.length-1;
            while (front < end) {
                if (front == curr) {
                    front++;
                    continue;
                } else if (end == curr) {
                    end--;
                    continue;
                }
                int sum = array[curr] + array[front] + array[end];
                if (minThreeSum == null || minThreeSum > Math.abs(sum)) {
                    minThreeSum = Math.abs(sum);
                }
                if (sum > 0) {
                    end--;
                } else if (sum < 0) {
                    front++;
                } else {
                    break;
                }
            }
        }
        System.out.println("Almost zero three sum : " + minThreeSum);
    }

    public void findBitonicPeak(Integer[] array) {
        int front = 0, rear = array.length-1, mid = -1;
        while (front <= rear) {
            mid = (front + rear)/2;
            int slope = getSlope(array, mid);
            if (slope == 0) {
                break;
            } else if (slope < 0) {
                rear = mid - 1;
            } else if (slope > 0) {
                front = mid + 1;
            }
        }
        System.out.println("Bitonic peak is at " + mid + " with value of " + array[mid]);
    }

    public void findBitonicWithoutN(Integer[] array) {
        int front = 0, rear = 1, mid = -1;
        while (getSlope(array, rear) >= 0) {
            rear = rear * 2;
        }
        while (front <= rear) {
            mid = (front + rear)/2;
            int slope = getSlope(array, mid);
            if (slope == 0) {
                break;
            } else if (slope < 0) {
                rear = mid - 1;
            } else if (slope > 0) {
                front = mid + 1;
            }
        }
        System.out.println("Bitonic peak is at " + mid + " with value of " + array[mid]);
    }

    private int getSlope(Integer[] array, int index) {
        int leftIdx = (index <= 0) ? 0 : index-1;
        int rightIdx = (index >= array.length-1) ? array.length-1 : index+1;
        int slope = 0;
        if ((array[index] - array[leftIdx]) > 0) {
            slope = 1;
        } else if ((array[index] - array[leftIdx]) < 0) {
            slope = -1;
        }
        if ((array[rightIdx] - array[index]) > 0) {
            slope = slope + 1;
        } else if ((array[rightIdx] - array[index]) < 0) {
            slope = slope - 1;
        }
        return slope;
    }

    public void findBinaryChangeIndex(Integer[] array) {
        int front = 0, rear = array.length-1, mid = -1;
        while (front <= rear) {
            mid = (front + rear)/2;
            int slope = getSlopeForBinary(array, mid);
            if (slope == 0) {
                break;
            } else if (slope < 0) {
                rear = mid - 1;
            } else if (slope > 0) {
                front = mid + 1;
            }
        }
        System.out.println("Index of the last occurence of 1 is " + mid);
    }

    private int getSlopeForBinary(Integer[] array, int index) {
        int leftIdx = (index <= 0) ? 0 : index-1;
        int rightIdx = (index >= array.length-1) ? array.length-1 : index+1;
        int slope = 0;
        if (array[index] == 1 && array[leftIdx] == 1 && array[rightIdx] == 1) {
            slope = 1;
        } else if (array[index] == 0 && array[rightIdx] == 0) {
            slope = -1;
        }
        return slope;
    }

    public int binarySearch(Integer[] array, Integer elem) {
        quickSort(array);
        return binarySearch(array, elem, 0, array.length-1);
    }

    private int binarySearch(Integer[] array, Integer elem, int front, int rear) {
        int mid = -1;
        boolean isfound = false;
        while (front <= rear) {
            mid = (front + rear) >>> 1;
            if (array[mid] == elem) {
                isfound = true;
                break;
            } else if (array[mid] > elem) {
                rear = mid - 1;
            } else {
                front = mid + 1;
            }
            // System.out.println("MID : " + mid + " FRONT : " + front + " REAR : " + rear);
        }
        if (isfound) {
            System.out.println("Element " + elem + " found at index " + mid);
            return mid;
        } else {
            System.out.println("Element " + elem + " not found in the array");
            return -1;
        }
    }

    public int searchRotatedArray(Integer[] array, Integer elem) {
        // Find rotation point in O(logn)
        int front = 0, rear = array.length - 1, mid = -1;
        while (front <= rear) {
            mid = (front + rear) >>> 1;
            if (array[mid] > array[front]) {
                front = mid;
            } else if (array[mid] < array[rear]) {
                rear = mid;
            } else {
                break;
            }
        }
        System.out.println("Starting point index : " + mid);

        // Do binary search on both sub arrays in O(logn)
        System.out.println("F : " + 0 + " M : " + mid + " R : " + (array.length-1));
        int res = binarySearch(array, elem, 0, mid);
        if (res == -1)
            binarySearch(array, elem, mid+1, array.length-1);
        return res;
    }

    // Doesn't handle duplicates as well though
    public void searchRotatedArrayRecursion(Integer[] array, Integer elem) {
        int index = searchRotatedArrayRecursion(array, elem, 0, array.length-1);
        if (index != -1)
            System.out.println("Element of value " + elem + " found at index : " + index);
        else
            System.out.println("Element of value " + elem + " not found!");
    }

    private int searchRotatedArrayRecursion(Integer[] array, Integer elem, int front, int rear) {
        // System.out.println("FRONT : " + front + " | REAR : " + rear);
        if (front > rear) {
            return -1;
        }
        int mid = (front + rear) >>> 1;
        if (array[mid] == elem)
            return mid;
        if (array[mid] >= array[front]) {
            // front ... mid is sorted
            if (elem < array[mid] && elem >= array[front]) {
                return searchRotatedArrayRecursion(array, elem, front, mid-1);
            } else {
                // try and resolve the subproblem
                return searchRotatedArrayRecursion(array, elem, mid+1, rear);
            }
        }
        // mid ... rear is sorted
        if (elem > array[mid] && elem <= array[rear]) {
            return searchRotatedArrayRecursion(array, elem, mid+1, rear);
        }
        // try and resolve the sub problem
        return searchRotatedArrayRecursion(array, elem, front, mid-1);
    }

    public void bitonicSearch(Integer[] array, Integer elem) {
        int index = bitonicSearch(array, elem, 0, array.length-1);
        if (index != -1)
            System.out.println("Element of value " + elem + " found at index : " + index);
        else
            System.out.println("Element of value " + elem + " not found!");
    }

    private int bitonicSearch(Integer[] array, Integer elem, int front, int rear) {
        // System.out.println("FRONT : " + front + " | REAR : " + rear);
        if (front > rear) {
            return -1;
        }
        int mid = (front + rear) >>> 1;
        if (array[mid] == elem) { 
            return mid;
        }
        int slope = getSlope(array, mid);
        int res = -1;
        if (slope >= 0) {
            // front ... mid is asc sorted
            if (elem < array[mid]) {
                res = bitonicSearch(array, elem, front, mid-1);
                if (res != -1) return res;
            } 
            if (elem > array[mid] || res == -1) {
                return bitonicSearch(array, elem, mid+1, rear);
            }
        }
        if (slope < 0) {
            // mid ... rear is desc sorted
            if (elem < array[mid]) {
                res = bitonicSearch(array, elem, mid+1, rear);
                if (res != -1) return res;
            }
            if (elem > array[mid] || res == -1) {
                return bitonicSearch(array, elem, front, mid-1);
            }
        }
        return -1;
    }

    // Find the first occurence and last occurence
    public void findOccurenceRange(Integer[] array, Integer num) {
        int start = findFirstOccurence(array, num, 0, array.length-1);
        int end = findLastOccurence(array, num, 0, array.length-1);
        if (start == -1 || end == -1) {
            System.out.println("Element of value " + num + " was not found in the array!");
        } else {
            System.out.println("Element of value " + num + " is found from index " + start + " upto index " + end);
        }
    }

    private int findFirstOccurence(Integer[] array, Integer num, int start, int end) {
        if (start > end)
            return -1;
        int mid = (start + end) >>> 1;
        // System.out.println("START : " + start + " MID : " + mid + " END : " + end);
        if (array[mid] == num && (mid == 0 || array[mid-1] < num)) {
            return mid;
        } else if (array[mid] >= num) {
            return findFirstOccurence(array, num, start, mid-1);
        } else {
            return findFirstOccurence(array, num, mid+1, end);
        }
    }

    private int findLastOccurence(Integer[] array, Integer num, int start, int end) {
        if (start > end)
            return -1;
        int mid = (start + end) >>> 1;
        // System.out.println("START : " + start + " MID : " + mid + " END : " + end + " LEN : " + array.length);
        if (array[mid] == num && (mid == array.length-1 || array[mid+1] > num)) {
            return mid;
        } else if (array[mid] > num) {
            return findLastOccurence(array, num, start, mid-1);
        } else {
            return findLastOccurence(array, num, mid+1, end);
        }
    }

    // 1 element has n/2 entries and rest are all distinct in an array of size 2n
    public void findMajority(Integer[] array) {
        Integer majority = null;
        for (int i=0; i < array.length-1; i++) {
            if (array[i] == array[i+1])
                majority = array[i];
        }
        if (majority == null) {
            for (int i=0; i < array.length-2; i+=2) {
                if (array[i] == array[i+2])
                    majority = array[i];
            }
        }
        System.out.println("Majority element : " + majority);
    }

    public static void main(String[] args) {
        Searching<Integer> searcher = new Searching<>();
        // Integer[] array = new Integer[] {3, 2, 4, 3, 4, 2, 6, 4, 6, 4, 1};    
        // Integer[] array = new Integer[] {3, 5, 4, 7, 8, 9, 1, 2, 6, 10};    
        // Integer[] array = new Integer[] {1, 60, -10, 70, 80, -85, 2};
        // Integer[] array = new Integer[] {-60, -25, 7, 80, 99, 60, 12, 0, -27};
        // Integer[] array = new Integer[] {10};
        // Integer[] array = new Integer[] {4, 5, 6, 7, 8, 1, 2, 3};
        Integer[] array = new Integer[] {1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8};
        // Integer[] array = new Integer[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        
        // searcher.firstRepeatedElement(array);
        // searcher.findMissingNumber(array);
        // searcher.findOddOccurringNum(array);
        // searcher.threeSumSquares(array);
        // searcher.almostZeroTwoSum(array);
        // searcher.almostZeroThreeSum(array);
        // searcher.findBitonicPeak(array);
        // searcher.findBitonicWithoutN(array);
        // searcher.findBinaryChangeIndex(array);
        // searcher.binarySearch(array, 10);
        // searcher.searchRotatedArray(array, 8);
        // searcher.searchRotatedArrayRecursion(array, 6);
        // searcher.bitonicSearch(array, 99);
        // searcher.findOccurenceRange(array, 8);
    }
}