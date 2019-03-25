import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class IntervalScheduler {

    private class Interval {
        Integer start;
        Integer end;
        public Interval(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "(" + this.start + ", " + this.end + ")";
        }
    }

    private Interval[] intervals;

    public void addIntervals(int[][] intervals) {
        this.intervals = new Interval[intervals.length];
        for (int index=0; index < intervals.length; index++) {
            this.intervals[index] = new Interval(intervals[index][0], intervals[index][1]);
        }
    }

    // Optimize to perform max number of tasks
    public void optMostTask() {
        PriorityQueue<Interval> minheap = new PriorityQueue<>(
            10, 
            new Comparator<Interval>() {
                public int compare(Interval i1, Interval i2) {
                    if (i1.end.compareTo(i2.end) != 0)
                        return i1.end.compareTo(i2.end);
                    else
                        return i1.start.compareTo(i2.start);
                }
        });
        for (int index=0; index < intervals.length; index++) {
            minheap.offer(intervals[index]);
        }
        List<Interval> res = new ArrayList<>();
        while (minheap.size() > 0) {
            Interval interval = minheap.remove();
            res.add(interval);
            while (minheap.size() > 0 && interval.end.compareTo(minheap.peek().start) > 0) {
                minheap.remove();
            }
        }
        String s = "START => ";
        for (Interval interval : res) {
            s = s + interval + " => ";
        }
        s = s + "END";
        System.out.println("Optimal schedule for max tasks : " + s);
    }

    public void findMaxOverlap() {
        PriorityQueue<Integer> minStart = new PriorityQueue<Integer>();
        PriorityQueue<Integer> minEnd = new PriorityQueue<Integer>();
        for (int index=0; index < intervals.length; index++) {
            minStart.offer(intervals[index].start);
            minEnd.offer(intervals[index].end);
        }
        int maxOverlap = -1, overlap = 0;
        int start = minStart.remove();
        int end = minEnd.remove();
        while (minStart.size() > 0) {
            if (start < end) {
                // System.out.println("Interval Start : " + start);
                start = minStart.poll();
                overlap++;
            } else {
                // System.out.println("Interval End : " + end);
                end = minEnd.poll();
                overlap--;
            }
            maxOverlap = Math.max(maxOverlap, overlap);
        }
        System.out.println("Max Overlap : " + maxOverlap);
    }

    // Optimize for minimal lateness, most busy schedule without overlap
    public void optMinLateness() {
        
    }

    public static void main(String[] args) {
        IntervalScheduler schedule = new IntervalScheduler();
        schedule.addIntervals(new int[][] {
            {2,4}, {1,4}, {3,5}, {5,6}, {6,8}, {7,8},{9,10} , {4,6}
        });
        schedule.optMostTask();
        schedule.findMaxOverlap();
        schedule.optMinLateness();
    }
}