package stack_n_queue.monotonic;

/*
Problem:
    https://takeuforward.org/data-structure/trapping-rainwater/
    https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {

    /*
    Approach:
        Explained in detail, in notes

        Time Complexity: O(3n)
            O(n) to calculate prefixMax array
            O(n) to calculate suffixMax array
            O(n) for main logic

        Space Complexity: O(2n)
            O(n) for prefixMax array
            O(n) for suffixMax array
     */
    public static int trapRainWaterBruteForce(int[] arr){
        int n = arr.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        int total = 0;

        prefixMax[0] = arr[0];
        for(int i = 1; i < n; i++){
            prefixMax[i] = Math.max(prefixMax[i-1], arr[i]);
        }

        suffixMax[n-1] = arr[n-1];
        for(int i = n-2; i >=0; i--){
            suffixMax[i] = Math.max(suffixMax[i+1], arr[i]);
        }

        for(int i = 0; i < n; i++){
            int leftMax = prefixMax[i];
            int rightMax = suffixMax[i];

            if(arr[i] < leftMax && arr[i] < rightMax){
                total = total + Math.min(leftMax, rightMax) - arr[i];
            }
        }
        return total;
    }

    /*
    Approach:
        Explained in detail, in notes

        Time Complexity: O(n)
            only one loop with two pointer l and r
        Space Complexity: O(1)
     */
    public static int trapRainWaterOptimal(int[] arr){
        int l = 0;
        int r = arr.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int total = 0;

        while( l < r){
            if(arr[l] <= arr[r]){
                if(leftMax > arr[l])
                    total = total + (leftMax - arr[l]);
                else
                    leftMax = arr[l];

                l++;
            }
            else{
                if(rightMax > arr[r]){
                    total = total + (rightMax - arr[r]);
                }
                else
                    rightMax = arr[r];

                r--;
            }
        }
        return total;
    }
}
