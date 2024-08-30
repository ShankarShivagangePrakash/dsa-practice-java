package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/data-structure/search-single-element-in-a-sorted-array/
    https://leetcode.com/problems/single-element-in-a-sorted-array/description/
 */
public class SingleElementInSortedArray {

    /*
    Approach:
        Better approach is to use XOR operator
        keep multiplying each element of the array in a single loop
        res = 0
        for(int i = 0; i < n; i++){
            res = res ^ arr[i];
        }
        return res;

            since XOR of same number will be 0 and each element repeats twice, all those will become 0

            only one element will be present only once, that will leave in `res`, return it

        Time Complexity: O(n)
        Space Complexity: O(1)

    Approach:
        Optimal Approach:
        since we know that array is sorted
        If we can apply binary search and solve, we would be able to further reduce time complexity to O(log n)

        so,
           consider this sorted array
           {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6}; - next line correspondingly we will mark their index
            0  1  2  3  4  5  6  7  8  9  10

            so, you can observe one thing
            single element can occur at three positions
                - start of the array
                - end of the array
                - somewhere in the middle

            so we need to handle all this scenario's

            so we will handle edge cases first, then we dive into actual logic
                case 1: if array has only one element then that is the single element. return it.
                case 2: if first element in the array is not equal to second element in the array, then that is the single element return it.
                case 3: If last element in the array is not equal to last but one element in the array, then that is the single element, return it

                case 4: single element is somewhere in the middle, we have to search.
                        If you notice, we have already handled the cases of single element occuring in first or last position of the array,
                        so we can omit those indices

                        we initialize low = 1, high = n-2

                        we check is arr[mid] != arr[mid-1] &&
                                    arr[mid] != arr[mid+1]

                                    it means, that this is the single element

                        if not we have to either move to left or right
                        but where to move, how to identify?

                        now consider array again
                        {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6}; - next line correspondingly we will mark their index
                         0  1  2  3  4  5  6  7  8  9  10

                         from naked eye, we can say 4 is the single element.
                         since it is a sorted array, check the element to the left of 4
                         each element is appearing twice and
                         (element at even index is equal to element at odd index)
                            eg: element 1 - element 1 is present at index 0 and 1, 0 is even index and 1 is odd index
                                element 3 - element 3 is present at index 4 and 5, 4 is even index and 5 is odd index

                         so we can say, the pattern for left sub array is (even,odd)

                         similarly, let's consider elements to right of 4
                            if this sub array is sorted means, then same elements will be order (odd, even)
                            eg: consider 5, 5 is present at index 7 and 8 ( odd, even)

                         so left sub array - (even, odd)
                           right sub array - (odd, even)

                         our approach is we will check for this (odd, even) check for either left sub array or right sub array
                            for one element in that range (element at `mid` index).
                            If that rule holds good then it means every element in that range is appearing twice, we can omit that range,
                            and we move to other half

                         we keep doing this and check is mid not equal to its adjacent elements and return the single element.

           Time complexity: O(log n)
           Space Complexity: O(1)
     */
    public static int singleNonDuplicate(int[] arr){
        int n = arr.length;

        // edge case: If array has only one element
        if(n == 1)
            return arr[0];

        // Check, is first element of the array is single element
        if(arr[0] != arr[1])
            return arr[0];

        // check, is last element of the array is single element.
        if(arr[n-1] != arr[n-2])
            return arr[n-1];

        // since we have checked first for first and last elements being single elements
        // if we are reaching here, it means they are not, so we can omit that range.
        // so initialize low = 1, high = last but second index
        int low = 1, high = n-2;

        while(low <= high){
            int mid = (low + high)/2;

            //if arr[mid] is not equal to its adjacent elements on both sides, then that is the single element, return it.
            if(arr[mid] != arr[mid-1] &&
               arr[mid] != arr[mid+1]){
                return arr[mid];
            }

            // check for left sub array
            // assume you are on the left sub array, then pattern will be (even, odd)
            // if you are on even index, then next index will be odd and that index element has to same as current - first half of the condition
            // if you are on odd index, then previous index element has to be same. It means left sub array don't have single element, move to right sub array. - second half of condition.
            if((mid % 2 == 0 && arr[mid] == arr[mid+1]) || (mid % 2 != 0 && arr[mid] == arr[mid-1])){
                // left sub array don't have any repeating element, because mid is the last element in the sub array
                // if that element is following(even, odd) rule then all previous elements must be appearing twice, so move to right sub array.
                low = mid + 1;
            }
            // now you need not explicitly check for (odd, even)
            // if above is `if` condition is not holding means that left sub array is having single element,
            // so we can omit right sub array easily.
            else{
                high = mid -1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6};
        System.out.printf("Single element in sorted array is %d\n", singleNonDuplicate(arr));
    }
}
