package dynamic_programming.lis;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/longest-string-chain-dp-45/
    https://leetcode.com/problems/longest-string-chain/description/
 */
public class LongestStringChain {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N*N * l)
            We are setting up two nested loops and the compare function can be estimated to l,
            where l is the length of the longest string in the words [ ] array.
            Also, we are sorting so the time complexity will be (N^2 * l + NlogN)

        Space Complexity: O(N)
            We are only using a single array of size n.
     */
    public int longestStrChain(String[] words) {
        // sorts the string array based on size
        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());

        int n = words.length;
        int longestStringChainLength = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for(int i = 0; i < n; i++){
            for(int prevIndex = 0; prevIndex < i; prevIndex++){

                String s1 = new String(words[i]);
                String s2 = new String(words[prevIndex]);
                if(compare(s1, s2) && 1 + dp[prevIndex] > dp[i]){
                    dp[i] = 1 + dp[prevIndex];
                    longestStringChainLength = Math.max(longestStringChainLength, dp[i]);
                }
            }
        }
        return longestStringChainLength;
    }

    public boolean compare(String s1, String s2){
        if(s1.length() - s2.length() > 1)
            return false;

        // Convert strings to character arrays and sort them
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int i = 0, j = 0;

        while (i < arr1.length) {
            if (j < arr2.length && arr1[i] == arr2[j]) {
                i++;
                j++;
            } else {
                i++;
            }

        }
        return (i == s1.length() && j == s2.length()) ? true : false;
    }
}
