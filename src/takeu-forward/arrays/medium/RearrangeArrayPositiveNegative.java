package arrays.medium;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/arrays/rearrange-array-elements-by-sign/
    https://leetcode.com/problems/rearrange-array-elements-by-sign/description/
 */
public class RearrangeArrayPositiveNegative {

    /*
    Approach:
        In this simple approach, since the number of positive and negative elements are the same, we put positives into an array called “pos” and negatives into an array called “neg”.
        After segregating each of the positive and negative elements, we start putting them alternatively back into array A.
        Since the array must begin with a positive number and the start index is 0,
        so all the positive numbers would be placed at even indices (2*i) and negatives at the odd indices (2*i+1),
        where i is the index of the pos or neg array while traversing them simultaneously.

        Time Complexity: O(n + n/2)
        Space Complexity: O(n)
     */
    public static int[] rearrangeBruteForce(int[] arr){
        ArrayList<Integer> positiveArray = new ArrayList<>();
        ArrayList<Integer> negativeArray = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            if(arr[i] >= 0){
                positiveArray.add(arr[i]);
            } else{
                negativeArray.add(arr[i]);
            }
        }

        for(int i = 0; i < arr.length/2; i++){
            arr[2*i] = positiveArray.get(i);
            arr[2 * i + 1] = negativeArray.get(i);
        }
        return arr;
    }

    /*
    Approach:
        In this optimal approach, we will try to solve the problem in a single pass and try to arrange the array elements in the correct order in that pass only.
        We know that the resultant array must start from a positive element so we initialize the positive index as 0 and
        negative index as 1 and start traversing the array such that whenever we see the first positive element, it occupies the space at 0 and
        then posIndex increases by 2 (alternate places).
        Similarly, when we encounter the first negative element, it occupies the position at index 1, and then each time we find a negative number,
        we put it on the negIndex, and it increments by 2.
        When both the negIndex and posIndex exceed the size of the array, we see that the whole array is now rearranged alternatively according to the sign.

        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    public static int[] rearrangeOptimal(int[] arr){
        int positiveIndex = 0, negativeIndex = 1;

        int[] ans = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            if(arr[i] > 0){
                ans[positiveIndex] = arr[i];
                positiveIndex += 2;
            } else{
                ans[negativeIndex] = arr[i];
                negativeIndex += 2;
            }
        }
        return  ans;
    }

    //TODO: Handle, What if there are no equal number of elements.
    public static int[] rearrangeOptimalAdditionalScenario(int[] arr){
        int[] ans = new int[arr.length];

        return  ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,-4,-5, -8, -10, 9, 11};
        int[] arr2 = {1,2,-4,-5, -8, -10, 9, 11};
        int[] arr3 = {1,2,-4,-5, -8, -10, 9, 11, 14,15, 16, -17};
        int[] ans = rearrangeBruteForce(arr);
        int[] ans2 = rearrangeOptimal(arr2);
        int[] ans3 = rearrangeOptimalAdditionalScenario(arr3);

        System.out.println("Array elements rearranged using BruteForce Approach:");
        for(int i = 0; i < ans.length; i++){
            System.out.printf("%d ", ans[i]);
        }

        System.out.println("\nArray elements rearranged using optimal Approach:");
        for(int i = 0; i < ans2.length; i++){
            System.out.printf("%d ", ans2[i]);
        }
    }
}
