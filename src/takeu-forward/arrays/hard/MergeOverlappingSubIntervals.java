package arrays.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/merge-overlapping-sub-intervals/
    https://leetcode.com/problems/merge-intervals/description/
 */
public class MergeOverlappingSubIntervals {

    /*
    Approach:
        The steps are as follows:

        First, we will group the closer intervals by sorting the given array of intervals(if it is not already sorted).
        After that, we will start traversing the array using a loop(say i) and insert the first element into our answer list(as the answer list is empty).
        Now, while traversing we will face two different cases:
        Case 1: If the current interval can be merged with the last inserted interval of the answer list:
        In this case, we will update the end of the last inserted interval with the maximum(current interval’s end,
        last inserted interval’s end) and continue moving afterward.
        Case 2: If the current interval cannot be merged with the last inserted interval of the answer list:
        In this case, we will insert the current interval in the answer array as it is. And after this insertion,
        the last inserted interval of the answer list will obviously be updated to the current interval.
        Thus, the answer list will be updated with the merged intervals and finally, we will return the answer list.

        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    public static List<List<Integer>> mergeOverlappingSubIntervalsOptimal(int[][] arr){

        //sort the given intervals:
        //TODO: read and know more about Comparable and Comparator in java.
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            /* if result list is empty or it is in the form of
             last pair the result is (1, 3) and arr[i] is (8, 9) then
             8 > 3is true, means arr[i][0] is greater than result set last pair right index
             it means we have to insert a new pair into result set.*/
            if(result.isEmpty() || arr[i][0] > result.get(result.size() - 1).get(1)){
                result.add(Arrays.asList(arr[i][0], arr[i][1]));
            }
            /* else means the arr[i] falls in the range of last pair of result set.
             but now the result set last pair index has to be updated
             say result set last pair is (1, 3) arr[i] is (2, 6)
             then 3 > 2 is false so if() will not execute it will enter else block
             so this pair has to be added under(1, 3) 2 can be accommodated in this range but not 6 so we have to update the end index of the result pair,
             so it will become maximum of (3, 6) = 6
             means max(resultSet.last pair[1], arr[i][1])*/
            else{
                result.get(result.size() - 1).set(1,
                        Math.max(result.get(result.size() - 1).get(1), arr[i][1]));
            }
        }

        return result;
    }

    /*
    same approach, just returns matrix instead of list of list.
     */
    public static int[][] mergeOverlappingSubIntervalsReturns2DArrayOptimal(int[][] arr){

        //sort the given intervals:
        //TODO: read and know more about Comparable and Comparator in java.
        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        List<int[]> result = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            /* if result list is empty or it is in the form of
             last pair the result is (1, 3) and arr[i] is (8, 9) then
             8 > 3is true, means arr[i][0] is greater than result set last pair right index
             it means we have to insert a new pair into result set.*/
            if(result.isEmpty() || arr[i][0] > result.get(result.size() - 1)[1]){
                result.add(new int[]{ arr[i][0], arr[i][1]});
            }
            /* else means the arr[i] falls in the range of last pair of result set.
             but now the result set last pair index has to be updated
             say result set last pair is (1, 3) arr[i] is (2, 6)
             then 3 > 2 is false so if() will not execute it will enter else block
             so this pair has to be added under(1, 3) 2 can be accommodated in this range but not 6 so we have to update the end index of the result pair,
             so it will become maximum of (3, 6) = 6
             means max(resultSet.last pair[1], arr[i][1])*/
            else{
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], arr[i][1]);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};

        List<List<Integer>> result = mergeOverlappingSubIntervalsOptimal(arr);
        int[][] result2 = mergeOverlappingSubIntervalsReturns2DArrayOptimal(arr);
        System.out.println("Merged intervals list: " + result);

        System.out.println("Merged intervals matrix");
        for(int i = 0; i < result2.length; i++){
            for(int j = 0; j < result2[0].length; j++){
                System.out.printf(result2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
