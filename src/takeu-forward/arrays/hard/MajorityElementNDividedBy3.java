package arrays.hard;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/majority-elementsn-3-times-find-the-elements-that-appears-more-than-n-3-times-in-the-array/
    https://leetcode.com/problems/majority-element-ii/description/
 */
public class MajorityElementNDividedBy3 {

    /*
   Approach:
       The steps are as follows:

       We will run a loop that will select the elements of the array one by one.
       Now, for each element, we will run another loop and count its occurrence in the given array.
       If any element occurs more than the floor of (N/3), we will simply return it.

       Time Complexity: O(n^2)
       Space Complexity: O(1)
    */
    public static List<Integer> majorityElementBruteForce(int[] arr){
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            int count = 0;
            for(int j = 0; j < arr.length; j++){
                if( arr[i] == arr[j]){
                    count++;
                }
            }
            if(count > Math.floor(arr.length/3) && result.contains(arr[i]) == false)
                result.add(arr[i]);
        }
        return result;
    }

    /*
    Approach:
        Use a better data structure to reduce the number of look-up operations and hence the time complexity.
        Moreover, we have been calculating the count of the same element again and again - so we have to reduce that also.

        Use a hashmap and store as (key, value) pairs.
        Here the key will be the element of the array and the value will be the number of times it occurs.
        Traverse the array and update the value of the key.
        Later, check if the value is greater than the floor of N/3.
        If yes, return the key
        Else iterate forward.

        Time Complexity: O(2n)
            O(n) + O(n) - one O(n) for first for loop to fill hash map, second to iterate through hashmap and get the majority element
            Note: If there, a majority element exists, then the size of hashmap will be lesser than `n` - array size. In this case second loop complexity will be lesser than O(n)
                    but we have to consider worst case, if all the elements are unique and no majority element exists.

        Space Complexity: O(n)
     */
    public static List<Integer> majorityElementBetterApproach(int[] arr){
        HashMap<Integer, Integer> elementsCount = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            int freq = elementsCount.getOrDefault(arr[i], 0);
            elementsCount.put(arr[i], freq + 1);
        }

        for(HashMap.Entry<Integer, Integer> entry: elementsCount.entrySet()){
            if (entry.getValue() > Math.floor(arr.length / 3)) {
                result.add(entry.getKey());
            }
        }
        return  result;
    }

    /*
    Approach:

        Optimal Approach (Extended Boyer Moore’s Voting Algorithm):

        Note: we have to return all elements who have occurred more than n/3 times in the array.
        With this background, there can be maximum 2 elements of such kind.
        Let's consider an example: If array size is 8
        then (int)(8/3) is 2, it means there can be maximum 2 elements which can occur for more than n/3 times.

        Algorithm:

        Initialize 4 variables:
        cnt1 & cnt2 –  for tracking the counts of elements
        el1 & el2 – for storing the majority of elements.

        Traverse through the given array.
        If cnt1 is 0 and the current element is not el2 then store the current element of the array as el1 along with increasing the cnt1 value by 1.
        If cnt2 is 0 and the current element is not el1 then store the current element of the array as el2 along with increasing the cnt2 value by 1.

        If the current element and el1 are the same increase the cnt1 by 1.
        If the current element and el2 are the same increase the cnt2 by 1.

        Below case means none of the above condition didn't hold-so decrement both the counts.
        Other than all the above cases: decrease cnt1 and cnt2 by 1.

        The integers present in el1 & el2 should be the result we are expecting.

        So, using another loop, we will manually check their counts if they are greater than the floor(N/3).

        Intuition: If the array contains the majority of elements, their occurrence must be greater than the floor(N/3).
        Now, we can say that the count of minority elements and majority elements is equal up to a certain point in the array.
        So when we traverse through the array we try to keep track of the counts of elements and the elements themselves for which we are tracking the counts.

        After traversing the whole array, we will check the elements stored in the variables.
        Then we need to check if the stored elements are the majority elements or not by manually checking their counts.

        Time Complexity: O(2n)
        Space Complexity: O(1)
     */
    public static List<Integer> majorityElementOptimal(int[] arr){
        int count1 = 0, count2 = 0;
        int element1 = Integer.MIN_VALUE;
        int element2 = Integer.MIN_VALUE;

        List<Integer> result = new ArrayList<>();
        int n = arr.length;
        for(int i = 0; i < n; i++){
            // this second operand comparison is because if a variable is present in one element and counter
            // the same element should not be set to other counter and element, to avoid that element2 != arr[i]
            if(count1 == 0 && element2 != arr[i]){
                count1 = 1;
                element1 = arr[i];
            }
            // this second operand comparison is because if a variable is present in one element and counter
            // the same element should not be set to other counter and element, to avoid that element1 != arr[i]
            else if (count2 == 0 && element1 != arr[i]) {
                count2 = 1;
                element2 = arr[i];
            } else if(arr[i] == element1) {
                count1++;
            } else if(arr[i] == element2){
                count2++;
            } else{
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for(int i = 0; i < n; i++){
            if(arr[i] == element1)
                count1++;
            if(arr[i] == element2)
                count2++;
        }

        int minimumCountRequired = (int)n/3;

        if (count1 > minimumCountRequired) {
            result.add(element1);
        }
        if (count2 > minimumCountRequired) {
            result.add(element2);
        }

        return result;
    }

    public static void main(String args[]) {
        int[] arr = {1, 1, 1, 3, 3, 2, 2, 2};
        List<Integer> ans = majorityElementBruteForce(arr);
        List<Integer> ans2 = majorityElementBetterApproach(arr);
        List<Integer> ans3 = majorityElementOptimal(arr);
        System.out.println("The majority element using brute force approach: " + ans);
        System.out.println("The majority element using better approach(Hashing): " + ans2);
        System.out.println("The majority element using optimal approach(Moore's Voting Algorithm): " + ans3);

    }
}
