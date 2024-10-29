package sliding_window_two_pointer;

import java.util.HashSet;
import java.util.HashMap;

/*
Problem:
    https://www.geeksforgeeks.org/problems/fruit-into-baskets-1663137462/1
 */
public class FruitIntoBasket {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    public static int fruitIntoBasketBruteforce(int[] arr){
        int maxLen = 0, n = arr.length;

        for(int i = 0; i < n; i++){
            HashSet<Integer> set = new HashSet<>();
            for(int j  = i; j < n; j++){
                set.add(arr[j]);

                if(set.size() <= 2)
                    maxLen = Math.max(maxLen, j-i+1);
                else
                    break;
            }
        }
        return maxLen;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2n)
            O(n) for outer while loop
            O(n) for inner while loop at worst case

        Space Complexity: O(3)
            hash map can have at most 3 elements so O(3) which is same as O(1)
     */
    public static int fruitIntoBasketBetterApproach(int[] arr){
        int left = 0, right = 0, maxLen = 0;
        // key value pair will be number and its frequency
        HashMap<Integer, Integer> map = new HashMap<>();

        while(right < arr.length){

            /* increment the frequency of the number in the hashmap
             if the number is not present in the hash map getOrDefault will take care of it*/
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            /* if the hash map size is more than 2, then we have to truncate sub array
             we move left pointer while moving we decrement frequency of number pointed by left pointer
             if the frequency has reduced to 0, we remove that entry from map*/
            while(map.size() > 2){
                map.put(arr[left], map.getOrDefault(arr[left], 0) -1);

                if(map.get(arr[left]) == 0)
                    map.remove(arr[left]);

                left++;
            }

            if(map.size() <= 2)
                maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }
        return maxLen;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            O(n) for outer while loop

        Space Complexity: O(3)
            hash map can have at most 3 elements so O(3) which is same as O(1)
     */
    public static int fruitIntoBasketOptimal(int[] arr){
        int left = 0, right = 0, maxLen = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        while(right < arr.length){
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            // same as better approach, instead of using while loop
            // we use if loop and we don't update `maxLen` till map size is <= 2
            if(map.size() > 2){
                map.put(arr[left], map.getOrDefault(arr[left], 0) -1);

                if(map.get(arr[left]) == 0)
                    map.remove(arr[left]);

                left++;
            }

            if(map.size() <= 2)
                maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }
        return maxLen;
    }
}
