package arrays.binarysearch.onanswers.minmax;

/*
Problem:
    https://takeuforward.org/data-structure/median-of-two-sorted-arrays-of-different-sizes/
    https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {

    /*
    Approach:
        The extremely naive approach is to merge the two sorted arrays and then find the median in that merged array.

        How to merge two sorted arrays:
        The word “merge” suggests applying the merge step of the merge sort algorithm.
        By using two pointers on two given arrays, we fill up the elements into a third array.

        How to find the median:

        If the length of the merged array (n1+n2) is even:
            The median is the average of the two middle elements. index = (n1+n2) / 2, median = (arr3[index] + arr3[index-1]) / 2.0.

        If the length of the merged array (n1+n2) is odd:
            index = (n1+n2) / 2, median = arr3[index].

        Algorithm:

        We will use a third array i.e. arr3[] of size (n1+n2) to store the elements of the two sorted arrays.

        Now, we will take two pointers i and j, where i points to the first element of arr1[] and j points to the first element of arr2[].
        Next, using a while loop( while(i < n1 && j < n2)), we will select two elements i.e. arr1[i] and arr2[j], and consider the smallest one among the two.
        Then, we will insert the smallest element in the third array and increase that specific pointer by 1.
        If arr1[i] < arr2[j]: Insert arr1[i] into the third array and increase i by 1.
        Otherwise: Insert arr2[j] into the third array and increase j by 1.

        After that, the left-out elements from both arrays will be copied as it is into the third array.

        Now, the third array i.e. arr3[] will be the sorted merged array. Now the median will be the following:
        If the length of arr3[] i.e. (n1+n2) is even: The median is the average of the two middle elements. index = (n1+n2) / 2, median = (arr3[index] + arr3[index-1]) / 2.0.
        If the length of arr3[] i.e. (n1+n2) is odd: index = (n1+n2) / 2, median = arr3[index].

        Finally, we will return the value of the median.

        Time Complexity: O(n1+n2), where  n1 and n2 are the sizes of the given arrays.
            Reason: We traverse through both arrays linearly.

        Space Complexity: O(n1+n2), where  n1 and n2 are the sizes of the given arrays.
            Reason: We are using an extra array of size (n1+n2) to solve this problem.
     */
    public static double medianOfTwoSortedArraysBruteForce(int[] arr1, int[] arr2){
        int n1 = arr1.length, n2 = arr2.length;
        int n = n1 + n2;
        int[] arr3 = new int[n];

        int i = 0, j = 0, k = 0;

        while(i < n1 && j < n2){
            if(arr1[i] <= arr2[j]){
                arr3[k++] = arr1[i++];
            } else{
                arr3[k++] = arr2[j++];
            }
        }
        while(i < n1){
            arr3[k++] = arr1[i++];
        }
        while(j < n2){
            arr3[k++] = arr2[j++];
        }

        if(n %2 == 1){
            return arr3[n/2];
        }
        else{
            int index1 = n/2;
            return (double)(arr3[index1] + arr3[index1-1])/2.0;
        }
    }

    /*
    Approach:
        To optimize the space used in the previous approach, we can eliminate the third array used to store the final merged result.
        After closer examination, we realize that we only need the two middle elements at indices (n1+n2)/2 and ((n1+n2)/2)-1,
        rather than the entire merged array, to solve the problem effectively.

        We will stick to the same basic approach, but instead of storing elements in a separate array,
        we will use a counter called 'cnt' to represent the imaginary third array's index.
        As we traverse through the arrays, when 'cnt' reaches either index (n1+n2)/2 or ((n1+n2)/2)-1,
        we will store that particular element. This way, we can achieve the same goal without using any extra space.

        Algorithm:

        We will call the required indices as ind2 = (n1+n2)/2 and ind1 = ((n1+n2)/2)-1.
        Now we will declare the counter called ‘cnt’ and initialize it with 0.

        Now, as usual, we will take two pointers i and j, where i points to the first element of arr1[] and j points to the first element of arr2[].

        Next, using a while loop( while(i < n1 && j < n2)), we will select two elements i.e. arr1[i] and arr2[j], and consider the smallest one among the two.
        Then, we will increase that specific pointer by 1.

        In addition to that, in each iteration, we will check if the counter ‘cnt’ hits the indices ind1 or ind2.
        when 'cnt' reaches either index ind1 or ind2, we will store that particular element.
        We will also increase the ‘cnt’ by 1 every time regardless of matching the conditions.

        If arr1[i] < arr2[j]: Check ‘cnt’ to perform necessary operations and increase i and ‘cnt’ by 1.
        Otherwise: Check ‘cnt’ to perform necessary operations and increase j and ‘cnt’ by 1.

        After that, the left-out elements from both arrays will be copied as it is into the third array.
        While copying, we will again check the above-said conditions for the counter, ‘cnt’ and increase it by 1.

        Now, let’s call the elements at the required indices as index1Element(at ind1) and index2Element(at ind2):

        If the total length i.e. (n1+n2) is even: The median is the average of the two middle elements. median = (index1Element + index2Element) / 2.0.
        If the total length i.e. (n1+n2) is odd: median = index2Element.

        Finally, we will return the value of the median.

        Time Complexity: O(n1+n2), where  n1 and n2 are the sizes of the given arrays.
            Reason: We traverse through both arrays linearly.

        Space Complexity: O(1), as we are not using any extra space to solve this problem.
     */
    public static double medianOfTwoSortedArraysBetterApproach(int[] arr1, int[] arr2){
        int n1 = arr1.length, n2 = arr2.length;
        int n = n1 + n2;
        int cnt = 0;
        int index2 = n/2;
        int index1 = index2 - 1;
        int index1Element = -1, index2Element = -1;

        int i = 0, j = 0;

        while(i < n1 && j < n2){
            if(arr1[i] <= arr2[j]){
                if(cnt == index1)
                    index1Element = arr1[i];
                if(cnt == index2)
                    index2Element = arr1[i];
                i++;
            } else{
                if(cnt == index1)
                    index1Element = arr2[j];
                if(cnt == index2)
                    index2Element = arr2[j];
                j++;
            }
            cnt++;
        }
        while(i < n1){
            if(cnt == index1)
                index1Element = arr1[i];
            if(cnt == index2)
                index2Element = arr1[i];
            i++;
            cnt++;
        }
        while(j < n2){
            if(cnt == index1)
                index1Element = arr2[j];
            if(cnt == index2)
                index2Element = arr2[j];
            j++;
            cnt++;
        }

        if(n %2 == 1){
            return index2Element;
        }
        else{
            return (double)(index2Element + index1Element)/2.0;
        }
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(log (min (n1,n2) ))
            where n1 and n2 are the sizes of two given arrays.
            Reason: We are applying binary search on the range [0, min(n1, n2)].

        Space Complexity: O(1) as no extra space is used.
     */
    public static double medianOfTwoSortedArraysOptimal(int[] arr1, int[] arr2){
        int n1 = arr1.length, n2 = arr2.length;
        int n = n1 + n2;
        if(n1 > n2)
            return medianOfTwoSortedArraysOptimal(arr2, arr1);

        int low = 0, high = n1;
        int noOfElementsInLeftHalf = (n1 + n2 + 1)/2;

        while(low <= high){
            // number of elements to consider for left half from arr1
            int mid1 = (low + high)/2;
            // number of elements to consider for left half from arr2
            int mid2 = noOfElementsInLeftHalf - mid1;

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            // to avoid runtime exceptions like Index out of bounds,
            // we have to check whether the indexes generated are valid
            // mid1 and mid2 have values which represents number of elements to take from
            // arr1 and arr2 for left half
            // but array index starts from 0, so last element of the left half from arr1 and arr2 will be
            // mid1-1. & mid2-1
            // elements at index mid1 and mid2 will be the first elements for right half from arr1 and arr2.
            if(mid1 < n1)
                r1 = arr1[mid1];
            if(mid2 < n2)
                r2 = arr2[mid2];

            if(mid1 - 1 >= 0)
                l1 = arr1[mid1 - 1];
            if(mid2 - 1 >= 0)
                l2 = arr2[mid2 - 1];

            if(l1 <= r2 && l2 <= r1){
                // we have found the answer
                // if(n is odd) then median is max(l1, l2)
                if(n % 2 == 1)
                    return Math.max(l1, l2);
                else{
                    // else means merged array will have even number of elements
                    // so
                    return (double)((Math.max(l1, l2) + Math.min(r1, r2))/2.0);
                }
            }
            else if(l1 > r2){
                // we have considered more elements from arr1 for left half
                // reduce no of elements from arr1 for left half - so that merged array will be sorted
                high = mid1 - 1;
            }
            else{
                // indirectly we are checking for l2 > r1
                // we have considered less number of elements from arr1 for left half
                // consider more elements from arr1 for left half, so final merged array will be sorted.
                low = mid1 + 1;
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 7, 10, 12};
        int[] b = {2, 3, 6, 15};
        System.out.println("The median of two sorted arrays using brute force solution: " + medianOfTwoSortedArraysBruteForce(a, b));
        System.out.println("The median of two sorted arrays using better solution: " + medianOfTwoSortedArraysBetterApproach(a, b));
        System.out.println("The median of two sorted arrays using optimal approach: " + medianOfTwoSortedArraysOptimal(a, b));
    }
}
