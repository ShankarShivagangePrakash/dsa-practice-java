package sorting;

/*
Problem:
    https://takeuforward.org/sorting/selection-sort-algorithm/

 */
public class SelectionSort {

    /*
    Approach:
        we start sorting the elements in increasing order from the front of the array
        first we place the smallest element at array[0] and we don't touch that index
        next we consider arr[1] to arr[n-1] means remaining array, and we find the smallest in this range, and we put it at array[1] and we don't touch this index anytime in future
        similarly now consider arr[2] to arr[n-1], third smallest in the entire array or smallest in this range will go to arr[2]
        ...
        similarly we keep repeating the same process
        when the range is array[n-2] to array[n-1], means last two elements - when we find the smallest in between these two and swap
        by default largest will go to last index and second largest will go to array[n-2] means both the elements will be sorted,
        so we need not form a range for last element,
        so we have to run a loop from 0 to n-2 (outer loop) - because only for these ranges we need to run and swap.

        Time complexity: O(n^2), (where N = size of the array), for the best, worst, and average cases.
        Reason: If we carefully observe, we can notice that the outer loop, say i,
        is running from 0 to n-2 i.e. n-1 times, and for each i, the inner loop j runs from i to n-1.
        For, i = 0, the inner loop runs n-1 times,
        for i = 1, the inner loop runs n-2 times, and so on.
        So, the total steps will be approximately the following: (n-1) + (n-2) + (n-3) + ……..+ 3 + 2 + 1.
        The summation is approximately the sum of the first n natural numbers i.e. (n*(n+1))/2.
        The precise time complexity will be O(n^2/2 + n/2). Previously,
        we have learned that we can ignore the lower values as well as the constant coefficients.
        So, the time complexity is O(n^2). Here the value of n is N i.e. the size of the array.

        Space Complexity: O(1)
     */
    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            int minimumValueIndex = i;
            for(int j = i; j < arr.length; j++){
                if(arr[j] < arr[minimumValueIndex]){
                    minimumValueIndex = j;
                }
            }

            // swap the element at minimumValueIndex with `i`
            // so array gets sorted from the front in increasing order
            int temp = arr[i];
            arr[i] = arr[minimumValueIndex];
            arr[minimumValueIndex] = temp;
        }
    }

    public static void main(String[] args){

        int[] arr = {13, 46, 24, 52, 20, 9};
        selectionSort(arr);

        System.out.println("Array after sorting");
        for(int i = 0; i < arr.length; i++){
            System.out.printf(arr[i] + " ");
        }
    }
}
