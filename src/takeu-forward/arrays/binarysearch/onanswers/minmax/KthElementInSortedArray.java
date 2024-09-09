package arrays.binarysearch.onanswers.minmax;

/*
Problem:
    https://takeuforward.org/data-structure/k-th-element-of-two-sorted-arrays/
    https://www.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=k-th-element-of-two-sorted-array
 */
public class KthElementInSortedArray {

    /*
    Approach:
        The extremely naive approach is to merge the two sorted arrays and then find the K-th element in that merged array.

        How to merge two sorted arrays:

        The word “merge” suggests applying the merge step of the merge sort algorithm .
        In that step, we essentially perform the same actions as required by this solution. By using two pointers on two given arrays,
        we fill up the elements into a third array.

        Approach:
        We will use a third array i.e. arr3[] of size (m+n) to store the elements of the two sorted arrays.

        Now, we will take two pointers i and j, where i points to the first element of arr1[] and j points to the first element of arr2[].

        Next, using a while loop( while(i < m && j < n)), we will select two elements i.e. arr1[i] and arr2[j],
        and consider the smallest one among the two. Then, we will insert the smallest element in the third array and increase that specific pointer by 1.

            If arr1[i] < arr2[j] : Insert arr1[i] into the third array and increase i by 1.
            Otherwise : Insert arr2[j] into the third array and increase j by 1.

        After that, the left-out elements from both arrays will be copied as it is into the third array.
        Now, the third array i.e. arr3[] will be the sorted merged array. Now the k-th will be the arr3[k-1].

        Finally, we will return the value of arr3[k-1].

        Time Complexity: O(m+n)
            where m and n are the sizes of the given arrays.
            Reason: We traverse through both arrays linearly.

        Space Complexity: O(m+n)
            where m and n are the sizes of the given arrays.
            Reason: We are using an extra array of size (m+n) to solve this problem.
     */
    public static int kthElementInMergedSortedArrayBruteForce(int k, int[] arr1, int[] arr2){
        int n1 = arr1.length, n2 = arr2.length;
        int n = n1 + n2;
        int arr3[] = new int[n];

        int i = 0, j = 0, l = 0;
        while(i < n1 && j < n2){
            if(arr1[i] <= arr2[j]){
                arr3[l++] = arr1[i++];
            }
            else{
                arr3[l++] = arr2[j++];
            }
        }

        while(i < n1){
            arr3[l++] = arr1[i++];
        }
        while(j < n2){
            arr3[l++] = arr2[j++];
        }

        return arr3[k-1];
    }

    /*
    Approach:
        To optimize the space used in the previous approach, we can eliminate the third array used to store the final merged result.
        After closer examination, we realize that we only need the k-th element at index (k-1), rather than the entire merged array, to solve the problem effectively.

        We will stick to the same basic approach, but instead of storing elements in a separate array,
        we will use a counter called 'cnt' to represent the imaginary third array's index. As we traverse through the arrays, when 'cnt' reaches the index (k-1), we will store that particular element. This way, we can achieve the same goal without using any extra space.

        Approach:
        We will declare the counter called ‘cnt’ and initialize it with 0.

        Now, as usual, we will take two pointers i and j, where i points to the first element of arr1[] and j points to the first element of arr2[].

        Next, using a while loop( while(i < m && j < n)), we will select two elements i.e. arr1[i] and arr2[j],
        and consider the smallest one among the two. Then, we will increase that specific pointer by 1.
        In addition to that, in each iteration, we will check if the counter ‘cnt’ hits the indices (k-1). when 'cnt' reaches that index,
        we will store that particular element. We will also increase the ‘cnt’ by 1 every time regardless of matching the conditions.

            If arr1[i] < arr2[j] : Check ‘cnt’ to perform necessary operations and increase i and ‘cnt’ by 1
            Otherwise : Check ‘cnt’ to perform necessary operations and increase j and ‘cnt’ by 1.

        After that, the left-out elements from both arrays will be copied as it is into the third array. While copying,
        we will again check the above-said conditions for the counter, ‘cnt’ and increase it by 1.

        Finally, we will return the value of the k-th element stored in the variable.

        Time Complexity: O(m+n)
            where m and n are the sizes of the given arrays.
            Reason: We traverse through both arrays linearly.

        Space Complexity: O(1)
     */
    public static int kthElementInMergedSortedArrayBetterApproach(int k, int[] arr1, int[] arr2){
        int n1 = arr1.length, n2 = arr2.length;
        int n = n1 + n2;

        int i = 0, j = 0, cnt = 0;
        while(i < n1 && j < n2){

            if(arr1[i] <= arr2[j]){
                if(k-1 == cnt)
                    return arr1[i];
                i++;
            }
            else{
                if(k-1 == cnt)
                    return arr2[j];
                j++;
            }
            cnt++;
        }

        while(i < n1){
            if(k-1 == cnt)
                return arr1[i];
            i++;
            cnt++;
        }
        while(j < n2){
            if(k-1 == cnt)
                return arr2[j];
            j++;
            cnt++;
        }

        // dummy return statement
        return -1;
    }

    /*
    Approach:
        almost same as Median of 2 sorted arrays
        here, we form left half of k elements
        refer to notes,

        Time complexity: O(log (min(n1, n2))
            because we are doing binary search on the smaller array.
        Space Complexity: O(1)
     */
    public static int kthElementInMergedSortedArrayOptimalApproach(int k, int[] arr1, int[] arr2){
        int n1 = arr1.length, n2 = arr2.length;
        int n = n1 + n2;

        if(n1 > n2)
            return kthElementInMergedSortedArrayOptimalApproach(k, arr2, arr1);

        int low = Math.max(0, k-n2), high = Math.min(k, n1);

        while(low <= high){
            int mid1 = (low + high)/2;
            int mid2 = k - mid1;

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if(mid1 < n1) r1 = arr1[mid1];
            if(mid2 < n2) r2 = arr2[mid2];
            if(mid1-1 >= 0) l1 = arr1[mid1-1];
            if(mid1-1 >= 0) l2 = arr2[mid2-1];

            if(l1 <= r2 && l2 <= r1)
                return Math.max(l1, l2);
            else if(l1 > r2)
                high = mid1 - 1;
            else
                low = mid1 + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int k = 5;
        int[] arr1 = {2, 3, 6, 7, 9}, arr2 = {1, 4, 8, 10};

        System.out.printf("%dth element in final merged sorted array using brute force approach is %d\n", k, kthElementInMergedSortedArrayBruteForce(k, arr1, arr2));
        System.out.printf("%dth element in final merged sorted array using better approach is %d\n", k, kthElementInMergedSortedArrayBetterApproach(k, arr1, arr2));
        System.out.printf("%dth element in final merged sorted array using optimal approach is %d\n", k, kthElementInMergedSortedArrayOptimalApproach(k, arr1, arr2));
    }
}
