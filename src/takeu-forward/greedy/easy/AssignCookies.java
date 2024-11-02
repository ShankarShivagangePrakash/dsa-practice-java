package greedy.easy;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/Greedy/assign-cookies
    https://leetcode.com/problems/assign-cookies/description/
 */
public class AssignCookies {

    /*
    Approach:
        Explained in notes, check

        The reason for this program comes under greedy category is
        we are kanjoos in assigning cookies to kids, we try to give only how much is required

        Time Complexity: O(max(m, n))
        Space Complexity: O(1)
     */
    public static int assignCookiesOptimal(int[] greed, int[] cookies){
        int left = 0, right = 0;
        int n = greed.length, m = cookies.length;

        Arrays.sort(greed);
        Arrays.sort(cookies);

        while(left < m && right < n){

            // Check, if the current cookie can satisfy the current child's greed
            // if so, Move to the next child, as the current child is satisfied
            if(cookies[left] >= greed[right])
                right++;

            // Always move to the next cookie whether the current child was satisfied or not
            left++;
        }
        return right;
    }
}
