package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/data-structure/peak-element-in-array/
    https://leetcode.com/problems/find-peak-element/description/
 */
public class FindPeakElement {

    /*
    Problem statement:
        Peak element is an element which is greater than element on its both sides
        First and Last elements in the array has to be treated specially, for them, we have to imaginary add one more elements on their other side i.e. -infinitiy
        any element will be greater than this, so that first element is greater than second element in the array then it will be Peak element
        similarly, for last element in the array, if it is greater than array[n-2], then it will be peak element as well

        So there can be many peak elements, we have to find anyone such element and return

    Approach:
        Brute Force:
            In case of brute force, we will traverse array entirely and check if it is greater than elements on it either side -then return that element as peak
            But this is O(n) solution.
            We can improvise

        Optimal Solution:
            Using Binary search
                To know why we are using binary search, understand the nature of problem
                if there is a peak means, at least some part of the array has to be sorted, and we need to search for a peak, which is searching for an element
                to search for an element in sorted array, binary search is an obvious choice, so that is the reason we are trying with binary search.

            Before deep diving into binary search approach, we need to handle edge cases
                - what if array has only one element, then return that as peak element
                - how to handle, if first or last element of the array is peak?
                    - add a special if condition for arr[0] if it is greater than arr[1] - then return arr[0] as peak
                           similarly if arr[n-1] > arr[n-2] then arr[n-1] is peak

           Now, if any of the above cases are not true, then we need to find peak the remaining array
                initialize low = 1, high = n-2, since we have already checked arr[0] and arr[n-1] cannot be peak
                    now compute mid, if mid is greater than element at index [mid-1] and [mid+1], then that is the peak
                    else, we need to move to either left or right?, where to move?
                        if arr[low] <= arr[mid], it means that left side is sorted, and we assume that let's move to right, and we might find some peak there
                        else means, we imagine that right side is sorted, let's check in left sub array
                        it works for array having multiple peak elements also, it will return some peak - not the highest peak, but the problem statement is to find some peak - we are good.

          Time Complexity: O(log n)
          Space Complexity: O(n)
     */
    public static int findPeak(int[] arr){
        int n = arr.length;

        if(n == 1)
            return 0;

        if(arr[0] > arr[1])
            return 0;

        if(arr[n-1] > arr[n-2])
            return n-1;

        int low = 1, high = n-2;

        while(low <= high){
            int mid = (low + high)/2;

            if(arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1])
                return mid;

            // we assume left sub array is sorted,
            // note: this left sub array might have some peak, but we still move to find right sub array to find some others peak
            if(arr[low] <= arr[mid]){
                low = mid + 1;
            }
            // else - we check in left sub array
            // no condition in else will handle one unique case
            // say the array is [1, 2, 1, 3, 1] and mid = 2
            // so arr[mid] = arr[2] is not greater than arr[1] or arr[2] in this case where to move
            // we can move anywhere, that is why in else case we are not explicitly checking is arr[mid] is it greater than right sub array.
            else{
                high = mid -1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 1};
        System.out.printf("Peak element index in the array is %d\n", findPeak(arr));
    }
}
