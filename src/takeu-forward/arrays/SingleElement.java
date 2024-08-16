package arrays;

import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/arrays/find-the-number-that-appears-once-and-the-other-numbers-twice/
    https://leetcode.com/problems/single-number/description/
 */
public class SingleElement {

    /*
    Approach:
        The intuition will be the same as the array hashing.
        The only difference here is we will use the map data structure for hashing instead of using another array for hashing.
        Because the value of inputs can vary from -infinity to +infinity

        First, we will declare a map.
        Now, using a loop we will store the elements of the array along with their frequency in the map data structure.
        Using another loop we will iterate over the map, and try to find the element for which the frequency is 1, and finally, we will return that particular element.

        Time complexity: O(n) + O(m)
            where n is the number of elements in the array, first loop
            m is the number of elements in the hash map, loop which finds element with frequency 1.

            But actually,
                O(N*logM) + O(M), where M = size of the map
                We are inserting N elements in the map data structure and insertion takes logM time(where M = size of the map). So this results in the first term O(N*logM).
                The second term is to iterate the map and search the single element.
                In Java, HashMap generally takes O(1) time complexity for insertion and search.
                so that's why it became O(n)

        Space complexity:
             O(M) as we are using a map data structure. Here M = size of the map i.e. M = (N/2)+1.
             m = n/2 + 1, because every element is repeated twice in array so those elements will be stored only once, so n/2
             only one element is not repeated for that +1.
     */
    public static int getSingleElementBruteForce(int[] arr){

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < arr.length; i++){
            int value = map.getOrDefault(arr[i], 0);
            map.put(arr[i], value + 1);
        }

        for( HashMap.Entry<Integer, Integer> i : map.entrySet()){
            if(i.getValue() == 1)
                return i.getKey();
        }
        return  -1;
    }

    /*
    Approach:
        since every element (except one) will be repeated exactly twice, xor of such same numbers will be zero.
        One number will occur only once, so xor of that number and zero will be that number itself.

        Example:
            Assume the given array is: [4,1,2,1,2]
            XOR of all elements = 4^1^2^1^2
                  = 4 ^ (1^1) ^ (2^2)
                  = 4 ^ 0 ^ 0 = 4^0 = 4
            Hence, 4 is the single element in the array.

        Time complexity: O(n)
        Space complexity: O(1)

     */
    public static int getSingleElementOptimal(int[] arr){
        int xor = 0;
        for(int i = 0; i < arr.length; i++){
            xor = xor ^ arr[i];
        }
        return xor;
    }

    public static void main(String args[]) {
        int[] arr = {4, 1, 2, 1, 2};
        int ans = getSingleElementBruteForce(arr);
        int ans2 = getSingleElementOptimal(arr);
        System.out.println("The single element using brute force approach: " + ans);
        System.out.println("The single element using optimal approach: " + ans2);

    }
}
