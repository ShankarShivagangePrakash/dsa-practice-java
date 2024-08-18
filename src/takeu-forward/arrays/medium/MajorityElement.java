package arrays.medium;

import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/find-the-majority-element-that-occurs-more-than-n-2-times/
    https://leetcode.com/problems/majority-element/description/
 */
public class MajorityElement {

    /*
    Approach:
        The steps are as follows:

        We will run a loop that will select the elements of the array one by one.
        Now, for each element, we will run another loop and count its occurrence in the given array.
        If any element occurs more than the floor of (N/2), we will simply return it.

        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    public static int majorityElementBruteForce(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int count = 0;
            for(int j = 0; j < arr.length; j++){
                if( arr[i] == arr[j]){
                    count++;
                }
            }
            if(count > arr.length/2)
                return arr[i];
        }
        return -1;
    }

    /*
    Approach:
        Use a better data structure to reduce the number of look-up operations and hence the time complexity.
        Moreover, we have been calculating the count of the same element again and again - so we have to reduce that also.

        Use a hashmap and store as (key, value) pairs.
        Here the key will be the element of the array and the value will be the number of times it occurs.
        Traverse the array and update the value of the key.
        Later, check if the value is greater than the floor of N/2.
        If yes, return the key
        Else iterate forward.

        Time Complexity: O(2n)
            O(n) + O(n) - one O(n) for first for loop to fill hash map, second to iterate through hashmap and get the majority element
            Note: If there, a majority element exists, then the size of hashmap will be lesser than `n` - array size. In this case second loop complexity will be lesser than O(n)
                    but we have to consider worst case, if all the elements are unique and no majority element exists.

        Space Complexity: O(n)
     */
    public static int majorityElementBetterApproach(int[] arr){
        HashMap<Integer, Integer> elementsCount = new HashMap<>();

        for(int i = 0; i < arr.length; i++){
            int freq = elementsCount.getOrDefault(arr[i], 0);
            elementsCount.put(arr[i], freq + 1);
        }

        for(HashMap.Entry<Integer, Integer> entry: elementsCount.entrySet()){
            if(entry.getValue() > arr.length/2)
                return entry.getKey();
        }
        return  -1;
    }

    /*
    Approach:
        If the array contains a majority element, its occurrence must be greater than the floor(N/2).
        Now, we can say that the count of minority elements and majority elements is equal up to a certain point in the array.
        With this background let's jump to algorithm.

        Initialize 2 variables:

        Count –  for tracking the count of majorityElement
        majorityElement – for which element we are counting
        Traverse through the given array.
        If Count is 0 then store the current element of the array as Element.
        If the current element and Element are the same increase the Count by 1.
        If they are different decrease the Count by 1.
        The integer present in Element should be the result we are expecting
        but verify why checking frequency of that element by running one more separate loop.

        Time Complexity: O(2n)
            = O(n) + O(n)

        Space Complexity: O(1)
     */
    public static int majorityElementOptimal(int[] arr){
        int majorityElement = -1;
        int count = 0;

        for(int i = 0; i < arr.length; i++){
            // If count becomes, then majorityElement we choose has to change,
            // we have to understand that in the previous iteration, count was set to 0, then current element in this iteration has to be set as `majorityElement`
            // reset count to 1.
            if(count == 0){
                majorityElement = arr[i];
                count = 1;
            }
            // If you are encountering the same element as the `majorityElement` chosen, increment count.
            else if(majorityElement == arr[i]){
                count ++;
            }
            // else means, the element you are encountering is not same element as the `majorityElement` chosen, decrement count.
            else{
                count --;
            }
        }

        // verifying whether the majorityElement chosen by algorithm is actually majority or not?
        int verifyCont = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == majorityElement)
                verifyCont++;
        }
        if(verifyCont > arr.length/2)
            return majorityElement;

        return -1;
    }

    public static void main(String args[]) {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        int ans = majorityElementBruteForce(arr);
        int ans2 = majorityElementBetterApproach(arr);
        int ans3 = majorityElementOptimal(arr);
        System.out.println("The majority element using brute force approach: " + ans);
        System.out.println("The majority element using better approach(Hashing): " + ans2);
        System.out.println("The majority element using optimal approach(Moore's Voting Algorithm): " + ans3);

    }
}
