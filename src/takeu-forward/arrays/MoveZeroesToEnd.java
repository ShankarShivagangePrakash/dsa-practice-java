package arrays;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/move-all-zeros-to-the-end-of-the-array/
    https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroesToEnd {

    /*
    Approach:
        The extremely naive solution, we can think of, involves the use of an extra array (arrayList).
        Suppose, there are N-X zeros and X non-zero elements in the array. We will first copy those X non-zero elements from the original array to a temporary array.
        Then, we will copy the elements from the temporary array one by one and fill the first X places of the original array.
        The last N-X places of the original array will be then filled with zero. Now, our task is completed.

    Time Complexity:
        O(N) + O(X) + O(N-X) ~ O(2*N), where N = total no. of elements,
        In general O(n)

        X = no. of non-zero elements, and N-X = total no. of zeros.
        Reason: O(N) for copying non-zero elements from the original to the temporary array.
        O(X) for again copying it back from the temporary to the original array.
        O(N-X) for filling zeros in the original array. So, the total time complexity will be O(2*N).

     Space complexity:
        O(n) - this is worst case scenario if there are no non-zero elements in the array, then the size of the temp array will be same as input array.
     */
    public static void moveZeroesToEndBruteForce(int[] arr){

        ArrayList<Integer> temp = new ArrayList<>();

        // iterate all the elements of the array and push non-zero elements to temp arraylist - O(n)
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0)
                temp.add(arr[i]);
        }

        int j = 0;
        // get elements from the array list and put them at the front of the array - O(x)
        // where x is number of non-zero elements in the array.
        for(int i : temp){
            arr[j] = i;
            j++;
        }

        // finally fill remaining positions of the array with zero = O(n-x)
        for(int i = j; i < arr.length; i++){
            arr[i] = 0;
        }

        // print all elements of the array.
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    /*
    Approach:
        We use two pointer approach
        The detailed explanation with screenshot is available in One-Notes.
        Notes name: dsa-practice-java- from-takeuforward / Arrays

        But the idea is having two variables `i` and `j`
        `j` will always point to zero element - to do this, we iterate through the array when we find zero element we store that index value to `j` and break from the loop.
        with `i` we iterate the array,
        if we find non-zero elements we swap. Thus, we enable moving non-zero elements to front and zero elements to back.

        Time complexity:
            O(n)
                actually it is O(x) + O(n-x) = x is the index at which we find the first zero element.
                then we iterate remaining elements of the array. we swap non-zero elements with zero elements i.e. (n-x)

         Space complexity:
            O(1) - only two pointer `i` and `j`

     */
    public static void moveZeroesToEndOptimal(int[] arr){

        int j = -1;
        // O(x)
        for(int i = 0; i < arr.length; i++){
            if( arr[i] == 0){
                j = i;
                break;
            }
        }

        // no zeroes present in the array, no need to do anything.
        if(j == -1)
            return;

        // O(n-x)
        for(int i = j + 1; i < arr.length; i++){
                if(arr[i] != 0){
                    // swap elements at position i and j
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    // increment j;
                    j++;
                }
        }

        // print all elements of the array.
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }


    public static void main(String[] args) {
        int arr[] = { 1 ,0 ,2 ,3 ,0 ,4 ,0 ,1};
        int arr2[] = { 1 ,0 ,2 ,3 ,0 ,4 ,0 ,1};

        System.out.println("Move all Zeroes to end of the array using brute force approach\n");
        moveZeroesToEndBruteForce(arr);

        System.out.println("Move all Zeroes to end of the array using optimal approach\n");
        moveZeroesToEndOptimal(arr2);
    }

}
