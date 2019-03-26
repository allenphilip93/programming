import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Comparator;

public class GreedyAlgorithms {

    // 1 must be present in the coin denominations
    public void optCoinChange(int[] coinDenominations, int sum) {
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(
            10,
            new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i2.compareTo(i1);
                }
            }
        );
        // Add the coin denominations
        for (int index=0; index < coinDenominations.length; index++)
            maxheap.add(coinDenominations[index]);
        // Make the greedy choice at every step
        int numcoins = 0;
        String s = "";
        while (sum > 0) {
            while (maxheap.peek() > sum)
                maxheap.poll();
            int coin = maxheap.poll();
            s = s + coin + "*" + (sum/coin) + " + ";
            numcoins = numcoins + (sum/coin);
            sum = sum % coin;            
        }
        System.out.println("Min number of greedy coins : " + numcoins);
        System.out.println("Coin configuration : " + s);
    }

    public void allCoinChange(int[] coinDenominations, int sum) {
        // To be implemented.. Need to build recursive until sum is 0 - dp equation
    }

    public void fractionalKnapsack(double[] weights, double[] values, double size) {
        class Node {
            Double unitValue;
            int index;
            public Node (Double unitValue, int index) {
                this.unitValue = unitValue;
                this.index = index;
            }
        }
        PriorityQueue<Node> maxheap = new PriorityQueue<>(
            10,
            new Comparator<Node>() {
                public int compare(Node n1, Node n2) {
                    return n2.unitValue.compareTo(n1.unitValue);
                }
            }
        );
        // Need to build heap to get the maximum value per weight unit
        for (int index=0; index < weights.length; index++) {
            maxheap.add(new Node(values[index]/weights[index], index));
        }
        // We need to keep picking until we are maxed out on size
        double maxValue = 0.0;
        String s = "";
        while (size > 0 && maxheap.size() > 0) {
            Node curr = maxheap.poll();
            if (weights[curr.index] < size) {
                s = s + (weights[curr.index] * curr.unitValue) + " [" + curr.index + "] + ";
                maxValue = maxValue + (weights[curr.index] * curr.unitValue);
                size = size - weights[curr.index];
            } else {
                s = s + (size * curr.unitValue) + " [" + curr.index + "] + ";
                maxValue = maxValue + (size * curr.unitValue);
                size = 0;
            }
        }
        System.out.println("Max Fractional Knapsack Value : " + maxValue);
        System.out.println("Knapsack Configuration : " + s);
    }

    // minimize for lateness
    public void scheduleProcesses(double[] processTimes) {
        PriorityQueue<Double> minheap = new PriorityQueue<Double>();
        double lateness = 0.0;
        for (int index=0; index < processTimes.length; index++) {
            minheap.add(processTimes[index]);
        }
        while (minheap.size() > 0) {
            double processTime = minheap.poll();
            lateness = lateness + processTime;
        }
        System.out.println("Max Processing Time : " + lateness);
    }

    // Some processes start at said release time
    public void scheduleProcessesWithReleaseTime(double[] processTimes, double[] releaseTimes) {
        // W[i,j] -> Waiting time of jth process when ith process is executed first
        // W[i,j] = r[i] + p[i] - r[j] , if r[j] > r[i]
        // W[i,j] = 0                  , if r[j] < r[i]
        // W[i] = sum over all j (W[i][j])
        // To find the i for which W[i] is min among all n process, 
        // we need to find the process with min p[i] + r[i]
        // Before we select the next process, we need to update 
        // p & r of all the subsequent process with the correct r times
        Set<Integer> exclusion = new HashSet<>();
        double completionTime = 0.0;
        while (exclusion.size() < processTimes.length) {
            int index = findMin(processTimes, releaseTimes, exclusion);
            exclusion.add(index);
            completionTime = completionTime + processTimes[index] + releaseTimes[index];
            // Need to update all release times now
            double releaseTime = releaseTimes[index];
            for (int rIdx=0; rIdx < releaseTimes.length; rIdx++) {
                if (releaseTimes[rIdx] > releaseTime) {
                    releaseTimes[rIdx] = releaseTimes[rIdx] - releaseTime;
                } else {
                    releaseTimes[rIdx] = 0;
                }
            }
        }
        System.out.println("Total completion time : " + completionTime);
    }

    // Processes can be prempted or pause and restarted
    public void scheduleProcessWithPremption(double[] processTimes, double[] releaseTimes) {
        // Kind of similar to the previous approach
        // Except when r[i] goes to 0 and its not in the exclusion set
        // we prempt it, like put the process in pause
        // Unless we need it again, we don't change that state
        // And when a process is in that state, it does not spend time 
        // waiting, so we can make sure only one process runs and rest
        // all are in a paused state
    }

    private int findMin(double[] p, double[] r, Set<Integer> exclusion) {
        double min = Double.POSITIVE_INFINITY;
        int res = -1;
        for (int index=0; index < p.length; index++) {
            if (p[index] + r[index] < min && !exclusion.contains(index)) {
                res = index;
                min = p[index] + r[index];
                System.out.println("MIN : " + min + " INDEX : "+ res);
            }
        }
        return res;
    }

    // events are all unit time, and if event starts before eventtime then event profits else 0 profit
    public void scheduleForEventProfit(double[] eventTimes, double[] eventProfits) {
        class Event {
            Integer index;
            Double start;
            Event(int index, double start) {
                this.index = index;
                this.start = start;
            }
        }
        // Events can be started utmost at e.finish to get profit
        // so build max heap by time finish first to squeeze in max 
        // num of events and then by profit
        PriorityQueue<Event> timeheap = new PriorityQueue<>(
            10,
            new Comparator<Event>() {
                public int compare(Event e1, Event e2) {
                    return e2.start.compareTo(e1.start);
                }
            }
        );
        for (int index=0; index < eventTimes.length; index++) {
            timeheap.add(new Event(index, Math.floor(eventTimes[index])));
        }
        // Evaluate max profit
        String s = "";
        double maxprofit = 0.0;
        double maxTime = timeheap.peek().start; // Important to get this part!
        while (timeheap.size() > 0 && maxTime >= 0) {
            PriorityQueue<Double> profitheap = new PriorityQueue<>(
                10,
                new Comparator<Double>() {
                    public int compare(Double d1, Double d2) {
                        return d2.compareTo(d1);
                    }
                }
            );
            double curr = timeheap.peek().start;
            while (timeheap.size() > 0 && timeheap.peek().start == curr)
                profitheap.add(eventProfits[timeheap.poll().index]);
            s = s + profitheap.peek() + " (" + maxTime + ") => ";
            maxprofit = maxprofit + profitheap.poll();
            maxTime--;
        }
        System.out.println("Max Profit : " + maxprofit);
        System.out.println("Profit Order : " + s);
    }

    
    public void preparingSongCasette() {
        // Pretty much the same as process scheduling, but lateness would be the rewind time in this case        
    }

    public static void main(String[] args) {
        GreedyAlgorithms greedy = new GreedyAlgorithms();
        greedy.optCoinChange(new int[] {1, 5, 10, 25}, 137);
        greedy.fractionalKnapsack(
            new double[] {1, 2, 3, 4, 5}, 
            new double[] {10, 15, 15, 30, 25}, 
            10);
        greedy.scheduleProcesses(new double[] {2, 5, 8});
        greedy.scheduleForEventProfit(
            // new double[] {0.7, 0.9, 1.2, 2.1, 3.2, 3.6, 3.9, 4.1, 4.8, 4.5},
            // new double[] {2, 3, 5, 10, 1, 3, 5, 10, 15, 9});
            new double[] {1.5, 2.0, 0.2, 1.7}, 
            new double[] {2, 6, 8, 7});
        greedy.scheduleProcessWithPremption(
            new double[] {2, 3, 5, 8},
            new double[] {1, 5, 2, 3});
    }
}