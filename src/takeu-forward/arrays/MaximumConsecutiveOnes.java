package arrays;

/*
Problem:
    https://takeuforward.org/data-structure/count-maximum-consecutive-ones-in-the-array/
    https://leetcode.com/problems/max-consecutive-ones/description/
 */
public class MaximumConsecutiveOnes {

    /*
    Approach:
        We start traversing from the beginning of the array. Since we can encounter either a 1 or 0 there can be two situations:-

        If  the value at the current index is equal to 1 we increase the value of count by one.
        After updating the count variable if it becomes more than the max_count  update the max_count.
        If the value at the current index is equal to zero we make the variable count as 0 since there are no more consecutive ones.

        Time complexity: O(n)
        Space complexity: O(1)
     */
    public static int findMaxConsecutiveOnes(int[] arr){
        int cnt = 0, max_count = 0;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 1) {
                cnt++;
            } else{
                cnt = 0;
            }
            max_count = cnt > max_count ? cnt : max_count;
        }
        return max_count;
    }

    public static void main(String[] args) {
        int nums[] = { 1, 1, 0, 1, 1, 1 };
        int ans = findMaxConsecutiveOnes(nums);
        System.out.println("The maximum  consecutive 1's are " + ans);
    }
}
