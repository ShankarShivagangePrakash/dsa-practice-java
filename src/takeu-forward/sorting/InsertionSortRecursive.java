package sorting;
/*
Problem:
    https://takeuforward.org/data-structure/insertion-sort-algorithm/
    https://www.geeksforgeeks.org/problems/insertion-sort/0?category%5B%5D=Algorithms&page=1&query=category%5B%5DAlgorithmspage1&utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=insertion-sort
 */
public class InsertionSortRecursive {

    /*
    Approach:
        Insertion sort iterative approach:
            Select an element in each iteration from the unsorted array(using a loop).
            Place it in its corresponding position in the sorted part and shift the remaining elements accordingly (using an inner loop and swapping).
            The “inner while loop” basically shifts the elements using swapping.

        so in summary, till we reach the end of the array from outer loop, we cannot say that any element is in correct position of the complete sorted array
        like how we say, in bubble and selection sort - that will not work here

        so the stopping condition is when we traverse every element from the outer loop,
        so we can say when i == n
            that can be the stopping or base condition.

        in simple words we can say that
            initially we consider the range as only one element
            after sorting it
            we increase the range of the sub array, to two elements - call same method
            then to three elements - call same method
            keep doing it, when the size of the sub array is greater than the number of elements
            it means that we have sorted the array

            stop. That is using the base condition.

        Time complexity:
            Worst case and average case:
                O(n^2)
                    Reason: If we carefully observe, we can notice that the outer loop,
                    say i, is running from 0 to n-1 i.e. n times, and for each i, the inner loop j runs from i to 1 i.e. i times.
                    For, i = 1, the inner loop runs 1 time, for i = 2, the inner loop runs 2 times, and so on.
                    So, the total steps will be approximately the following: 1 + 2 + 3 +......+ (n-2) + (n-1).
                    The summation is approximately the sum of the first n natural numbers i.e. (n*(n+1))/2.
                    The precise time complexity will be O(n^2/2 + n/2).
                    Previously, we have learned that we can ignore the lower values as well as the constant coefficients.
                    So, the time complexity is O(n^2). Here the value of n is N i.e. the size of the array.

           Best case:
                O(n)
                    when array is sorted, it doesn't enter while loop at all, it executes only outer for loop
                    which executes for n times.
                    So time complexity: O(n)

       Space Complexity: O(1)
     */
    public static void insertionSort(int[] arr, int i, int n){
        // here `i` is the range of the sub array, we are considering
        // when the range is whole array +1, it is the stopping condition

        if(i == n)
            return;

        // j = i means, we are considering the sub array from 0 to i
        int j = i;
        //Note: If array is already sorted, this while loop doesn't execute, only outer loop executes
        // so best case time complexity, when array is already sorted is O(n)
        while(j > 0 && arr[j] < arr[j-1]){
            // swap arr[j] and arr[j-1]
            int temp = arr[j];
            arr[j] = arr[j-1];
            arr[j-1] = temp;

            // decrement j, so that it can compare with next left cell value.
            j--;
        }
        insertionSort(arr, i+1, n);
    }

    public static void main(String[] args){

        int[] arr = {13, 46, 24, 52, 20, 9};
        insertionSort(arr, 0, arr.length);

        System.out.println("Array after sorting");
        for(int i = 0; i < arr.length; i++){
            System.out.printf(arr[i] + " ");
        }
        System.out.println();
    }
}
