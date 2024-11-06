package greedy.easy;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/*
Problem:
    https://leetcode.com/problems/insert-interval/description/
 */
public class InsertIntervals {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            where n is number of intervals
        Space Complexity: O(2n)
            O(n) for storing intervals in resultant list
            O(n) for storing and returning resultant 2D array
     */
    public static int[][] insertIntervals(int[][] intervals, int[] newInterval){

        // edge case, if the intervals array is empty and you want to insert new interval, directly insert it and return
        if(intervals.length == 0){
            // create new 2D matrix, insert new interval and return it.
            int[][] twoDArray = new int[1][];
            twoDArray[0] = newInterval;
            return twoDArray;
        }

        List<List<Integer>> resultList = new ArrayList<>();

        int i = 0, n = intervals.length;

        while(i < n){

            // left section
            while(i < n && intervals[i][1] < newInterval[0]) {
                resultList.add(new ArrayList<>(Arrays.asList(intervals[i][0], intervals[i][1])));
                i++;
            }

            /* middle section the current section startin index is less than new interval ending index
             since left section has been completed, this interval should be overlapping*/
            int minimumInterval = Integer.MAX_VALUE, maximumInterval = Integer.MIN_VALUE;
            while(i < n && intervals[i][0] < newInterval[1]){
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
                i++;
            }
            // middle section non overlapping has been formed, insert into result list
            resultList.add(new ArrayList<>(Arrays.asList(newInterval[0], newInterval[1])));

            // right section
            while(i < n) {
                resultList.add(new ArrayList<>(Arrays.asList(intervals[i][0], intervals[i][1])));
                i++;
            }
        }

        // convert result list to result 2D array and return
        int[][] resultArray = resultList.stream()
                .map(innerList -> innerList.stream().mapToInt(Integer:: intValue).toArray())
                .toArray(int[][] :: new);

        return resultArray;

    }
}
