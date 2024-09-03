package arrays.binarysearch.onanswers.minmax;
import java.util.Arrays;
/*
Problem:
    https://takeuforward.org/data-structure/aggressive-cows-detailed-solution/
    https://www.naukri.com/code360/problems/aggressive-cows_1082559?utm_source=striver&utm_medium=website&utm_campaign=codestudio_a_zcourse
    https://www.spoj.com/problems/AGGRCOW/
 */
public class AggressiveCows {

    /*
    Brute Force:
        Check Notes

        Time Complexity: O(n * log n) + O(n * (arr[max] - arr[min]))
                        which is closer to O(n^2)
        Space Complexity: O(1)
     */
    public static int aggressiveCowsBruteForce(int[] arr, int noOfCows){
        int n = arr.length;
        // takes O(n*log n)
        Arrays.sort(arr);

        // we know that answer will range from 1 to (max(arr) - min(arr))
        // since array is sorted to get (max(arr) - min(arr), just do.
        int limit = arr[n-1] - arr[0];

        for(int i = 1; i <= limit; i++){

            // instead writing unnecessary logic, if we `canWePlace` returns true - continue
            // else return (i-1), just check for false scenario, true scenario will be implicitly taken care.
            if(canWePlaceCows(arr, noOfCows, i) == false)
                return (i-1);
        }
        // if we are coming out of the loop mean, we are able to maintain a minimum distance of `limit` between each cows
        // which is the maximum possible - return it.
        return limit;
    }

    public static int aggressiveCowsOptimal(int[] arr, int noOfCows){
        int n = arr.length;
        Arrays.sort(arr);

        // low minimum possible distance
        int low = 1;
        int high = arr[n-1] - arr[0];

        while(low <= high){
            int mid = (low + high)/2;

            // check, can we maintain a minimum distance of `mid` value
            // if possible, we try can we maintain more distance. so we move to right in sub array.
            if(canWePlaceCows(arr, noOfCows, mid)){
                low = mid + 1;
            }
            // else means we couldn't maintain a distance of `mid` we have to try with lower distance. so move left in sub array
            else{
                high = mid - 1;
            }
        }
        // we are not storing possible distance in a separate variable.
        // we know that high has crossed low, that is the reason while loop ended.
        // that will be the maximum distance possible. return it that is the answer.
        return high;
    }

    public static boolean canWePlaceCows(int[] arr, int noOfCows, int distanceToMaintain){
        // we will place first cow at first stall available.
        int count = 1, lastFilledStall = arr[0];

        // we use loop to place remaining cows
        // since we have already placed first cow at first stall, start from second stall.
        for(int i = 1; i < arr.length; i++){
            int distance = arr[i] - lastFilledStall;

            if(distance >= distanceToMaintain){
                // we can place cow here
                count++;
                lastFilledStall = arr[i];
            }

            // if we have placed required number of cows `noOfCows`, don't further iterate just return true.
            if(count >= noOfCows){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] stalls = {0, 3, 4, 7, 10, 9};
        int k = 4;
        System.out.printf("The maximum possible minimum distance using brute force approach is: %d\n", aggressiveCowsBruteForce(stalls, k));
        System.out.printf("The maximum possible minimum distance using optimal approach is: %d\n", aggressiveCowsOptimal(stalls, k));
    }
}
