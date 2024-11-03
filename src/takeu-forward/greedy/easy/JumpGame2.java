package greedy.easy;

/*
Problem:
    https://leetcode.com/problems/jump-game-ii/description/
 */
public class JumpGame2 {

    public static int jumpGame2Bruteforce(int[] arr){
        return jumpGame2Recursive(arr, 0, 0);
    }

    /*
    Approach:
        Explores all possible ways
        and finds out number of jumps required for each ways
        among them chooses the minimum

        Time Complexity: O(n^n)
            at each index, we might have n ways
            we have to explore all n ways, for each index
            there will be n indices
            so n^n

        Space Complexity: O(n)
            At any point of time, there can be maximum of n recursive calls waiting in the stack
            so O(n)
     */
    public static int jumpGame2Recursive(int[] arr, int index, int jumps){

        /* base case, if you have reached end of the array of crossed it,
         return jumps i.e. the number of jumps you have taken to reach the end by following curent path*/
        if(index >= arr.length -1)
            return jumps;

        int minimumJumps = Integer.MAX_VALUE;

        /* at index `i`, you can take jump ranging from 1 to arr[i],
         try out all possible ways and yield minimum of all possible ways*/
        for(int i = 1; i <= arr[index]; i++){
            /* check the parameters sent for jumpGame2Recursive()
             second parameter is index + i
             index represents the current possition where you are,
             from that position you can take jumps ranging from 1 to arr[i] which will be represented using i
             so if you take a jump of i steps, then you will be on index (new index) = index + i
             last parameter, number of jumps you have taken, it will increment by 1*/
            minimumJumps = Math.min(minimumJumps, jumpGame2Recursive(arr,index + i, jumps + 1));
        }
        return minimumJumps;
    }

}
