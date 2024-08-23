package arrays.hard;

import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/length-of-the-longest-subarray-with-zero-sum/
    https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1?category%5B%5D=Hash&company%5B%5D=Amazon&page=1&query=category%5B%5DHashcompany%5B%5DAmazonpage1company%5B%5DAmazoncategory%5B%5DHash&utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=largest-subarray-with-0-sum
 */
public class LongestSubArrayWithSum0 {

    public static int longestSubArrayWithSum0Optimal(int[] arr){
        int maxLen = 0;
        int sum = 0;

        HashMap<Integer, Integer> prefixSum = new HashMap<>();

        for(int i = 0; i < arr.length; i++){
            sum += arr[i];

            if(sum == 0){
                maxLen = Math.max(maxLen, i + 1);
            }
            // since rem will be always same as sum; we can simply search for sum.
            //int rem = sum - 0;
            if (prefixSum.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - prefixSum.get(sum));
            }

            if(!prefixSum.containsKey(sum)){
                prefixSum.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args){
        int a[] = {9, -3, 3, -1, 6, -5};
        System.out.printf("Longest sub array with sum 0 is %d\n", longestSubArrayWithSum0Optimal(a));
    }
}
