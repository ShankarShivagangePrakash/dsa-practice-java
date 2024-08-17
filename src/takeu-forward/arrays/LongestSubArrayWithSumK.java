package arrays;

import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/
    https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=longest-sub-array-with-sum-k
 */
public class LongestSubArrayWithSumK {

    /*
    Approach:
        The steps are as follows:

        First, we will run a loop(say i) that will select every possible starting index of the subarray.
        The possible starting indices can vary from index 0 to index n-1(n = array size).

        Inside the loop, we will run another loop(say j) that will signify the ending index as well as the current element of the subarray.
        For every subarray starting from the index i, the possible ending index can vary from index i to n-1(n = size of the array).

        Inside loop j, we will add the current element to the sum of the previous subarray i.e. sum = sum + arr[j].
        If the sum is equal to k, we will consider its length i.e. (j-i+1).

        Among all such sub-arrays with sum k, we will consider the one with the maximum length by comparing all the lengths.

        Intuition:
        If we carefully observe, we can notice that to get the sum of the current subarray
        we just need to add the current element(i.e. arr[j]) to the sum of the previous subarray i.e. arr[i….j-1].

        Assume previous subarray = arr[i……j-1]
        current subarray = arr[i…..j]
        Sum of arr[i….j] = (sum of arr[i….j-1]) + arr[j]

        My explanation:
            we start with index i = 0, then we try to form every sub array to it by adding elements right to it, means element at index 1,2 upto n -1
            at any point if the summation of that sub array (in this iteration it will be from index 0 to current index) if it is equal to k
            to form this summation of sub array, when we traverse that element to right, we add that to summation, that's how we don't need additional for loop to calculate summation of sub-array.
            then we compare maxLen with the sub array length, if it is greater than maxLen we update it.

            same for other index, now index is at 1.
            now sub arrays for it will be the index 1 itself, and all element to its right. index 2, 3 and so on.

           So that's how, we keep forming all possible sub arrays from every index, and we compare with the given sum k
           if length of sub array is greater than maxLen we update it.

       Time complexity: O(n^2)
       Space complexity: O(1)

     */
    public static int longestSubArrayWithSumKBruteForce(int[] arr, long k){
        int maxLen = 0;
        for(int i = 0; i < arr.length; i++){
            long sum = 0;
            for(int j = i; j < arr.length; j++){
                sum += arr[j];

                if(sum == k){
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    /*
    Approach:
        We try to solve this problem using prefix sum method.

        Note: This is a better solution if array input has only positive elements
              But if array input has negatives or zeroes, this will be the optimal solution.

        The steps are as follows:

        First, we will declare a map to store the prefix sums and the indices.
        Then we will run a loop(say i) from index 0 to n-1(n = size of the array).

        For each index i, we will do the following:
        We will add the current element i.e. a[i] to the prefix sum.
        If the sum is equal to k, we should consider the length of the current subarray i.e. i+1. We will compare this length with the existing length and consider the maximum one.

        If that sum of the remaining part i.e. x-k exists in the map, we will calculate the length i.e. i-preSumMap[x-k],
        and consider the maximum one comparing it with the existing length we have achieved until now.

        We are checking the map before insertion because we want the index to be as minimum as possible, and so we will consider the earliest index where the sum x-k has occurred.

        Time complexity: O(n)
            insertion and retrieval time complexity of hash map is O(1) and we iterate array only once, so it is O(n)

        Space complexity: O(n) - hash map.

     */
    public static int longestSubArrayWithSumKBetterApproachUsingPrefixSum(int[] arr, long k){
        long sum = 0;
        int maxLen = 0;
        // This hash map stores the prefix sum of sub array.
        // Key first parameter will be sum and the value (second parameter) will be position at which this sum occured.
        HashMap<Long, Integer> prefixSum = new HashMap<>();

        for(int i = 0; i < arr.length; i++){
            sum += arr[i];

            // while traversing itself, if the whole sub array starting from index 0 to position `i` is equal to desired sum `k`
            // then update maxLen.
            // since index starts from 0, second parameter is i + 1
            if(sum == k)
                maxLen = Math.max(maxLen, i + 1);

            long rem = sum - k;

            // Now check for sub array with sum ( x - k), if exists then go inside if
            if(prefixSum.containsKey(rem)){
                // if exists the length of the sub array with desired sum `k` will be
                // i ( end of the current sub array) -  prefixSum.get(rem) ( last index of (x - k)).
                int len = i - prefixSum.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            // If there doesn't exist a sum in hash map with value `x` then only add value to hash map
            // else it won't work for edge cases, such as zeroes and negative inputs.
            if(!prefixSum.containsKey(sum)){
                prefixSum.put(sum, i);
            }
        }
        return maxLen;
    }

    /*
    Approach:
        Two pointer approach.

        Note: This is the optimal approach if array contains only positives.

        We are using two pointers i.e. left and right.
        The left pointer denotes the starting index of the subarray and the right pointer denotes the ending index.
        Now as we want the longest subarray, we will move the right pointer in a forward direction every time adding the element i.e. a[right] to the sum.
        But when the sum of the subarray crosses k, we will move the left pointer in the forward direction as well to shrink the size of the subarray as well as to decrease the sum.
        Thus, we will consider the length of the subarray whenever the sum becomes equal to k.

        The steps are as follows:

        First, we will take two pointers: left and right, initially pointing to the index 0.
        The sum is set to a[0] i.e. the first element initially.
        Now we will run awhile loop until the right pointer crosses the last index i.e. n-1.

        Inside the loop, we will do the following:

        We will use another while loop, and it will run until the sum is lesser or equal to k.
        Inside this second loop, from the sum, we will subtract the element that is pointed by the left pointer and increase the left pointer by 1.

        After this loop gets completed, we will check if the sum is equal to k.
        If it is, we will compare the length of the current subarray i.e. (right-left+1) with the existing one and consider the maximum one.

        Then we will move forward the right pointer by 1. If the right pointer is pointing to a valid index(< n) after the increment, we will add the element i.e. a[right] to the sum.
        Finally, we will return the maximum length.

        Time complexity: O(2n)
            how 2n, main while loop will execute from 0 to n-1 so O(n) for this
            but there is additional inner while loop, but it won't execute every time, it executes only when sum crosses k, and it can move upto right pointer.

            The outer while loop i.e. the right pointer can move up to index n-1(the last index). Now, the inner while loop i.e. the left pointer can move up to the right pointer at most.
            So, every time the inner loop does not run for n times rather it can run for n times in total. So, the time complexity will be O(2*N)

        Space Complexity: O(1) as we are not using any extra space.
     */
    public static int longestSubArrayWithSumKOptimalApproach(int[] arr, long k){
            int left = 0, right = 0;
            long sum = arr[0];
            int maxLen = 0;

            // Note: right and left are just pointers both will start from index 0
            // right pointer is used to move forward to reach sum.
            // left pointer is used to trim sum when it exceeds beyond k.
            // below while condition indicates, we run till the end of the array.
            while(right < arr.length){

                // if sum > k, reduce the subarray from left
                // until sum becomes less or equal to k:
                // if left index crosses right index, then it will not be sub array. Do not allow it.
                while( sum > k && left <= right){
                    sum -= arr[left];
                    left++;
                }

                // if sum = k, update the maxLen i.e. answer:
                if(sum == k){
                    maxLen = Math.max(maxLen, right - left + 1);
                }

                // Move forward thw right pointer and add current element value of the array to sum.
                right++;
                if(right < arr.length){
                    sum += arr[right];
                }
            }

            return maxLen;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        int len = longestSubArrayWithSumKBruteForce(a, k);
        int len2 = longestSubArrayWithSumKBetterApproachUsingPrefixSum(a, k);
        int len3 = longestSubArrayWithSumKOptimalApproach(a, k);
        System.out.println("The length of the longest subarray using brute force approach: " + len);
        System.out.println("The length of the longest subarray using prefix sum (Better Approach): " + len2);
        System.out.println("The length of the longest subarray using two pointer (Optimal Approach): " + len3);
    }
}
