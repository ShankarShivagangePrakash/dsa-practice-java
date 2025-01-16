package dynamic_programming.introduction_to_dp.space_optimization;

/*
Problem:
    https://takeuforward.org/data-structure/dynamic-programming-introduction/
    https://www.geeksforgeeks.org/problems/introduction-to-dp/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=introduction-to-dp
 */
public class FibanocciNumber {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N)
            We are running a simple iterative loop

        Space Complexity: O(1)
     */
    static long bottomUp(int n) {
        if(n <= 1)
            return n;

        int prev = 1, prev2 = 0;
        for(int i = 2; i <= n; i++){
            int cur = prev + prev2;
            prev2 = prev;
            prev = cur;
        }

       return prev;
    }
}
