package arrays.binarysearch.onanswers.minmax;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/arrays/painters-partition-problem/
    https://www.naukri.com/code360/problems/painter-s-partition-problem_1089557?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
 */
public class PaintersPartition {

    /*
    Approach:
        Same as Book Allocation problem
        just variables names are changed

        Time Complexity: O(N * (sum(arr[])-max(arr[])+1))
        Space Complexity: O(1)
     */
    public static int paintPartitionsBruteForce(int[] arr, int n, int k){
        if(k > n)
            return -1;

        int low = Arrays.stream(arr).reduce((a, b) -> (a > b) ? a : b).orElseThrow(()-> new RuntimeException("Error while finding maximum"));
        int high = Arrays.stream(arr).reduce(0, (sum, i)-> sum + i);

        for(int i = low; i <= high; i++){
            int paintersCount = countPainters(arr, i);
            if(paintersCount == k)
                return i;
        }
        return high;
    }

    /*
    Approach:
        Same as BookAllocation.java

        Time Complexity: O(N * log(sum(arr[])-max(arr[])+1))
        Space Complexity: O(1)
     */
    public static int paintPartitionsOptimal(int[] arr, int n, int k){
        int low = Arrays.stream(arr).reduce((a, b)-> (a > b) ? a : b).orElseThrow(()-> new RuntimeException("Error while finding maximum element"));
        int high = Arrays.stream(arr).reduce(0, (sum, i)-> sum + i);

        while(low <= high){
            int mid = (low + high)/2;

            int paintersCount = countPainters(arr, mid);

            //no of paint partitions is less than or equal to k, move to left. - in that case, the answer will be larger, so we have to move left to get smaller values.
            if(paintersCount <= k){
                high = mid - 1;
            }
            // no of paint partition is greater than k, move right
            else{
                low = mid + 1;
            }
        }
        return low;
    }

    public static int countPainters(int[] arr, int time){
        int paintersCount = 1, currentBoardPaintRequiredtime = 0;

        for(int i = 0; i < arr.length; i++){
            if(currentBoardPaintRequiredtime + arr[i] <= time)
                currentBoardPaintRequiredtime += arr[i];
            else{
                paintersCount++;
                currentBoardPaintRequiredtime = arr[i];
            }
        }
        return paintersCount;
    }

    public static void main(String[] args) {
        int n = 6, k = 2;
        int[] arr = {2, 1, 5, 6, 2, 3};

        System.out.printf("Minimum time required to paint using brute force approach: %d\n", paintPartitionsBruteForce(arr, n, k));
        System.out.printf("Minimum time required to paint using optimal approach: %d\n", paintPartitionsOptimal(arr, n, k));
    }

}
