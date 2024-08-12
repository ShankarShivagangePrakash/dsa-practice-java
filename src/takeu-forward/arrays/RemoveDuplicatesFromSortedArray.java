package arrays;

import java.util.HashSet;

/*
Problem:
    https://takeuforward.org/data-structure/remove-duplicates-in-place-from-sorted-array/
    https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 */
public class RemoveDuplicatesFromSortedArray {

    /*
    Intuition: We have to think of a data structure that does not store duplicate elements. So can we use a Hash set? Yes! We can. As we know HashSet only stores unique elements.

    Approach:
        Declare a HashSet.
        Run a for loop from starting to the end.
        Put every element of the array in the set.
        Store size of the set in a variable K.
        Now put all elements of the set in the array from the starting of the array.
        Return K.

        time complexity: O( n*logn + n)
                n*logn is for inserting element into hash set
                n is for iterating through element of set and replace first k index of original array with unique elements.
     */
    public static int removeDuplicatesBruteForce(int[] arr){
        HashSet<Integer> set = new HashSet();

        for (int i = 0; i < arr.length; i++) {
            // Inserting into hash set requires O(n*log n)
            set.add(arr[i]);
        }

        int j = 0;
        // looping this set under worst case would be O(n) - when there are no duplicates
        for (int i : set) {
            arr[j++] = i;
        }
        // you can return j or set.size() - both will be same.
        return j;
    }

    /*
    Intuition: We can think of using two pointers ‘i’ and ‘j’,
    we move ‘j’ till we don't get a number arr[j] which is different from arr[i].
    As we got a unique number(means number different from arr[i]) we will increase the i pointer and update its (arr[++i]) value by arr[j].

    Approach:
    Take a variable i as 0;
    Use a for loop by using a variable ‘j’ from 1 to length of the array.
    If arr[j] != arr[i], increase ‘i’ and update arr[i] == arr[j].
     After completion of the loop return i+1, i.e size of the array of unique elements.
     + 1 is required because we don't modify first element of the array, but even that is unique element. So +1 while returning result.

     check the YouTube video in same page or read article.

     Time complexity: O(n)
     Space complexity: O(1)
     */
    public static int removeDuplicatesOptimal(int[] arr){
        int i = 0;
        for(int j = 1; j< arr.length; j++ ){
            if (arr[i] != arr[j]) {
                arr[++i] = arr[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int arr[] = {1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4};
        int arr2[] = {1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4};

        System.out.printf("Number of duplicate elements in the array: %d\n", removeDuplicatesBruteForce(arr));
        System.out.printf("Number of duplicate elements in the array: %d\n", removeDuplicatesOptimal(arr2));
    }
}
