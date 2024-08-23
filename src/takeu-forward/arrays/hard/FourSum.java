package arrays.hard;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/4-sum-find-quads-that-add-up-to-a-target-value/
    https://leetcode.com/problems/4sum/
 */
public class FourSum {

    // It has bruteforce and better solutions
    // brute force uses loops 3-4
    // better approach uses set space complexity will not be O(1)
    // it is similar to how it is implemented in ThreeSum problem
    // refer take u forward article.

    /*
    Approach:
        Extension of Three sum problem
        The same two pointer approach optimal solution is extended and used here

        The steps are as follows:

        First, we will sort the entire array.
        We will use a loop(say i) that will run from 0 to n-1. This i will represent one of the fixed pointers.

        In each iteration, this value will be fixed for all different values of the rest of the 3 pointers.

        Inside the loop, we will first check if the current and the previous element is the same and if it is we will do nothing and continue to the next value of i.

        After checking inside the loop, we will introduce another fixed pointer j(runs from i+1 to n-1) using another loop.

        Inside this second loop, we will again check for duplicate elements and only perform any further operation if the elements are different.
        Inside the second loop, there will be 2 moving pointers i.e. k(starts from j+1) and l(starts from the last index).

        The pointer k will move forward and the pointer l will move backward until they cross each other while the value of i and j will be fixed.
        Now we will check the sum i.e. nums[i]+nums[j]+nums[k]+nums[l].

        If the sum is greater, then we need lesser elements and so we will decrease the value of l.
        If the sum is lesser than the target, we need a bigger value and so we will increase the value of k.

        If the sum is equal to the target, we will simply insert the quad i.e. nums[i], nums[j], nums[k], and nums[l]
        into our answer and move the pointers k and l skipping the duplicate elements(i.e. by checking the adjacent elements while moving the pointers).

        Finally, we will have a list of unique quadruplets.

        Time Complexity: O(n^3) + O(n log n)
        Space Complexity: O(number of quadruplets) - but this used to return the solution
                           if we are not considering it, then it will become O(1)
     */
    public static List<List<Integer>> fourSumOptimal(int[] arr, int target){
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(arr);
        int n = arr.length;
        for(int i = 0; i < n; i++){

            if( i > 0 && arr[i] == arr[i-1])
                continue;

            for(int j = i+1; j < n; j++){

                if(j > i+1 && arr[j] == arr[j-1])
                    continue;

                int k = j+1, l = n-1;

                while(k < l){
                    // here sum data type is considered to handle extreme large and small values
                    // similarly when we sum very large values as a + b + c + d it will give wrong result,
                    // so we are doing sum += a; then sum+= b;...
                    long sum = arr[i];
                         sum += arr[j];
                         sum += arr[k];
                         sum += arr[l];

                    if(sum < target)
                        k++;
                    else if(sum > target)
                        l--;
                    else{
                        List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k], arr[l]);
                        result.add(temp);
                        k++;
                        l--;
                        while(k < l && arr[k] == arr[k-1])
                            k++;
                        while(k < l && arr[l] == arr[l+1])
                            l--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] arr ={4, 3, 3, 4, 4, 2, 1, 2, 1, 1};
        int[] arr ={1000000000,1000000000,1000000000,1000000000};

        List<List<Integer>> result = fourSumOptimal(arr, -294967296);

        System.out.println("Four Sum result using optimal approach (Two pointers):" + result);
    }
}
