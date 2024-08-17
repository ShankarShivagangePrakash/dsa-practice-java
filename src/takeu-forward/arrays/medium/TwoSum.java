package arrays.medium;

import java.util.Arrays;
import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/two-sum-check-if-a-pair-with-given-sum-exists-in-array/
    https://leetcode.com/problems/two-sum/description/

    There are two variants to this problem.
        - just return true or false.
        - return the index of the pair
 */
public class TwoSum {

    /*
    Approach:
        First, we will use a loop(say i) to select the indices of the array one by one.
        For every index i,
        we will traverse through the remaining array using another loop(say j)
        to find the other number such that the sum is equal to the target (i.e. arr[i] + arr[j] = target).

        Time complexity: O(n^2)
        Space complexity: O(1)
     */
    public static String twoSumVariant1BruteForce(int n, int[] arr, int target) {
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if(arr[i] + arr[j] == target)
                    return "YES";
            }
        }
        return "NO";
    }

    /*
    Approach:
        First, we will use a loop(say i) to select the indices of the array one by one.
        For every index i,
        we will traverse through the remaining array using another loop(say j)
        to find the other number such that the sum is equal to the target (i.e. arr[i] + arr[j] = target).

        Time complexity: O(n^2)
        Space complexity: O(1)
     */
    public static int[] twoSumVariant2BruteForce(int n, int[] arr, int target) {
        int[] ans = new int[2];
        ans[0] = ans[1] = -1;
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                if(arr[i] + arr[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }

    /*
    Approach:
        Using hashing.
        We will select the element of the array one by one using a loop(say i).
        Then we will check if the other required element(i.e. target-arr[i]) exists in the hashMap.
        If that element exists, then we will return “YES” for the first variant or we will return the current index i.e.
        i, and the index of the element found using map i.e. mp[target-arr[i]].
        If that element does not exist, then we will just store the current element in the hashMap along with its index.
        Because in the future, the current element might be a part of our answer.

        Time complexity: O(n)
        Space complexity: O(n)
     */
    public static String twoSumVariant1BetterApproach(int n, int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            int moreNeeded = target - arr[i];
            if(map.containsKey(moreNeeded)){
                return "YES";
            }
            map.put(arr[i], i);
        }
        return "NO";
    }

    /*
    Approach:
        Using hashing
        We will select the element of the array one by one using a loop(say i).
        Then we will check if the other required element(i.e. target-arr[i]) exists in the hashMap.
        If that element exists, then we will return “YES” for the first variant or we will return the current index i.e.
        i, and the index of the element found using map i.e. mp[target-arr[i]].
        If that element does not exist, then we will just store the current element in the hashMap along with its index.
        Because in the future, the current element might be a part of our answer.

        Time complexity: O(n)
        Space complexity: O(n)
     */
    public static int[] twoSumVariant2BetterApproach(int n, int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        ans[0] = ans[1] = -1;
        for(int i = 0; i < arr.length; i++){
            int moreNeeded = target - arr[i];
            if(map.containsKey(moreNeeded)){
                ans[0] = map.get(moreNeeded);
                ans[1] = i;
                return ans;
            }
            map.put(arr[i], i);
        }
        return ans;
    }

    /*
    Approach:
        Pre requisite for this approach is array has to be sorted.

        We will keep a left pointer at the first index and a right pointer at the last index.
        Now until left < right, we will check the sum of arr[left] and arr[right].
        Now if the sum < target, we need bigger numbers, and so we will increment the left pointer.
        But if sum > target, we need to consider lesser numbers, and so we will decrement the right pointer.

        If sum == target we will return either “YES” or the indices as per the question.
        But if the left crosses the right pointer, we will return “NO” or {-1, -1}.

        The steps are the following:

        We will sort the given array first.
        Now, we will take two pointers i.e. left, which points to the first index, and right, which points to the last index.
        Now using a loop we will check the sum of arr[left] and arr[right] until left < right.
        If arr[left] + arr[right] > sum, we will decrement the right pointer.
        If arr[left] + arr[right] < sum, we will increment the left pointer.
        If arr[left] + arr[right] == sum, we will return the result.
        Finally, if no results are found we will return “No” or {-1, -1}.

        Time Complexity: O(N) + O(N*logN), where N = size of the array.
            Reason: The loop will run at most N times. And sorting the array will take N*logN time complexity.

        Space Complexity: O(1) as we are not using any extra space.

            Note: Here we are distorting the given array. So, if we need to consider this change, the space complexity will be O(N).
     */
    public static String twoSumVariant1Optimal(int n, int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length -1;

        while( left < right){
            int sum = arr[left] + arr[right];
            if(sum == target){
                return "YES";
            } else if(sum < target){
                left++;
            } else
                right--;
        }
        return "NO";
    }

    /*
    Approach:
        Pre requisite for this approach is array has to be sorted.

        We will keep a left pointer at the first index and a right pointer at the last index.
        Now until left < right, we will check the sum of arr[left] and arr[right].
        Now if the sum < target, we need bigger numbers, and so we will increment the left pointer.
        But if sum > target, we need to consider lesser numbers, and so we will decrement the right pointer.

        If sum == target we will return either “YES” or the indices as per the question.
        But if the left crosses the right pointer, we will return “NO” or {-1, -1}.

        The steps are the following:

        We will sort the given array first.
        Now, we will take two pointers i.e. left, which points to the first index, and right, which points to the last index.
        Now using a loop we will check the sum of arr[left] and arr[right] until left < right.
        If arr[left] + arr[right] > sum, we will decrement the right pointer.
        If arr[left] + arr[right] < sum, we will increment the left pointer.
        If arr[left] + arr[right] == sum, we will return the result.
        Finally, if no results are found we will return “No” or {-1, -1}.

        Time Complexity: O(N) + O(N*logN), where N = size of the array.
            Reason: The loop will run at most N times. And sorting the array will take N*logN time complexity.

        Space Complexity: O(1) as we are not using any extra space.

            Note: Here we are distorting the given array. So, if we need to consider this change, the space complexity will be O(N).
     */
    public static int[] twoSumVariant2Optimal(int n, int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length -1;
        int[] ans = new int[2];
        ans[0] = ans[1] = -1;

        while( left < right){
            int sum = arr[left] + arr[right];
            if(sum == target){
                ans[0] = left;
                ans[1] = right;
                return ans;
            } else if(sum < target){
                left++;
            } else
                right--;
        }
        return ans;
    }



    public static void main(String[] args) {
        int n = 5;
        int[] arr = {2, 6, 5, 8, 11};
        int target = 14;
        System.out.println("Two sum variant 1 using brute force approach: " + twoSumVariant1BruteForce(n, arr, target));
        System.out.println("Two sum variant 1 using better approach(Hashing): " + twoSumVariant1BetterApproach(n, arr, target));
        System.out.println("Two sum variant 1 using better approach(Hashing): " + twoSumVariant1Optimal(n, arr, target));
        int[] ans = twoSumVariant2BruteForce(n, arr, target);
        int[] ans2 = twoSumVariant2BetterApproach(n, arr, target);
        int[] ans3 = twoSumVariant2Optimal(n, arr, target);
        System.out.printf("This is the answer for variant 2 using brute force approach: [%d, %d]\n", ans[0], ans[1]);
        System.out.printf("This is the answer for variant 2 using better approach(Hashing): [%d, %d]\n", ans2[0], ans2[1]);
        System.out.printf("This is the answer for variant 2 using better approach(Hashing): [%d, %d]\n", ans3[0], ans3[1]);
    }
}
