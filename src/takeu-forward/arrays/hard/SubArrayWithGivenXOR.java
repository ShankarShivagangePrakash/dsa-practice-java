package arrays.hard;

import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/count-the-number-of-subarrays-with-given-xor-k/
    https://www.interviewbit.com/problems/subarray-with-given-xor/
 */
public class SubArrayWithGivenXOR {

    /*
    Approach:
        Generate every possible sub arrays using two loops
        If the XOR of the sub array is `target`, Increment count
        return count;

        The steps are as follows:

        Generate Sub arrays:
        First, we will run a loop(say i) that will select every possible starting index of the subarray.

        The possible starting indices can vary from index 0 to index n-1(n = array size).
        Inside the loop, we will run another loop(say j) that will signify the ending index as well as the current element of the subarray.
        For every subarray starting from the index i, the possible ending index can vary from index i to n-1(n = size of the array).
        Calculate XOR of the subarray: Inside loop j, we will XOR the current element to the XOR of the previous subarray i.e. xorr = XOR(a[i….j-1]) ^ arr[j].
        Check the XOR and modify the count: After calculating the XOR, we will check if the sum is equal to the given k.
        If it is, we will increase the value of the count.

        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    public static int subArrayWithGivenXORkBruteForce(int[] arr, int target){
        int count = 0;

        for(int i  = 0; i < arr.length; i++){
            int xor = 0;
            for(int j = i; j < arr.length; j++){
                xor = xor ^ arr[j];

                if(xor == target){
                    count++;
                }
            }
        }
        return count;
    }

    /*
    Approach:

        The steps are as follows:

        First, we will declare a map to store the prefix XORs and their counts.
        initialize xor = 0;
        Then we will run a loop(say i) from index 0 to n-1(n = size of the array).
        For each index i, we will do the following:
        We will XOR the current element i.e. arr[i] to the existing prefix XOR i.e. `xr`.
        Then we will calculate the prefix XOR i.e. xr^k, for which we need the occurrence.
        We will add the occurrence of the prefix XOR xr^k i.e. prefixXORMap[xr^k] to our answer.
        Then we will store the current prefix XOR, xr in the map increasing its occurrence by 1.

        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    public static int subArrayWithXORkOptimal(int[] arr, int k){
        int count = 0;
        int xr = 0;

        // map which stores xor value at index `i` as the key and no of sub arrays having that xor value(count)
        HashMap<Integer, Integer> prefixXORMap = new HashMap<>();

        for(int i = 0; i < arr.length; i++){
            xr = xr ^ arr[i];

            if(xr == k)
                count++;

            /*  we are trying to find the xor of the remaining part of the sub array. If that exists then there is a sub array with xor target
             how many such sub arrays are possible means, count of map data structure for xr ^ k
             why this count is required, let's consider array: [4, 2, 2, 6, 4] and current index is 6
             map will look like below
             xor ---- count
             4  ---- 2 ( [4] [4, 2, 2] )
             6  ---- 1 ( [4, 2])

             now xor at when i is at 6 is xr is 2
             x = xr ^ k
             x= 2 ^ 6 which will be 4 ( do xor for binary representation of this number to calculate manualy)
             now there are two sub arrays with xor as 4 [4] and [4, 2, 2]
             the current element can be combined with both of the above two sub arrays and the xor will be still 6.
             So you have to maintain count in the map and add that count.*/
            int x = xr ^ k;
            if (prefixXORMap.containsKey(x)) {
               count += prefixXORMap.get(x);
            }

            if(prefixXORMap.containsKey(xr)){
                prefixXORMap.put(xr, prefixXORMap.get(xr) + 1);
            }
            else{
                prefixXORMap.put(xr, 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 6, 4};
        System.out.printf("Number of sub arrays with XOR as %d using brute force approach: %d\n", 6, subArrayWithGivenXORkBruteForce(arr, 6));
        System.out.printf("Number of sub arrays with XOR as %d using optimal approach(Prefix sum or hashing) %d\n", 6, subArrayWithXORkOptimal(arr, 6));
    }
}
