import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DivideConquer {

    public void medianTwoSortedArrays(double[] arr1, double[] arr2) {
        // refer selections algo
    }

    public void strassensMatrixMultiplication(double[][] A, double[][] B) {
        // We split matrix into four block, and recursively do that
        // Still we would end up with 8 multiplications
        // But Strassen's matrix mul was able to reduce that to 7
        // T(n) = 7.T(n/2) + O(n^2)
        // T(n) = O(n^log7) = O(n^2.81..)
    }

    public void maximizeProfitStock(double[] prices) {
        int maxindex = prices.length-1;
        double maxprice = prices[maxindex];
        double profit = 0.;
        for (int index=prices.length-2; index >= 0; index--) {
            if (prices[index] > maxprice) {
                maxprice = prices[index];
                maxindex = index;
            } else {
                profit = profit - prices[index] + prices[maxindex];
            }
        }
        System.out.println("Maximum Profit : " + profit);
    }

    public void maximizeProfitStock(double[] prices, int maxstocks) {
        int maxindex = prices.length-1;
        double maxprice = prices[maxindex];
        double profit = 0.;
        PriorityQueue<Double> maxheap = new PriorityQueue<>(
            10,
            new Comparator<Double>() {
                public int compare(Double d1, Double d2) {
                    return d2.compareTo(d1);
                }
            }
        );
        for (int index=prices.length-2; index >= 0; index--) {
            if (prices[index] > maxprice) {
                maxprice = prices[index];
                maxindex = index;
            } else {
                System.out.println("Adding profit of " + (prices[maxindex] - prices[index]));
                maxheap.offer(prices[maxindex] - prices[index]);
                // we can ofcourse just add to an array, heapify,
                // and use an auxillary heap to do this at O(n + klogk)
            }
        }
        while (maxstocks > 0 && maxheap.size() > 0) {
            profit = profit + maxheap.poll();
            maxstocks--;
        }
        System.out.println("Maximum Profit : " + profit);
    }

    // egg drop problem - practice

    // first/last occurence in sorted repeated array - refer searching

    // {a1, a2, a3 ... an, b1, b2, b3 ... bn} ---> {a1, b1, a2, b2 ... an, bn} 
    public void rearrangeArray(int[] array) {
        // refer shuffling algo
    }

    public void nutsBoltsProblem() {
        // refer sorting algo
    }

    public void maxValContiguousSubsequence(int[] array) {
        System.out.println("Longest Subsequence has a max sum of " + maxValContSubseq(array, 0, array.length-1));
    }

    private int maxValContSubseq(int[] array, int start, int end) {
        int mid = (start + end) >>> 1;
        if (start == end) 
            return array[start];
        // Two halves - (start, mid) & (mid+1, end)
        int maxLeftHalf = maxValContSubseq(array, start, mid);
        int maxRightHalf = maxValContSubseq(array, mid+1, end);
        int leftBorder = array[mid], rightBorder = array[mid+1], maxLeftBorder = array[mid], maxRightBorder = array[mid+1];
        for (int index=mid-1; index >= start; index--) {
            leftBorder = leftBorder + array[index];
            maxLeftBorder = Math.max(leftBorder, maxLeftBorder);
        }
        for (int index=mid+2; index <= end; index++) {
            rightBorder = rightBorder + array[index];
            maxRightBorder = Math.max(rightBorder, maxRightBorder);
        }
        return Math.max(maxLeftHalf, Math.max(maxRightHalf, maxLeftBorder + maxRightBorder));
    }

    public void closestPairPoints(double[] x, double[] y) {
        // Little painful to implement - must try later
    }

    class Interval {
        int start;
        int end;
        int height;
        public Interval(int start, int height, int end) {
            this.start = start;
            this.height = height;
            this.end = end;
        }

        public String toString() {
            return "(" + start + ", " + height + ", " + end + ")";
        }
    }

    public void skylineProblem(int[] startpos, int[] height, int[] endpos) {
        int maxlen = endpos[0];
        for (int index=1; index < endpos.length; index++)
            maxlen = Math.max(maxlen, endpos[index]);
        // int[] skyline = skyline(0, startpos.length-1, startpos, endpos, height, maxlen+1);
        // String s = "";
        // for (int top : skyline)
        //     s = s + top + " ";
        // System.out.println("Skyline : " + s);
        List<Interval> skyline = skylineOptimal(0, startpos.length-1, startpos, endpos, height);

        String s = "";
        for (Interval top : skyline)
            s = s + top + " ";
        System.out.println("Skyline : " + s);
    }

    // Takes in the subsequence of points defined by (startpos, height, endpos), and computes the skyline for it
    // This is not the best way since the complexity is pseudo polynomial and potentially exponential for long skylines
    private int[] skyline(int start, int end, int[] startpos, int[] endpos, int[] height, int length) {
        if (start == end) {
            int[] skyline = new int[length];
            for (int index = startpos[start]; index < endpos[start]; index++)
                skyline[index] = height[start];
            return skyline;
        }

        int skylineStart = startpos[start], skylineEnd = endpos[start];
        for (int index=start+1; index <= end; index++) {
            skylineStart = Math.min(skylineStart, startpos[index]);
            skylineEnd = Math.max(skylineEnd, endpos[index]);
        }

        // We will recurse to populate skyline[skylinestart : skylineend]
        int mid = (start + end) >>> 1;
        int[] leftSkyline = skyline(start, mid, startpos, endpos, height, length);
        int[] rightSkyline = skyline(mid+1, end, startpos, endpos, height, length);
        
        // Need to merge the two skylines into the leftskyline and return that
        for (int index=skylineStart; index <= skylineEnd; index++) {
            leftSkyline[index] = Math.max(leftSkyline[index], rightSkyline[index]);
        }

        return leftSkyline;
    }
    
    // Takes in the subsequence of points defined by (startpos, height, endpos), and computes the skyline for it
    // Assume that the skylines are sorted by the startpos in increasing order
    private List<Interval> skylineOptimal(int start, int end, int[] startpos, int[] endpos, int[] height) {
        if (start == end) {
            List<Interval> skyline = new ArrayList<>();
            skyline.add(new Interval(startpos[start], height[start], endpos[start]));
            return skyline;
        }

        // We will recurse to populate skyline[skylinestart : skylineend]
        int mid = (start + end) >>> 1;
        List<Interval> leftSkyline = skylineOptimal(start, mid, startpos, endpos, height);
        List<Interval> rightSkyline = skylineOptimal(mid+1, end, startpos, endpos, height);
        List<Interval> skyline = new ArrayList<>();
        
        // Need to merge the two skylines into the leftskyline and return that
        int leftIntervalIdx = 0, rightIntervalIdx = 0;
        while (leftIntervalIdx < leftSkyline.size() && rightIntervalIdx < rightSkyline.size()) {
            // try { Thread.sleep(1000);} catch(Exception e) {}
            Interval leftInterval = leftSkyline.get(leftIntervalIdx);
            Interval rightInterval = rightSkyline.get(rightIntervalIdx);

            if (leftInterval.start == rightInterval.start) {
                skyline.add(new Interval(leftInterval.start, Math.max(leftInterval.height, rightInterval.height), 
                    Math.min(leftInterval.end, rightInterval.end)));
                if (leftInterval.end < rightInterval.end) {
                    System.out.println("CASE : Both intervals start together and left interval ends first");
                    leftIntervalIdx++;
                    rightInterval.start = leftInterval.end;
                } else {
                    System.out.println("CASE : Both intervals start together and right interval ends first");
                    rightIntervalIdx++;
                    leftInterval.start = rightInterval.end;
                }
            } else if (leftInterval.end < rightInterval.start && leftInterval.start < rightInterval.start) {
                System.out.println("CASE : Left interval before right interval and no overlap");
                skyline.add(new Interval(leftInterval.start, leftInterval.height, leftInterval.end));
                leftIntervalIdx++;
            } else if (leftInterval.end > rightInterval.start && leftInterval.start < rightInterval.start) {
                System.out.println("CASE : Left interval before right interval and overlaps");
                skyline.add(new Interval(leftInterval.start, leftInterval.height, rightInterval.start));
                leftInterval.start = rightInterval.start;
            }
             else if (rightInterval.end < leftInterval.start && rightInterval.start < leftInterval.start) {
                System.out.println("CASE : Right interval before left interval and no overlap");
                skyline.add(new Interval(rightInterval.start, rightInterval.height, rightInterval.end));
                rightIntervalIdx++;
            } else if (rightInterval.end > leftInterval.start && rightInterval.start < leftInterval.start) {
                System.out.println("CASE : Right interval before left interval and overlaps");
                skyline.add(new Interval(rightInterval.start, rightInterval.height, leftInterval.start));
                rightInterval.start = leftInterval.start;
            }
        }
        while (leftIntervalIdx < leftSkyline.size()) {
            skyline.add(leftSkyline.get(leftIntervalIdx));
            leftIntervalIdx++;
        }

        while (rightIntervalIdx < rightSkyline.size()) {
           skyline.add(rightSkyline.get(rightIntervalIdx));
           rightIntervalIdx++;
        }

        // Reduce redundencies
        int prev = 0, curr = 0, prevEnd = -1, prevStart = -1, prevHeight = -1;
        while (curr < skyline.size()) {
            Interval prevInt = skyline.get(prev);
            Interval currInt = skyline.get(curr);
            if (prevInt.height == currInt.height) {
                prevInt.end = currInt.end;
                curr++;
            } else {
                prev++;
                // copy curr onto prev
                prevInt = skyline.get(prev);
                prevInt.start = currInt.start;
                prevInt.height = currInt.height;
                prevInt.end = currInt.end;
            }
        }

        prev = prev + 1;
        while (prev < skyline.size()) {
            skyline.remove(prev);
        }
        
        return skyline;
    }

    public static void main(String[] args) {
        DivideConquer dc = new DivideConquer();
        dc.maximizeProfitStock(new double[] {31,312,3,35,33,3,44,123,126,2,4,1});
        dc.maximizeProfitStock(new double[] {31,312,3,35,33,3,44,123,126,2,4,1}, 4);
        // dc.maxValContiguousSubsequence(new int[] {-2, 11, -4, -1, 13, -5, 2});
        dc.maxValContiguousSubsequence(new int[] {-2, -4, -3, 2, -5, -1});
        dc.skylineProblem(
            new int[] {3, 5, 1, 14, 15, 26, 20, 23}, 
            new int[] {9, 17, 14, 11, 6, 14, 19, 15}, 
            new int[] {10, 12, 7, 18, 27, 29, 22, 30});
    }
}