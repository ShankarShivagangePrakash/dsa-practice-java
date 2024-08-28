package sorting;

/*
Problem:
    https://takeuforward.org/data-structure/bubble-sort-algorithm/
    https://www.geeksforgeeks.org/problems/bubble-sort/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=bubble-sort
 */
public class BubbleSortRecursive {

    /*
    Approach:
        The algorithm steps are as follows:

        Refer BubbleSort.java first
        it is an improvisation of it to convert it into recursive way

        First, call the recursive function with the given array and the range of n(size of the array).
        Inside that recursive function, repeatedly swap 2 adjacent elements if arr[j] > arr[j+1].
        Here, the maximum element of the unsorted array reaches the end of the unsorted array after each recursive call.
        Each time after step 2, call the recursion again decreasing the range by 1.
        The recursion will continue until the range(i.e. the size) of the array is reduced to 1.
        Base Case: if(n == 1) return;

        Time Complexity:
            Worst case and average case:
                O(n^2)
            Best case:
                O(n) - if input array is sorted,
                    with small modification (using didWeSwap boolean variable), we can exit from the algorithm in O(n)

        Space Complexity: O(1)
     */
    public static void bubbleSort(int[] arr, int n){
        // stopping condition for bubble sort is when the array range is only one element
        if(n == 1){
            return;
        }

        boolean didWeSwap = false;
        // here previously in iterative approach j < i was there
        // value for `i` initially will be n-1, then it will reduce to n-2, ... becomes 1
        // same we have to do using n parameter of the method. it will represent `i`
        // we keep swapping and start storing the largest element to the right of the array.
        for(int j = 0; j <  n-1; j++){
            if(arr[j] > arr[j+1]) {
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
                didWeSwap = true;
            }
        }
        // there is no swapping means the array is already sorted. so in this case break.
        if(!didWeSwap) {
            return;
        }
        // since we are sorting from right of the sub array, those indexes has to be excluded from the next recursive call
        // so reduce n by n-1
        bubbleSort(arr, n-1);
    }
    public static void main(String[] args){

        int[] arr = {13, 46, 24, 52, 20, 9};
        bubbleSort(arr, arr.length);

        System.out.println("Array after sorting");
        for(int i = 0; i < arr.length; i++){
            System.out.printf(arr[i] + " ");
        }
        System.out.println();
    }
}
