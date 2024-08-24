package arrays.hard;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/merge-two-sorted-arrays-without-extra-space/
    https://leetcode.com/problems/merge-sorted-array/description/
 */
public class MergeSortedArrays {

    /*
    Approach:
        The sizes of the given arrays are n(size of arr1[]) and m(size of arr2[]).

        The steps are as follows:

        We will declare two pointers i.e. left and right. The left pointer will point to the last index of the arr1[]
        (i.e. Basically the maximum element of the array). The right pointer will point to the first index of the arr2[]
        (i.e. Basically the minimum element of the array).
        Now, the left pointer will move toward index 0 and the right pointer will move towards the index m-1.
        While moving the two pointers we will face 2 different cases like the following:
        If arr1[left] > arr2[right]: In this case, we will swap the elements and move the pointers to the next positions.
        If arr1[left] <= arr2[right]: In this case, we will stop moving the pointers as arr1[] and arr2[] are containing correct elements.
        Thus, after step 2, arr1[] will contain all smaller elements and arr2[] will contain all bigger elements.
        Finally, we will sort the two arrays.

        Time Complexity: O(min(n, m)) + O(n * log n) + O(m * log m), where n and m are the sizes of the given arrays.
        Reason:
        O(min(n, m)) is for swapping the array elements, maximum we will swap x elements that will be minimum of m & n
        And O(n * log n) and O(m * log m) are for sorting the two arrays.

        Space Complexity: O(1) as we are not using any extra space.
     */
    public static void mergeSortedArrayOptimal1(int[] arr1, int m, int[] arr2, int n){

        int left = m - 1;
        int right = 0;

        while(left >= 0 && right < n){
            if(arr1[left] > arr2[right]){
                int temp = arr2[right];
                arr2[right] = arr1[left];
                arr1[left] = temp;
                left--;
                right++;
            }
            else{
                break;
            }
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    /*
    LeetCode problem varies slightly. There m and n are not exactly size of arrays
    m corresponds to non-zero elements in arr1
    and when there is zero elements in arr1[] that has to replaced by a number from arr2
    finally everything has to be sorted.

   Approach:
       The sizes of the given arrays are n(size of arr1[]) and m(size of arr2[]).

       everything is same as optimal1()
       but left will set as arr1.length - 1;
       if arr1[left] == 0
       then swap it with arr2[right]

       The steps are as follows:

       We will declare two pointers i.e. left and right. The left pointer will point to the last index of the arr1[]
       (i.e. Basically the maximum element of the array). The right pointer will point to the first index of the arr2[]
       (i.e. Basically the minimum element of the array).
       Now, the left pointer will move toward index 0 and the right pointer will move towards the index m-1.
       While moving the two pointers we will face 3 different cases like the following:
       If arr1[left] == 0, swap arr1[left] and arr2[right] left--, right++;
       If arr1[left] > arr2[right]: In this case, we will swap the elements and move the pointers to the next positions.
       If arr1[left] <= arr2[right]: In this case, we will stop moving the pointers as arr1[] and arr2[] are containing correct elements.
       Thus, after step 2, arr1[] will contain all smaller elements and arr2[] will contain all bigger elements.
       Finally, we will sort the two arrays.

       Time Complexity: O(n)
       Space Complexity: O(1)
    */
    public static void mergeSortedArrayLeetCodeVariationOptimal1(int[] arr1, int m, int[] arr2, int n){

        int left = m - 1;
        int right = 0;

        while(left >= 0 && right < n){
            if(arr1[left] > arr2[right]){
                int temp = arr2[right];
                arr2[right] = arr1[left];
                arr1[left] = temp;
                left--;
                right++;
            }
            else{
                break;
            }
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    /*
    Approach:
        The steps are as follows:

        First, assume the two arrays as a single array and calculate the gap value i.e. ceil((size of arr1[] + size of arr2[]) / 2).
        We will perform the following operations for each gap until the value of the gap becomes 0:
        Place two pointers in their correct position like the left pointer at index 0 and the right pointer at index (left+gap).
        Again we will run a loop until the right pointer reaches the end i.e. (n+m). Inside the loop, there will be 3 different cases:
        If the left pointer is inside arr1[] and the right pointer is in arr2[]: We will compare arr1[left] and arr2[right-n] and swap them if arr1[left] > arr2[right-n].
        If both the pointers are in arr2[]: We will compare arr1[left-n] and arr2[right-n] and swap them if arr1[left-n] > arr2[right-n].
        If both the pointers are in arr1[]: We will compare arr1[left] and arr2[right] and swap them if arr1[left] > arr2[right].
        After the right pointer reaches the end, we will decrease the value of the gap and it will become ceil(current gap / 2).
        Finally, after performing all the operations, we will get the merged sorted array.
        stopping condition is when gap value becomes 1 for the second time.

        Time Complexity: O((n+m)*log(n+m)), where n and m are the sizes of the given arrays.
        Reason: The gap is ranging from n+m to 1, initial value is ciel((n+m)/2) and every time the gap gets divided by 2.
        So, the time complexity of the outer loop will be O(log(n+m)).
        Now, for each value of the gap, the inner loop can at most run for (n+m) times (while right < len).
        So, the time complexity of the inner loop will be O(n+m).
        So, the overall time complexity will be O((n+m)*log(n+m)).

        Space Complexity: O(1)
     */
    public static void mergeSortedArrayOptimal2(int[] arr1, int m, int[] arr2, int n){

        // len of the imaginary single array:
        int len = m + n;

        /* gap = Math.ceil [ (m + n)/2 ]
         same can be rewritten as m/2 + m%2;
         because since we are doing ceil, we can add reminder to quotient that will be the gap value*/
        int gap = (len / 2) + (len % 2);

        while(gap > 0){
            int left = 0;
            int right = left + gap;

            while(right < len) {
                // element at left index is in arr1[] and element at right index is in arr[2]
                // we have to compare and swap.
                // since right is the index value starting from arr1[] we have to subtract the length of first array to pass actual arr2[] index.
                if (left < m && right >= m) {
                    swap(arr1, arr2, left, right - m);
                }
                // if left index is greater than size of arr1 length means both left and right has to be in arr2[]
                // modify index accordingly while invoking swap.
                else if (left >= m) {
                    swap(arr2, arr2, left - m, right - m);
                }
                // both left and right are in arr1.
                else {
                    swap(arr1, arr1, left, right);
                }
                left++;
                right++;
            }

            /* after right is greater than len
             we have to update gap, new gap value will be = Math.ceil [ existing gap /2]

             if gap is encountered for the second time, that is the breaking point
             so first compare for gap value 1 before updating gap, so for first time before making gap as 1
             this if executes and evaluates as false, line 145 makes gap as 1 and remaining while loop executes
             then again when control comes here, gap will be 1, and it exits while loop*/
            if(gap == 1){
                break;
            }
            gap = (gap / 2) + (gap % 2);
        }
    }

    private static void swap(int[] arr1, int[] arr2, int index1, int index2){
        if(arr1[index1] > arr2[index2]){
           int temp = arr1[index1];
           arr1[index1] = arr2[index2];
           arr2[index2] = temp;
        }
    }

    public static void main(String[] args){

        // Approach 1:
        int[] arr1 = {1, 4, 8, 10};
        int[] arr2 = {2, 3, 9};
        int m = 4, n = 3;

        System.out.println("Merged Sorted arrays result using optimal approach 1");
        mergeSortedArrayOptimal1(arr1, m, arr2, n);

        for(int i = 0; i < m; i++){
            System.out.printf(arr1[i] + " ");
        }
        System.out.println();
        for(int i = 0; i < n; i++){
            System.out.printf(arr2[i] + " ");
        }
        System.out.println();

        // Approach 2:

        int[] arr3 = {1, 4, 8, 10};
        int[] arr4 = {2, 3, 9};
        int m1 = 4, n1 = 3;

        System.out.println("Merged Sorted arrays result using optimal approach 1");
        mergeSortedArrayOptimal2(arr3, m1, arr4, n1);

        for(int i = 0; i < m1; i++){
            System.out.printf(arr3[i] + " ");
        }
        System.out.println();
        for(int i = 0; i < n1; i++){
            System.out.printf(arr4[i] + " ");
        }
        System.out.println();
    }
}
