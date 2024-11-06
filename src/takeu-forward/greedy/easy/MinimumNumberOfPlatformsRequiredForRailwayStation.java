package greedy.easy;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/minimum-number-of-platforms-required-for-a-railway/
    https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
 */
public class MinimumNumberOfPlatformsRequiredForRailwayStation {

    /*
    Approach:
        Explain in notes, check

        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    public static int minimumNumberOfPlatformsRequiredForRailwayStationBruteforce(int[] arrival, int[] departure) {
        int ans = 1;
        int n = arrival.length;

        for (int i = 0; i < n; i++) {
            int count = 1; // count of overlapping interval of only this iteration
            for (int j = i + 1; j < n; j++) {
                if ((arrival[i] >= arrival[j] && arrival[i] <= departure[j]) ||
                        (arrival[j] >= arrival[i] && arrival[j] <= departure[i])) {
                    count++;
                }
            }
            // update ans to maximum number of intersections.
            ans = Math.max(ans, count);
        }
        return ans;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: 2 *(O(n * logn) + O(n))

            O(n * log n) for sorting arrival array
            O(n * log n) for sorting departure array

            O(2n) for while loop
                because in while loop, sometimes we are incrementing i
                sometimes we are incrementing j
                so it runs for 2n times

        Space Complexity: O(1)
     */
    public static int minimumNumberOfPlatformsRequiredForRailwayStationOptimal(int[] arrival, int[] departure) {
        // sort arrival and departure times
        Arrays.sort(arrival);
        Arrays.sort(departure);

        // index `i` refers to arrival times and `j` refers to departure times
        int i = 0, j = 0;
        int n = arrival.length;
        /* count referes to number of platforms required at a particular time
         maxCount refers to maximum value of count in the entire process, which holds answer*/
        int count = 0, maxCount = 0;

        while(i < n){

            /* if a new train has arrived before the previous train has departed,
             we need a new platform, so increment `count`, i++ means go to next train(arrival)*/
            if(arrival[i] <= departure[j]){
                count++;
                i++;
            }
            else{
                /* else means, the previous train has departed before current train comes
                 so we decrement count as the previous platform is not longer needed
                 do j++ because we move to next departure time.*/
                count--;
                j++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}
