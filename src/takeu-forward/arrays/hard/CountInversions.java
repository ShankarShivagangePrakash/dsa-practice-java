package arrays.hard;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/count-inversions-in-an-array/
    https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=inversion-of-array
 */
public class CountInversions {

    /*
    Approach:
        The steps are as follows:

        First, we will run a loop(say i) from 0 to N-1 to select the first element in the pair.
        As index j should be greater than index i, inside loop i, we will run another loop i.e. j from i+1 to N-1.
        Inside this second loop, we will check if a[i] > a[j]
        i.e. if a[i] and a[j] can be a pair. If they satisfy the condition, we will increase the count by 1.
        Finally, we will return the count i.e. the number of such pairs.

        Time Complexity: O(n^2), where n = size of the given array.
        Space Complexity: O(1) as we are not using any extra space to solve this problem.
     */
    public static int countInversionsBruteForce(int[] arr){
        int count = 0;

        for(int i = 0; i < arr.length -1; i++){
            for(int j = i+1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    count++;
                }
            }
        }
        return count;
    }

    /*
    Approach:
        he steps are basically the same as they are in the case of the merge sort algorithm.
        The change will be just a one-line addition inside the merge() function. Inside the merge(), we need to add the number of pairs to the count when a[left] > a[right].

        The steps of the merge() function were the following:

        In the merge function, we will use a temp array to store the elements of the two sorted arrays after merging.
        Here, the range of the left array is low to mid and the range for the right half is mid+1 to high.
        Now we will take two pointers left and right, where left starts from low and right starts from mid+1.
        Using a while loop( while(left <= mid && right <= high)), we will select two elements, one from each half,
        and will consider the smallest one among the two. Then, we will insert the smallest element in the temp array.
        After that, the left-out elements in both halves will be copied as it is into the temp array.
        Now, we will just transfer the elements of the temp array to the range low to high in the original array.
        Modifications in merge() and mergeSort():

        In order to count the number of pairs, we will keep a count variable, cnt, initialized to 0 beforehand inside the merge().
        While comparing a[left] and a[right] in the 3rd step of merge(), if a[left] > a[right], we will simply add this line:
        cnt += mid-left+1 (mid+1 = size of the left half)
        Now, we will return this cnt from merge() to mergeSort().
        Inside mergeSort(), we will keep another counter variable that will store the final answer.
        With this cnt, we will add the answer returned from mergeSort() of the left half, mergeSort() of the right half, and merge().
        Finally, we will return this cnt, as our answer from mergeSort().

        Time Complexity: O(N*logN), where N = size of the given array.
        Reason: We are not changing the merge sort algorithm except by adding a variable to it. So, the time complexity is as same as the merge sort.

        Space Complexity: O(N), as in the merge sort We use a temporary array to store elements in sorted order.
     */
    /*********************************************
     * TODO: Try again after learning Recursion and Merge Sort
     *********************************************/
    public static int countInversionsOptimal(int[] arr){
        // Count the number of pairs:
        return mergeSort(arr, 0, arr.length - 1);
    }

    public static int mergeSort(int[] arr, int low, int high) {
        int cnt = 0;
        if (low >= high) return cnt;
        int mid = (low + high) / 2 ;
        cnt += mergeSort(arr, low, mid);  // left half
        cnt += mergeSort(arr, mid + 1, high); // right half
        cnt += merge(arr, low, mid, high);  // merging sorted halves
        return cnt;
    }

    private static int merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>(); // temporary array
        int left = low;      // starting index of left half of arr
        int right = mid + 1;   // starting index of right half of arr

        //Modification 1: cnt variable to count the pairs:
        int cnt = 0;

        //storing elements in the temporary array in a sorted manner//

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                cnt += (mid - left + 1); //Modification 2
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

        // transferring all elements from temporary to arr //
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        return cnt; // Modification 3
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 4, 1};
        int count = countInversionsBruteForce(arr);
        int count2 = countInversionsOptimal(arr);

        System.out.println("Inversions count of array using brute force approach: " + count);
        System.out.println("Inversions count of array using optimal approach: " + count2);
    }
}
