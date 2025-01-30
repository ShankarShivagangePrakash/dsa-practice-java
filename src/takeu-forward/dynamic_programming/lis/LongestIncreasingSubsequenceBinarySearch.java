package dynamic_programming.lis;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/longest-increasing-subsequence-binary-search-dp-43/
    https://geeksforgeeks.org/problems/longest-increasing-subsequence-1587115620/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=longest-increasing-subsequence
 */
public class LongestIncreasingSubsequenceBinarySearch {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * log n)
            for each operation it will take log(n)
            we will insert at max n elements to the array

        Space complexity: O(n)
            if the whole array is sorted in ascending order
                 */
    static int longestIncreasingSubsequenceBinarySearch(int arr[]) {
        ArrayList<Integer> lis = new ArrayList<>();
        lis.add(arr[0]);

        for(int i = 1; i < arr.length; i++){
            if(arr[i] > lis.get(lis.size()-1))
                lis.add(arr[i]);
            else{
                int upperBound = upperBound(lis, lis.size(), arr[i]);
                lis.set(upperBound, arr[i]);
            }
        }
        return lis.size();
    }

    /* gets the index of the element which is equal or larger than target
     but smallest among larger elements
     log(n)*/
    public static int upperBound(ArrayList<Integer> arr, int n, int target){
        int low = 0, high = n-1;

        while(low <= high){
            int mid = (low + high)/2;
            if(arr.get(mid) == target)
                return mid;
            else if(arr.get(mid) < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }
}
