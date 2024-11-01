package sliding_window_two_pointer;

/*
Problem:
    https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/
 */
public class MaximumPointsToObtainFromCards {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2* k)
            Two for loops running for k times

        Space Complexity: O(1)
     */
    public static int maxPointsToObtainFromCardsOptimal(int[] arr, int k){
        int leftSum = 0, rightSum = 0, maxSum = 0;

        // calculate the total points by considering all k cards from left.
        for(int i = 0; i < k; i++)
            leftSum = leftSum + arr[i];

        // we can set leftSum(total points considering all k element from left) as maxSum for time being
        maxSum = leftSum;

        // remaining combinations.
        int rightIndex = arr.length - 1;
        for(int i = k-1; i >= 0; i--){
            leftSum = leftSum - arr[i];
            rightSum = rightSum + arr[rightIndex--];

            maxSum = Math.max(maxSum, leftSum + rightSum);
        }
        return maxSum;
    }
}
