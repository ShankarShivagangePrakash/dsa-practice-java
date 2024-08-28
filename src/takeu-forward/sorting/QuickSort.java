package sorting;

/*
Problem:
    https://takeuforward.org/data-structure/quick-sort-algorithm/
    https://www.geeksforgeeks.org/problems/quick-sort/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=quick-sort
 */
public class QuickSort {

    public static int partition(int[] arr, int low, int high){
        int pivot = low;
        int i = low;
        int j = high;

        while(i < j){
            // keep moving i till you find larger element than pivot
            while (arr[i] <= arr[pivot] && i <= high -1){
                i++;
            }
            // keep moving j till you find smaller element than pivot
            while(arr[j] > arr[pivot] && j >= low+1){
                j--;
            }
            // if i position is less than j, after encountering such elements, swap them.
            if(i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[pivot];
        arr[pivot] = arr[j];
        arr[j] = temp;

        return j;
    }

    /*
    Approach:
        Check notes

        Time Complexity: O(n log n)
        Space Complexity: O(1)
     */
    public static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex -1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    public static void main(String[] args){
        int[] arr = {4, 1, 3, 9, 7};

        quickSort(arr,0, arr.length-1);

        System.out.println("Array after sorting");
        for(int i = 0; i < arr.length; i++){
            System.out.printf(arr[i] + " ");
        }
        System.out.println();
    }
}
