package arrays.hard;

import java.util.ArrayList;
import java.util.Arrays;

public class CountReversePairs {

    /*
    Approach:
        The steps are as follows:

        First, we will run a loop(say i) from 0 to N-1 to select the a[i].
        As index j should be greater than index i, inside loop i, we will run another loop i.e. j from i+1 to N-1, and select the element a[j].
        Inside this second loop, we will check if a[i] > 2*a[j] i.e. if a[i] and a[j] can be a pair. If they satisfy the condition, we will increase the count by 1.
        Finally, we will return the count i.e. the number of such pairs.

        Time Complexity: O(n^2), where n = size of the given array.
        Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    public static int countReversePairsBruteForce(int[] arr){
        int count = 0;

        for(int i = 0; i < arr.length -1; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] > 2* arr[j]){
                    count++;
                }
            }
        }
        return count;
    }

     /*
    Approach:

        The steps are basically the same as they are in the case of the merge sort algorithm. The change will be just in the mergeSort() function:

        In order to count the number of pairs, we will keep a count variable, cnt, initialized to 0 beforehand inside the mergeSort().
        We will add the numbers returned by the previous mergeSort() calls.
        Before the merge step, we will count the number of pairs using a function, named countPairs().
        We need to remember that the left half starts from low and ends at mid, and the right half starts from mid+1 and ends at high.
        The steps of the countPairs() function will be as follows:

        We will declare a variable, cnt, initialized with 0.
        We will run a loop from low to mid, to select an element at a time from the left half.
        Inside that loop, we will use another loop to check how many elements from the right half can make a pair.
        Lastly, we will add the total number of elements i.e. (right-(mid+1)) (where right = current index), to the cnt and return it.

        Time Complexity: O(2N*logN), where N = size of the given array.

        Reason: Inside the mergeSort() we call merge() and countPairs() except mergeSort() itself.
        Now, inside the function countPairs(), though we are running a nested loop,
        we are actually iterating the left half once and the right half once in total.
        That is why, the time complexity is O(N). And the merge() function also takes O(N).
        The mergeSort() takes O(logN) time complexity. Therefore, the overall time complexity will be O(logN * (N+N)) = O(2N*logN).

        Space Complexity: O(N), as in the merge sort We use a temporary array to store elements in sorted order.
     */
    /*********************************************
     * TODO: Try again after learning Recursion and Merge Sort
     *********************************************/
    public static int countReversePairsOptimal(int[] arr){
        // Count the number of pairs:
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low >= high) return cnt;
        int mid = (low + high) / 2 ;
        cnt += mergeSort(arr, low, mid);  // left half
        cnt += mergeSort(arr, mid + 1, high); // right half
        cnt += countPairs(arr, low, mid, high); //Modification
        merge(arr, low, mid, high);  // merging sorted halves
        return cnt;
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array
        int left = low;      // starting index of left half of arr
        int right = mid + 1;   // starting index of right half of arr

        //storing elements in the temporary array in a sorted manner//

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        // if elements on the left half are still left //

        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        //  if elements on the right half are still left //
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // transfering all elements from temporary to arr //
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    // This method drives the reverse pair logic - see program again.
    private static int countPairs(int[] arr, int low, int mid, int high) {
        int right = mid + 1;
        int cnt = 0;
        for (int i = low; i <= mid; i++) {
            while (right <= high && arr[i] > 2 * arr[right]) right++;
            cnt += (right - (mid + 1));
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 3, 1};
        // Doing deep copy of array using Arrays util
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int count = countReversePairsOptimal(arr);
        int count2 = countReversePairsOptimal(arr2);

        System.out.println("Count Reverse pairs in the array using brute force approach: " + count);
        System.out.println("Count Reverse pairs in the array using optimal approach: " + count2);
    }
}
