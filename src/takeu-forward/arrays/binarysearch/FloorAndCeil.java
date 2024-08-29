package arrays.binarysearch;

/*
Problem:
    https://takeuforward.org/arrays/floor-and-ceil-in-sorted-array/
    https://www.naukri.com/code360/problems/ceiling-in-a-sorted-array_1825401
 */
public class FloorAndCeil {

    /*
    Approach:
        To find ceil, we are using lower Bound algorithm, check LowerBound.java
        To find floor, we are slighly modifying lower bound algorithm.
            we say, we need to find the maximum element in the array, such that it should be lesser than or equal to target
        so we initialize floor = -1
        low = 0, high = n-1;
            we calculate mid
                if arr[mid] > target, then all the elements to its right will be greater than target, no need to check them
                so move to left sub array, using high = mid-1
                else
                    it can be two cases
                        - arr[mid] can be equal to target
                        - arr[mid] is lesser than target
                     so in both the cases, we will set floor to max(floor, mid)
                     once we do that, we again search can there be any element greater than mid, but lesser than or equal to target
                     so, we move to right sub array
                        by low = mid + 1

       Time Complexity: O(log n)
       Space Complexity: O(1)
     */
    public static int[] getFloorAndCeil(int[] arr, int n, int x){
        int low = 0, high = n-1;
        int floor = -1, ceil = n;

        // find ceil
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] < x){
                low = mid + 1;
            } else{
                ceil = Math.min(ceil, mid);
                high = mid - 1;
            }
        }

        low = 0; high = n-1;
        // find floor
        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] > x){
                high = mid - 1;
            }else{
                floor = Math.max(floor, mid);
                low = mid + 1;
            }
        }
        return new int[]{floor, ceil};
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 4, 7, 8, 10};
        int n = 6, x = 5;
        int[] ans = getFloorAndCeil(arr, n, x);
        System.out.println("The floor and ceil are: " + ans[0]
                + " " + ans[1]);
    }
}
