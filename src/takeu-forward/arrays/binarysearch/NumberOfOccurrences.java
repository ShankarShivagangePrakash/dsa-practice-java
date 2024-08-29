package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/data-structure/count-occurrences-in-sorted-array/
    https://www.geeksforgeeks.org/problems/number-of-occurrence2259/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=number-of-occurrence
 */
public class NumberOfOccurrences {

    /*
    Approach:
        If we do a linear search and increment count when we find the element `x` - we can solve it in O(n)

        But we can still improvise it is sorted array.
        The idea is we find the first and last index of the `x` each operation takes O(log n)
        then subtract their index + 1 will the answer,
        so we will solve it in O(log n)

        Time Complexity: O(log n)
        Space Complexity: O(1)
     */
    public static int countOccurrences(int arr[], int n, int x) {
        int[] ans = firstAndLastPosition(arr, n, x);
        if (ans[0] == -1) return 0;
        return (ans[1] - ans[0] + 1);
    }

    public static int[] firstAndLastPosition(int[] arr, int n, int k) {
        int first = firstOccurrence(arr, n, k);
        if (first == -1) return new int[] { -1, -1};
        int last = lastOccurrence(arr, n, k);
        return new int[] {first, last};
    }

    public static int firstOccurrence(int[] arr, int n, int x){
        int low = 0, high = n-1;
        int floor = n;

        // find first occurance of x
        while(low <= high){
            int mid = (low + high)/2;

            if(arr[mid] == x){
                floor = Math.min(floor, mid);
                high = mid - 1;
            } else if(x < arr[mid]){
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        // If x is not present, then we have to reset floor to -1
        if(floor == arr.length){
            floor = -1;
        }
        return floor;
    }

    public static int lastOccurrence(int[] arr, int n, int x){
        int ceil = -1;

        int low = 0, high = arr.length - 1;

        // find ceil
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] == x){
                ceil = Math.max(ceil, mid);
                low = mid+1;
            } else if(x < arr[mid]){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
        int[] arr =  {2, 4, 6, 8, 8, 8, 11, 13};
        int n = 8, x = 8;
        int ans = countOccurrences(arr, n, x);
        System.out.println("The number of occurrences is: " + ans);
    }
}
