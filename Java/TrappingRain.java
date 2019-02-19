import java.util.Arrays;
import java.util.Collections;

public class TrappingRain {

    public static void largestContainer(int [] arr) {
        int maxRain = 0;
        int leftMax = 0, rightMax = 0;
        int leftIdx =0, rightIdx = arr.length-1;
        
        // as long as LM <= RM, bounding pillar will be LM
        // as long as RM <= LM, bounding pillar will be RM
        
        int currRain = 0;
        while (leftIdx <= rightIdx) {
            if (leftMax <= rightMax) {
                if (arr[leftIdx] > leftMax) {
                    // Update the leftMax
                    leftMax = arr[leftIdx];
                    if (currRain > maxRain) {
                        maxRain = currRain;
                    }
                    currRain = 0;
                } else {
                    // we can solve for that leftidx
                    currRain = currRain + leftMax - arr[leftIdx];
                }
                // Increment leftIdx
                leftIdx++;
            } else if (rightMax < leftMax) {
                
                if (arr[rightIdx] > rightMax) {
                    // Update the rightMax
                    rightMax = arr[rightIdx];
                    if (currRain > maxRain) {
                        maxRain = currRain;
                    }
                    currRain = 0;
                } else {
                    // we can solve for that rightIdx
                    currRain = currRain + rightMax - arr[rightIdx];
                }
                // Decrement rightIdx
                rightIdx--;
            }
        }

        if (currRain > maxRain)
            maxRain = currRain;

        System.out.println("Max Rain Container : " + maxRain);
    }
    
    public static void trapRain(int[] arr) {
        int totalRain = 0;
        int leftMax = 0, rightMax = 0;
        int leftIdx =0, rightIdx = arr.length-1;
        
        // as long as LM <= RM, bounding pillar will be LM
        // as long as RM <= LM, bounding pillar will be RM
        
        while (leftIdx <= rightIdx) {
            if (leftMax <= rightMax) {
                if (arr[leftIdx] > leftMax) {
                    // Update the leftMax
                    leftMax = arr[leftIdx];
                } else {
                    // we can solve for that leftidx
                    totalRain = totalRain + leftMax - arr[leftIdx];
                }
                // Increment leftIdx
                leftIdx++;
            } else if (rightMax < leftMax) {
                
                if (arr[rightIdx] > rightMax) {
                    // Update the rightMax
                    rightMax = arr[rightIdx];
                } else {
                    // we can solve for that rightIdx
                    totalRain = totalRain + rightMax - arr[rightIdx];
                }
                // Decrement rightIdx
                rightIdx--;
            }
        }

        System.out.println("Total Rain Trapped : " + totalRain);
    }
    public static void main(String[] args) {
        // int[] arr = {4, 0, 0, 3, 0, 2, 0, 0, 4, 0};
        int[] arr = {1,0,8,0,6,0,2,0,5,0,4,0,8,0,3,0,7};
        System.out.println("Input Array : " + Arrays.toString(arr));
        printHistogram(arr);
        trapRain(arr);
        largestContainer(arr);
    }

    private static void printHistogram(int[] arr) {
        int max = arr[0];
        for (int idx=0; idx < arr.length; idx++) {
            if (arr[idx] > max)
                max = arr[idx];
        }
        String[] histogram = new String[max + 1];
        for (int i=0; i < histogram.length; i++) {
            histogram[i] = "";
        }
        for (int i=0; i < histogram.length; i++) {
            for (int j=0; j < arr.length; j++) {
                if (i > max) {
                    histogram[i] += " ";
                } else if (i == 0) {
                    histogram[i] += "==";
                } else {
                    if (i == arr[j]) {
                        histogram[i] += "__";
                    } else if (i < arr[j]) {
                        histogram[i] += "||";
                    } else {
                        histogram[i] += "  ";
                    }
                }
            }
        }
        for (int idx = histogram.length-1; idx >= 0; idx--) 
            System.out.println(histogram[idx]);
    }
}