package arrays.medium;
/*
Problem:
    https://takeuforward.org/data-structure/sort-an-array-of-0s-1s-and-2s/
    https://leetcode.com/problems/sort-colors/description/
 */
public class SortArrayOf012 {

    /*
    Approach:
        Keeping count of values
        Since in this case there are only 3 distinct values in the array so it's easy to maintain the count of all, Like the count of 0, 1, and 2.
        This can be followed by overwriting the array based on the frequency(count) of the values.

        Take 3 variables to maintain the count of 0, 1 and 2.
        Travel the array once and increment the corresponding counting variables
        ( let's consider count_0 = a, count_1 = b, count_2 = c )

        In 2nd traversal of array, we will now overwrite the first ‘a’ indices / positions in array with ’0’, the next ‘b’ with ‘1’ and the remaining ‘c’ with ‘2’.

        Time Complexity: O(2n)
         O(N) + O(N), where N = size of the array. First O(N) for counting the number of 0’s, 1’s, 2’s, and second O(N) for placing them correctly in the original array.

        Space Complexity: O(1) as we are not using any extra space.
     */
    public static void sortArrayBruteForce(int[] arr, int n){
        int count0 = 0, count1 = 0, count2 = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0)
                count0++;
            else if(arr[i] == 1)
                count1++;
            else
                count2++;
        }
        for(int i = 0; i < count0; i++){
            arr[i] = 0;
        }
        for(int i = count0; i < count0 + count1; i++){
            arr[i] = 1;
        }
        for(int i = count0 + count1; i < n; i++){
            arr[i] = 2;
        }
    }

    /*
    Approach:
        This problem is a variation of the popular Dutch National flag algorithm.

        This algorithm contains 3 pointers i.e. low, mid, and high, and 3 main rules.  The rules are the following:

        arr[0….low-1] contains 0. [Extreme left part]
        arr[low….mid-1] contains 1.
        arr[high+1….n-1] contains 2. [Extreme right part], n = size of the array

        The steps will be the following:

        First, we will run a loop that will continue until mid <= high.
        There can be three different values of mid-pointer i.e. arr[mid]
        If arr[mid] == 0, we will swap arr[low] and arr[mid] and will increment both low and mid. Now the subarray from index 0 to (low-1) only contains 0.
        If arr[mid] == 1, we will just increment the mid-pointer and then the index (mid-1) will point to 1 as it should according to the rules.
        If arr[mid] == 2, we will swap arr[mid] and arr[high] and will decrement high. Now the subarray from index high+1 to (n-1) only contains 2.
        In this step, we will do nothing to the mid-pointer as even after swapping, the subarray from mid to high(after decrementing high) might be unsorted.
        So, we will check the value of mid again in the next iteration.
        Finally, our array should be sorted.

        Read notes, you will understand.

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static void sortArrayOptimal(int[] arr, int n){
        int low = 0, mid = 0, high = n -1;

        while(mid <= high){
            if(arr[mid] == 0){
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;

                low++;
                mid++;
            }
            else if(arr[mid] == 1){
                mid++;
            }
            //  other case means, if(arr[mid] == 2){
            else{
                int temp = arr[high];
                arr[high] = arr[mid];
                arr[mid] = temp;

                high--;
            }
        }
    }

    public static void main(String args[]) {
        int n = 6;
        int[] arr = {0, 2, 1, 2, 0, 1};
        sortArrayBruteForce(arr, n);
        System.out.println("After sorting using Brute force approach:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int[] arr2 = {0, 2, 1, 2, 0, 1};
        sortArrayOptimal(arr2, n);
        System.out.println("After sorting using optimal Approach (Dutch National Flag Algorithm):");
        for (int i = 0; i < n; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();

    }
}
