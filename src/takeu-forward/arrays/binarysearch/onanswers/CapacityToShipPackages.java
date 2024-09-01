package arrays.binarysearch.onanswers;

/*
Problem:
    https://takeuforward.org/arrays/capacity-to-ship-packages-within-d-days/
    https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
 */
public class CapacityToShipPackages {

    /*
    Approach:
        we know that to ship should be able to carry the maximum weight (means it should be able to carry max(array))
        so it's capacity should be minimum max(array)
        Maximum capacity of the ship can be entire array summation. so that it can carry all items in one single day
        Ship's capacity can vary in this range
            max Element in the array <= Ship Capacity <= summation of elements in the array.

        we need to find the minimum capacity possible in this range, such that it can carry all goods in specified days.
        we have to carry items, in the specified order

        so we write, three functions
            - function to return max and summation of the array, this will be used as low and high for binary search
            - function which returns number of days required to carry all items with specified capacity.
            - main function, we invoke above two functions there and we follow binary search tedhnique to search for capacity in low to high range
                for the capacity we chose, if we can transport all goods in less than specified days
                    it can be our answer, we update and move left in the sub array to find even smaller capactity which satisfies the criteria.
                else
                    we move right side in the sub array.

      Time Complexity:
            O(n * log(sum(weights[]) - max(weights[]) + 1)
                    where sum(weights[]) = summation of all the weights,
                    max(weights[]) = maximum of all the weights, N = size of the weights array.
                    Reason: We are applying binary search on the range [max(weights[]), sum(weights[])].
                    For every possible answer ‘mid’, we are calling findDays() function. Now, inside the findDays() function, we are using a loop that runs for n times.

        Space Complexity: O(1)
     */
    public static int leastWeightCapacity(int[] arr, int days){
        int capacity = Integer.MAX_VALUE;

        int[] temp = getMaximumAndTotalSumOfArray(arr);
        int low = temp[0], high = temp[1];

        while(low <= high){
            int mid = (low + high)/2;

            if(daysRequiredToShipWithCapacityMid(arr, mid) <= days){
                capacity = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return capacity;
    }

    public static int[] getMaximumAndTotalSumOfArray(int[] arr){
        int max = Integer.MIN_VALUE, sum = 0;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max)
                max = arr[i];

            sum += arr[i];
        }
        return new int[]{max, sum};
    }

    /*
        We initialize days required as 1,
        and we keep a `count` which is initialized with 0

            inside the loop we keep adding arr[i] to count
            if it crosses the `capacity` means we need to add that additional weights to next day travel.
            so we increment count, and we reset count as arr[i]
        we return days.

        Time complexity: O(n)
     */
    public static int daysRequiredToShipWithCapacityMid(int[] arr, int capacity){
        int days = 1;
        int count = 0;

        for(int i = 0; i < arr.length; i++){
            count += arr[i];

            if(count > capacity){
                days++;
                count = arr[i];
            }
        }
        return days;
    }

    public static void main(String[] args) {
        int[] weights = {5, 4, 5, 2, 3, 4, 5, 6};
        int days = 5;
        System.out.printf("The minimum capacity to ship within % days should be: %d\n", days, leastWeightCapacity(weights, days));
    }
}
