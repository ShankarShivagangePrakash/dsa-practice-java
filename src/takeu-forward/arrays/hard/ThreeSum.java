package arrays.hard;

import javax.print.attribute.IntegerSyntax;
import java.lang.reflect.Array;
import java.util.*;

/*
Problem:
    https://takeuforward.org/data-structure/3-sum-find-triplets-that-add-up-to-a-zero/
    https://leetcode.com/problems/3sum/description/
 */
public class ThreeSum {

    /*
    Approach:
        This approach is pretty straightforward. Here, we will check all possible triplets using 3 loops and among them,
        we will consider the ones whose sum is equal to the given target i.e. 0. And before considering
        them as our answer we need to sort the triplets in ascending order so that we can consider only the unique ones.

        Algorithm:
        The steps are as follows:

        First, we will declare a set data structure as we want unique triplets.
        Then we will use the first loop(say i) that will run from 0 to n-1.

        Inside it, there will be the second loop(say j) that will run from i+1 to n-1.

        Then there will be the third loop(say k) that runs from j+1 to n-1.

        Now, inside these 3 nested loops, we will check the sum i.e. arr[i]+arr[j]+arr[k],
        and if it is equal to the target i.e. 0 we will sort this triplet and insert it in the set data structure.
        Finally, we will return the list of triplets stored in the set data structure.

        Time Complexity: O(n^3 * O(n)), where n = size of the array.
        Reason: Here, we are mainly using 3 nested loops. And inserting triplets into the hash set takes O(1) under best case and log(n) under worst case.
        we consider worst case time complexity.
        But we are not considering the time complexity of sorting as we are just sorting 3 elements every time.

        Space Complexity: O(2 * no. of the unique triplets) as we are using a set data structure and a list to store the triplets.
     */
    public static List<List<Integer>> tripletBruteForce(int[] arr) {
        HashSet<List<Integer>> tripletsSet = new HashSet<>();

        int n = arr.length;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                for(int k = j+1; k < n; k++){
                    if(arr[i] + arr[j] + arr[k] == 0){
                        List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k]);
                        Collections.sort(temp);
                        tripletsSet.add(temp);
                    }
                }
            }
        }
        // any collection class first <> parameter is to specify the type of variables it stores
        // second parameter () - common brackets are for constructors, if you want to create object using other existing objects you can pass them.
        List<List<Integer>> triplets = new ArrayList<List<Integer>>(tripletsSet);
        return triplets;
    }

    /*
    Approach:
        The steps are as follows:

        First, we will declare a set data structure as we want unique triplets.

        Then we will use the first loop(say i) that will run from 0 to n-1.
        Inside it, there will be the second loop(say j) that will run from i+1 to n-1.

        Before the second loop, we will declare another HashSet to store the array elements as we intend to search for the third element using this HashSet.
        Inside the second loop, we will calculate the value of the third element i.e. -(arr[i]+arr[j]).

        If the third element exists in the HashSet, we will sort these 3 values i.e. arr[i], arr[j], and the third element,
        and insert it in the set data structure declared in step 1.

        After that, we will insert the j-th element i.e. arr[j] in the HashSet as we only want to insert those array elements that are in between indices i and j.
        Finally, we will return a list of triplets stored in the set data structure.

        Time Complexity: O(N2 * number of unique triplets), where N = size of the array.
        Reason: Here, we are mainly using 3 nested loops. And inserting triplets into the set takes O(no. of unique triplets) time complexity under worst case
        but O(1) under best case and note worst case is very rare to occur.
        But we are not considering the time complexity of sorting as we are just sorting 3 elements every time.

        Space Complexity: O(2 * no. of the unique triplets) + O(N)
        as we are using a set data structure
        and a list to store the triplets and extra O(N) for storing the array elements in another set.
     */
    public static List<List<Integer>> tripletBetterApproach(int[] arr){
        HashSet<List<Integer>> tripletSet = new HashSet<>();

        int n = arr.length;
        for(int i = 0; i < n; i++){
            HashSet<Integer> hashSet = new HashSet<>();
            for(int j = i+1; j < n; j++){

                // calculate third element
                int thirdElement = -(arr[i] + arr[j]);

                // find the third element in the hash set.
                if (hashSet.contains(thirdElement)) {
                    List<Integer> temp = Arrays.asList(arr[i], arr[j], thirdElement);
                    Collections.sort(temp);
                    tripletSet.add(temp);
                }
                hashSet.add(arr[j]);
            }
        }
        return new ArrayList<>(tripletSet);
    }

    /*
    Approach:
        The steps are as follows:

        First, we will sort the entire array.

        We will use a loop(say i) that will run from 0 to n-1. This i will represent the fixed pointer.

        Inside the loop, we will first check if the current and the previous element is the same and if it is we will do nothing and continue to the next value of i.

        After that, there will be 2 moving pointers i.e. j(starts from i+1) and k(starts from the last index).
        The pointer j will move forward and the pointer k will move backward until they cross each other while the value of i will be fixed.

        Now we will check the sum i.e. arr[i]+arr[j]+arr[k].
        If the sum is greater, then we need lesser elements and so we will decrease the value of k(i.e. k--).
        If the sum is lesser than the target, we need a bigger value and so we will increase the value of j (i.e. j++).
        If the sum is equal to the target, we will simply insert the triplet i.e. arr[i], arr[j], arr[k] into our answer
        and move the pointers j and k skipping the duplicate elements(i.e. by checking the adjacent elements while moving the pointers).

        Finally, we will have a list of unique triplets.

        Time Complexity: O(N log N)+O(N^2), where N = size of the array.
        Reason:
        n log n - for sorting the array
        The pointer i, is running for approximately N times.
        And both the pointers j and k combined can run for approximately N times including the operation of skipping duplicates.
        So the total time complexity will be O(N^2).

        Space Complexity: O(no. of quadruplets),
        This space is only used to store the answer. We are not using any extra space to solve this problem.
        So, from that perspective, space complexity can be written as O(1).
     */
    public static List<List<Integer>> tripletOptimal(int[] arr){
        List<List<Integer>> triplets = new ArrayList<>();

        // sort the array - O(n log n)
        Arrays.sort(arr);

        int n = arr.length;
        for(int i = 0; i < n; i++){

            // if arr[i] is same as previous element arr[i-1]
            // whether we have formed triplet with i or not doesn't matter we cannot use it, because it might not form triplet
            // or even if it forms, it would be duplicate. That's why we skip this kind of iterations.
            if(i > 0 && arr[i] == arr[i-1])
                continue;

            // create j and k indices
            int j = i+1;
            int k = n-1;

            // run loop till j crosses k.
            while (j < k) {
                int sum = arr[i] + arr[j] + arr[k];

                // since array is sorted,
                // if sum is less than zero means we need to find bigger number. so increment j
                if(sum < 0){
                    j++;
                }
                // if sum is greater than zero means we need to find lesser number. so decrement k
                else if(sum > 0){
                    k--;
                }
                // we have found a triplet add it to the result.
                else{
                    List<Integer> temp = Arrays.asList(arr[i], arr[j], arr[k]);
                    triplets.add(temp);
                    // now increment j and decrement k
                    j++;
                    k--;

                    // there is a possibility that even after incrementing j and decrementing k
                    // the value at newer index might be same as previous, if we continue then we will again generate duplicate triplets
                    // so we increment j till we find different value
                    // same for k, we keep decrementing till we find different value
                    // also, we run this loop till j < k, else there is no point, because if we cross j and k - triplets will not be sorted. Stop
                    while(j < k && arr[j] == arr[j-1])
                        j++;
                    while(j < k && arr[k] == arr[k+1])
                        k--;
                }
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        int[] arr = { -1, 0, 1, 2, -1, -4};
        List<List<Integer>> ans = tripletBruteForce(arr);
        List<List<Integer>> ans2 = tripletBetterApproach(arr);
        List<List<Integer>> ans3 = tripletOptimal(arr);

        System.out.println("3 Sum triplets using brute force approach: " + ans);
        System.out.println("3 Sum triplets using Better approach: " + ans2);
        System.out.println("3 Sum triplets using optimal approach(Two pointer): " + ans3);
    }
}
