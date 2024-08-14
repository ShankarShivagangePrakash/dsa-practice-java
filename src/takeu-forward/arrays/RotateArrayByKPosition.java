package arrays;

/*
Problem:
    https://takeuforward.org/data-structure/rotate-array-by-k-elements/
    https://leetcode.com/problems/rotate-array/
    https://www.geeksforgeeks.org/print-array-after-it-is-right-rotated-k-times/
 */
public class RotateArrayByKPosition {

    /*
    Approach:
        For Rotating the Elements to right

        Using a temp array
        Step 1: Copy the last k elements into the temp array.
        Step 2: Shift n-k elements from the beginning by k position to the right
        Step 3: Copy the elements into the main array from the temp array.

        Time complexity: O(n)
        Space complexity: O(n)
     */
    public static void rightRotateByKPositionBruteForce(int[] arr, int n, int k){
        if(n == 0)
            return;

        // after n rotations, rotated array will be greater than original array,
        // so it is enough to rotate only k % n times.
        k = k % n;
        // If K is greater than n means, it is greater than the size of the array, we cannot rotate.
        if(k > n)
            return;

        // Have a temp array, which will store last k elements in this temp array.
        int temp[] = new int[k];

        // storing last k elements in temp array.
        for(int i = n - k; i < n; i++){
            temp[i - n + k] = arr[i];
        }

        // shift remaining elements of the array by k positions to the right
        // means elements before kth positions are shifted by K places
        for(int i = n-k-1;  i >=0; i--){
            arr[i+k] = arr[i];
        }

        // copy elements stored in temp array to main array.
        for(int i = 0; i < k; i++){
            arr[i] = temp[i];
        }

        // iterate and print.
        for(int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }
    }

    /*
    Approach:
        For Rotating the Elements to left

        Step 1: Copy the first k elements into the temp array.
        Step 2: Shift n-k elements from last by k position to the left
        Step 3: Copy the elements into the main array from the temp array.

        Time complexity: O(n)
        Space complexity: O(n)
     */
    public static void leftRotateByKPositionBruteForce(int[] arr, int n, int k){
        if(n == 0)
            return;

        k = k % n;
        if(k > n)
            return;

        // To store first k elements of the array.
        int[] temp = new int[k];

        // store first k elements in the temp array.
        for(int i = 0; i < k; i++){
            temp[i] = arr[i];
        }

        // starting from kth position move all elements to left side by k positions
        // means i = i-k
        for(int i = k; i < n; i++){
            arr[i - k] = arr[i];
        }

        // now elements of temp array has to be added at the end of the array
        // starting position has to be n - k
        for(int i = n-k; i < n; i++){
            /*
            Interesting fact:

            here i value is greater than zero = n-k
            but temp array we have to iterate from 0
            in that case, can we do it without the use of any other iterator variable like `j`
            answer is yes, we can
            now say i = n - k;
            to get temp[0] you have to do i-(n-k)
            which is i-n+k
            replace n-k for i in the above equation
            then it will be n-k -n+k
            all cancels out then it will be zero
            in next iteration i will increase by one, so after calculation it will be 1
            in that way we can iterate temp array from 0th index.
            other array can start with any other desired position.
            */
            arr[i] = temp[i-n+k];
        }

        // finally iterate all the elements of the array and print.
        for(int i = 0; i < n; i++){
            System.out.printf(arr[i] + " ");
        }
    }

    public static void reverse(int[] arr, int start, int end){
        while(start <= end){
            int temp = arr[start];

            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /*
    Approach:
        For Rotating Elements to right

        Step 1: Reverse the last k elements of the array
        Step 2: Reverse the first n-k elements of the array.
        Step 3: Reverse the whole array.

        implementation explanation:
            when we reverse those small chunks of array
                those sub arrays gets reversed, in that small sub array
                    - last will be moved to first location, first element to last location
                    - second element to last but one location, last but one element to second location

            when we do this reversal on arrays
                - elements before k
                - elements after k (including kth position)
                - entire array

            array will be rotated by k positions.

        Time complexity: O(n)
        Space complexity: O(1)
     */
    // IMPORTANT: Debug this method to understand properly.
    public static void rightRotateByKPositionsOptimal(int[] arr, int n, int k){

        // reverse last `k` elements of the array.
        reverse(arr, n-k, n-1);

        // reverse first k elements of the array.
        // since we are rotating right first k elements means entire sub array
        // starting from 0th index to last k elements.
        reverse(arr, 0, n-k-1);

        // reverse the whole array
        reverse(arr, 0, n-1);

        for(int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }
    }

    /*
    Approach:
        For Rotating Elements to left

        Step 1: Reverse the first k elements of the array
        Step 2: Reverse the last n-k elements of the array.
        Step 3: Reverse the whole array.

        implementation explanation:
            when we reverse those small chunks of array
                those sub arrays gets reversed, in that small sub array
                    - last will be moved to first location, first element to last location
                    - second element to last but one location, last but one element to second location

            when we do this reversal on arrays
                - elements before k
                - elements after k (including kth position)
                - entire array

            array will be rotated by k positions.

        Time complexity: O(n)
        Space complexity: O(1)
     */
    // IMPORTANT: Debug this method to understand properly.
    public static void leftRotateByKPositionsOptimal(int[] arr, int n, int k){


        // reverse first k elements of the array.
        reverse(arr, 0, k - 1);

        // reverse last `k` elements of the array.
        // since we are rotating left,
        // we have to consider k as the starting position and till last index of the array
        // because this is the remaining sub array.
        reverse(arr, k, n-1);

        // reverse the whole array
        reverse(arr, 0, n-1);

        for(int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }
    }


    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7};
        int arr2[] = {1,2,3,4,5,6,7};
        int arr3[] = {1,2,3,4,5,6,7};
        int arr4[] = {1,2,3,4,5,6,7};

        int k = 2;
        System.out.printf("Rotate Right array by %d using brute force approach\n", k);
        rightRotateByKPositionBruteForce(arr, arr.length, k);

        System.out.printf("\nRotate Left array by %d using brute force approach\n", k);
        leftRotateByKPositionBruteForce(arr2, arr2.length, k);

        System.out.printf("\nRotate Right array by %d using Optimal approach\n", k);
        rightRotateByKPositionsOptimal(arr3, arr3.length, k);

        System.out.printf("\nRotate Left array by %d using Optimal approach\n", k);
        leftRotateByKPositionsOptimal(arr4, arr4.length, k);

        // TODO: When you learn recursion, try solving this problem using a recursive approach.
    }

}
