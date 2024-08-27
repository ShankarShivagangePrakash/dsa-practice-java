package sorting;

/*
Problem:
    https://takeuforward.org/data-structure/bubble-sort-algorithm/
    https://www.geeksforgeeks.org/problems/bubble-sort/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=bubble-sort
 */
public class BubbleSort {

    /*
    Approach:
        The algorithm steps are as follows:

        First, we will select the range of the unsorted array. For that, we will run a loop(say i) that will signify the last index of the selected range.
        The loop will run backward from index n-1 to 0(where n = size of the array).
        The value i = n-1 means the range is from 0 to n-1, and similarly, i = n-2 means the range is from 0 to n-2, and so on.

        Within the loop, we will run another loop(say j, runs from 0 to i-1 though the range is from 0 to i)
        to push the maximum element to the last index of the selected range, by repeatedly swapping adjacent elements.

        Basically, we will swap adjacent elements(if arr[j] > arr[j+1]) until the maximum element of the range reaches the end.

        Thus, after each iteration, the last part of the array will become sorted.
         Like: after the first iteration, the array up to the last index will be sorted,
         and after the second iteration, the array up to the second last index will be sorted, and so on.

        After (n-1) iteration, the whole array will be sorted.

        Note: Here, after each iteration, the array becomes sorted up to the last index of the range.
        That is why the last index of the range decreases by 1 after each iteration.
        This decrement is achieved by the outer loop and the last index is represented by variable i in the following code.
        And the inner loop(i.e. j) helps to push the maximum element of range [0….i] to the last index(i.e. index i).

        Time Complexity:
            Worst case and average case:
                O(n^2)
            Best case:
                O(n) - if input array is sorted,
                    with small modification (using didWeSwap boolean variable), we can exit from the algorithm in O(n)

        Space Complexity: O(1)
     */
    public static void bubbleSort(int[] arr){
        for(int i = arr.length -1; i > 0; i--){
            boolean didWeSwap = false;
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    didWeSwap = true;
                }
            }
            // there is no swapping means the array is already sorted. so in this case break.
            if(!didWeSwap) {
                break;
            }
        }
    }
    public static void main(String[] args){

        int[] arr = {13, 46, 24, 52, 20, 9};
        bubbleSort(arr);

        System.out.println("Array after sorting");
        for(int i = 0; i < arr.length; i++){
            System.out.printf(arr[i] + " ");
        }
        System.out.println();
    }
}
