package arrays.binarysearch.onanswers;

/*
Problem:
    https://takeuforward.org/arrays/kth-missing-positive-number/
    https://leetcode.com/problems/kth-missing-positive-number/description/
 */
public class KthMissingPositiveNumber {

    /*
    Approach:
        Brute force method:
            In case of brute force way, we will create an array starting from 1 to max element in the array
            keep a counter missingCount = 0;
            we iterate through every element in the array, then for every element we check is it present in the original array
                if it is present, don't do anything
                else, increment missingCount and check is `missingCount` == k
                        if so, then that number is the kth missing number, return it

           Time Complexity: O(n^2)
           Space Complexity: O(1)

        Optimal Approach:
            instead of finding all missing numbers from the beginning and counting the kth missing number
            if we can find the position/range of where the kth missing number will be available
            then our job will become easier
            eg: consider the array [3, 4, 6, 9]
                at array[0] - we have 3, but there actually it should contain 1 - so we can say 2 numbers are missing till this position
                at array[0] - we have 4, but there actually it should contain 2 - so we can say 2 numbers are missing till this position
                at array[2] - we have 6, but there actually it should contain 3 - so we can say 3 numbers are missing till this position

                we told this by observation, but how to say programmatically
                    we can say number of missing integers till the position i can be = arr[i] - i - 1
                    let's do it for every element
                    noOfMissingElement = arr[i] - i - 1
                          for array[0] = 3 - 0 - 1 = 2 ----> 2 missing elements till array[0]
                          for array[1] = 4 - 1 - 1 = 2 ----> 2 missing elements till array[1]
                          for array[2] = 6 - 2 - 1 = 3 ----> 3 missing elements till array[2]

           so, if we are asked to find kth missing element - say we need to find 5th missing number

           we will do something different here. We will try to find the closest neighbors (i.e. Present in the array)
           for the kth missing number by counting the number of missing numbers for each element in the given array.
                we do a binary search from array[0] to array[n-1]
                using low and high pointers commonly how we do in other binary search problem
                    we calculate mid,
                        if noOfMissingElements for `mid` < k
                            we make low = mid + 1; because we move to index which is nearest neighbour to kth missing element
                        else
                            kth missing element will be to the left of the current index
                            move left
                            high = mid - 1

               when we complete this while loop
               high will be lower than low
               so high will point to array[2] and low will be pointing to array[3]
               our kth missing element, i.e. 5th missing element will occur after 6
               till array[2] = 6, we know that 3 elements are missing, so if we add 2 more to it.
               we will get 5th missing element,
                so it will be arr[high] + more needed
                    = 6 + 2 = 8

              Now, how to calculate more needed
                In order to determine the number of additional missing values required to reach the kth position, we can calculate this as
                more_missing_numbers = arr[high] - no of missing elements till the position high
                more_missing_numbers = k - (arr[high] - (high+1)).
                Now, we will simply add more_missing_numbers to the preceding neighbor i.e. vec[high] to get the kth missing number.
                kth missing number = arr[high] + k - (arr[high] - (high+1))
                        =  arr[high] + k - arr[high] + high + 1
                        = k + high + 1.

              so, we will return `k + high + 1` as result.

            Time Complexity: O(log n)
            Space Complexity: O(1)
     */
    public static int kthMissingElement(int[] arr, int k){
        int low = 0, high = arr.length-1;

        while(low <= high){
            int mid = (low + high)/2;

            if(numberOfMissingElementsTillCurrentIndex(arr,mid) < k)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return k + high + 1;
    }

    public static int numberOfMissingElementsTillCurrentIndex(int[] arr, int index){
        return arr[index] - (index + 1);
    }

    public static void main(String[] args){
        int[] arr = {3, 4, 5, 6};
        int k = 2;
        System.out.printf("%dnd missing element in the array is: %d\n", k, kthMissingElement(arr, k));
    }
}
