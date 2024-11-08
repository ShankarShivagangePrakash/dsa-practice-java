package greedy.easy;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/find-minimum-number-of-coins/
    https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 */
public class FindMinimumNumberOfCoins {

    /*
    Approach:
        Explained in notes, check

        Time Complexity:O(V)
            say the give value `v` is 9
            then you would need to give 1 rupee 9 coins
            you will iterate for 9 times so O(v)
            this is the worst case time complexity

            when V increases, time complexity decreases

        Space Complexity:O(1)
            even though we have a array, the array size is small( less than 10 elements)
            and it is static, fixed for all inputs.
     */
    public static int findMinimumNumberOfCoins(int val){
        int V = 49;
        ArrayList < Integer > ans = new ArrayList < > ();
        int coins[] = {1, 2, 5, 10, 20, 50, 100, 500, 1000};
        int count = 0;
        int n = coins.length;
        for (int i = n - 1; i >= 0; i--) {
            while (V >= coins[i]) {
                V -= coins[i];
                // this ans arraylist is not actually required, if interviewer asks to give the denominations used to represents change, return it
                ans.add(coins[i]);
                count++;
            }
        }
        return count;
    }
}
