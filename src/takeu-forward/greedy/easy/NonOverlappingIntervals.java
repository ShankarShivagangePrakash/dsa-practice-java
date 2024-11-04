package greedy.easy;

import java.util.Arrays;
import java.util.Comparator;

/*
Problem:
    https://leetcode.com/problems/non-overlapping-intervals/description/
 */
public class NonOverlappingIntervals {

    public static int nonOverlappingIntervalsOptimal(int[][] intervals){
        int n = intervals.length;

        // sort intervals in ascending order based on end time. arr[i][1]
        Arrays.sort(intervals, (a, b) -> (a[1] - b[1]) );

        int count = 0;
        // there can be negative valued intervals, so initialize endTime to negative minimum value
        int endTime = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++){
            /* if the current interval starting point is greater than or equal to last interval ending point,
             it is considered as non overlappint interval, increment count.*/
            if(intervals[i][0] >= endTime){
                count++;
                endTime = intervals[i][1];
            }
        }

        /* we can keep count number of intervals without any overlaps,
         for that we have to remove remaining intervals, i.e.*/
        return n - count;
    }
}
