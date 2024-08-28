package sorting;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/merge-sort-algorithm/
    https://www.geeksforgeeks.org/problems/merge-sort/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=merge-sort
 */
public class MergeSortAlgorithm {

    /*
    Approach:
        Check Notes

        Time Complexity: O(n log n)
                check notes

        Space Complexity: O(n)
     */
    public static void mergeSort(int[] arr, int low, int high){
        if(low == high)
            return;

        int mid = (low + high)/2;

        mergeSort(arr, low, mid);
        mergeSort(arr, mid+1, high);
        merge(arr, low, mid, high);
    }

    // note: Both low and high are valid index in the array, so we have to run loop till <=high in all cases.
    public static void merge(int[] arr, int low, int mid, int high){
        int left = low;
        // mid will point to end of left sub array, but we want right to point to first element of right sub array so adding +1
        int right = mid+1;

        ArrayList<Integer> temp = new ArrayList<>();
        // two pointer approach to merge two sorted arrays, so that the final array will also be sorted.
        while(left <= mid && right <= high){
            if(arr[left] <= arr[right]){
                // add left element to temp arraylist
                temp.add(arr[left]);
                left++;
            } else{
                temp.add(arr[right]);
                right++;
            }
        }

        // after executing the above while loop, there can be scenario where any one of the sub array is not completely traversed.
        while(left <= mid){
            temp.add(arr[left]);
            left++;
        }
        while(right <= high){
            temp.add(arr[right]);
            right++;
        }

        /* we need to place these arraylist elements in the correct position of the sub arrays.
         sub array position need not start from 0, so it has to start from low and go till high(we have to include even end index) because will pass argument for high as n-1 initially
         so every sub array high position is valid in array, so we have to run the loop till <= high
         but low can be greater than zero, but arraylist index will start from 0
         in order to get 0 when low is not zero, just do i-low, so low - low = 0
         when i increments, i will be (low+1) - (i- low) = (low+1 - low) = 1
         that's how we can access every index of arraylist, while storing that value to correct index of original array. */
        for(int i = low; i <= high; i++){
            arr[i] = temp.get(i- low);
        }
    }

    public static void main(String[] args){

        int[] arr = {13, 46, 24, 52, 20, 9};
        int low = 0, high = arr.length -1;
        mergeSort(arr, low, high);

        System.out.println("Array after sorting");
        for(int i = 0; i < arr.length; i++){
            System.out.printf(arr[i] + " ");
        }
        System.out.println();
    }
}
