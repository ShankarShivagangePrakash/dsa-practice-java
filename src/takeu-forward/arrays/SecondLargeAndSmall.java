package arrays;
/*
Problem:
    https://takeuforward.org/data-structure/find-second-smallest-and-second-largest-element-in-an-array/
    https://www.geeksforgeeks.org/problems/second-largest3735/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=second-largest
 */
public class SecondLargeAndSmall {

    /*
    Approach:
        We would require four variables: small,second_small, large, and second_large.
        Variable small and second_small are initialized to INT_MAX
        while large and second_large are initialized to INT_MIN.

        Second Smallest Algo:
        If the current element is smaller than ‘small’, then we update second_small and small variables
        Else if the current element is smaller than ‘second_small’ then we update the variable ‘second_small’
        Once we traverse the entire array, we would find the second-smallest element in the variable second_small.

        Second Largest Algo:
        If the current element is larger than ‘large’ then update second_large and large variables
        Else if the current element is larger than ‘second_large’ then we update the variable second_large.
        Once we traverse the entire array, we would find the second-largest element in the variable second_large.
     */
    public static int secondLargest(int[] arr){
        int large = Integer.MIN_VALUE;
        int second_large = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] > large){
                second_large = large;
                large = arr[i];
            } else if (arr[i] > second_large && arr[i] != large) {
                second_large = arr[i];
            }
        }
        return second_large;
    }
    public static int secondSmallest(int[] arr){
        int small = Integer.MAX_VALUE;
        int second_small = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] < small){
                second_small = small;
                small = arr[i];
            } else if (arr[i] < second_small && arr[i] != small) {
                second_small = arr[i];
            }
        }
        return second_small;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 5, 4, 3};

        System.out.printf("Second largest element in the array is %d\n", secondLargest(arr));
        System.out.printf("Second smallest element in the array is %d\n", secondSmallest(arr));
    }
}
