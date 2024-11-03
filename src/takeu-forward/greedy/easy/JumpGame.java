package greedy.easy;

/*
Problem:
    https://takeuforward.org/Greedy/jump-game-i
    https://leetcode.com/problems/jump-game/description/
 */
public class JumpGame {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static boolean jumpGameOptimal(int[] arr){
        int maxIndex = 0;

        for(int i = 0; i < arr.length; i++){
            if(maxIndex < i)
                return false;

            maxIndex = Math.max(maxIndex, i + arr[i]);
        }
        return true;
    }
}
