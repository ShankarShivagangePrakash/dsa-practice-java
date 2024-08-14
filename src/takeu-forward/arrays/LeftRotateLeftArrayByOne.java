package arrays;

/*
Problem:
    https://takeuforward.org/data-structure/left-rotate-the-array-by-one/
    https://leetcode.com/problems/rotate-array/
 */
public class LeftRotateLeftArrayByOne {

    /*
    Approach:
        Have a temp array same as input array size.
        Since we have to move every element to left side by one position
        start from index 1 = `i` (second element in the array) - store them at index [i-1] of temp array
        Keep repeating this process till last element.
        For last position of the temp array, put first element of the input array. Because when we left rotate first element will go to last place.

        Time complexity: O(n)
        Space complexity: O(n)
     */
    public static void leftRotateByOnePositionBruteForce(int[] arr, int n){
        int temp[] = new int[n];

        for(int i = 1; i< n; i++){
            temp[i - 1] = arr[i];
        }
        temp[n-1] = arr[0];
        for(int i = 0; i < n; i++){
            System.out.print( temp[i] + " ");
        }
    }

    /*
    Approach:
        Have a temp variable which will store arr[0]
        Since we have to move every element to left side by one position
        start from index 0 = `i` (first element in the array) - store next element of the array [i + 1] at current index - move elements left.
        Keep repeating this process till last element.
        For last position of the array, put first element from temp variable. Because when we left rotate first element will go to last place.

        Time complexity: O(n)
        Space complexity: O(1)
     */
    public static void leftRotateByOnePositionOptimal(int[] arr, int n){
        int temp = arr[0];

        for(int i = 0; i< n - 1; i++){
            arr[i] = arr[i + 1];
        }
        arr[n-1] = temp;
        for(int i = 0; i < n; i++){
            System.out.print( arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 5, 4, 3};
        int arr2[] = {1, 2, 5, 4, 3};

        System.out.println("Rotate Left array by 1 using brute force approach");
        leftRotateByOnePositionBruteForce(arr, arr.length);

        System.out.println("\nRotate Left array by 1 using optimal approach");
        leftRotateByOnePositionOptimal(arr2, arr2.length);

    }
}
