package arrays.binarysearch.onanswers;

/*
Problem:
    https://takeuforward.org/binary-search/koko-eating-bananas/
    https://leetcode.com/problems/koko-eating-bananas/

    Problem statement:
        A monkey is given ‘n’ piles of bananas, whereas the 'ith' pile has ‘a[i]’ bananas. An integer ‘h’ is also given,
        which denotes the time (in hours) for all the bananas to be eaten.
        Each hour, the monkey chooses a non-empty pile of bananas and eats ‘k’ bananas.
        If the pile contains less than ‘k’ bananas, then the monkey consumes all the bananas and won’t eat any more bananas in that hour.
        Find the minimum number of bananas ‘k’ to eat per hour so that the monkey can eat all the bananas within ‘h’ hours.

        For example: Piles of bananas are [7, 15, 6, 3] amd Number of hours to complete all piles is 8
        then we check how many bananas it should eat every hour so that it can complete all piles, which we denote with `k` - which we have to find.

        ex: if we take `k` as 1
            then to eat each pile it will take [7, 15, 6, 3] = 31 hours > 8 hours, so it is not enough to eat just one banana every hours

            if k = 2
            check first pile has 7 bananas, our koko can eat 2 bananas in one hour.
            To eat 7 bananas it will take 2.5 hours appropriately,but we have to consider the ceil value of it, so it will become 3.
            so time required will be [3, 8, 3, 2] = 16 > 8

            when k = 4
                it will take more than 8 hours.

            when k = 5
                [2, 3, 2, 1] = 8 <= 8
                so this will be our answer.
           so when k = 6 or more, even then koko can finish all piles within 8 hours,
           maximum value of k will be maximum bananas in all piles.
            in this example, we have chosen it is 15.
           but we have to return minimum `k`
           so answer is 5
 */
public class KokoEatingBanana {

    /*
    Approach:
        we will write a function, for each k we choose it will return the total time required to finish the all piles. = calculateTotalHoursRequired()
            Time complexity for this method will be O(n)
                Reason: it has to visit each pile and find the hours required to finish pile for the chosen `i`

        Brute Force:
            we run a loop from 1 to maximum value in the array
                for each value `i`, we invoke calculateTotalHoursRequired()
                if it is less than or equal to `h`, we return that `i` as answer.

            Time Complexity: O(n * n) = O(n^2)
            Space Complexity: O(1)

       Optimal Approach:
            Using binary search:
                Instead of running a loop from 1 to maximum value in the array
                we can use binary search technique
                    we initialize low = 1, high = max(array)

                        - if calculateTotalHoursRequired() <= `h`
                            then it can be our answer, we update answer and move to left of the array, to find smaller `i` values
                        - else we move to right sub array.

            Negative use case:
                if array size is more than the input `h`
                regardless of how many bananas koko will eat, it cannot finish all piles
                because at max, koko can finish one pile in an hour.
                So to handle this case, we initialize `ans` with value -1

                in this case while loop will execute, but it will never update `ans`, so when we return `ans` it will be -1

            Time Complexity: O(n * log(max element in the array))
            Space Complexity: O(1)
     */
    public static int minimumRateToEatBananas(int[] arr, int hours){
        int res = -1;

        int max = findMaxInArray(arr);
        int low = 1, high = max;

        while(low <= high){
            int mid = (low + high)/2;

            int timeRequired = findTotalHoursRequired(arr, mid);

            // if time required is less than or equal to input `hours` means it is a possible answer
            // but still move to the left to find smaller value of `k`
            if(timeRequired <= hours){
                res = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return res;
    }

    public static int findMaxInArray(int[] arr){
        int max  = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    // O(n)
    public static int findTotalHoursRequired(int[] arr, int days){
        double totalTimeRequired = 0;

        for(int i = 0; i < arr.length; i++){
            /* whenever Math package operation is involved use double or bigDecimal as input and output data type
             else the calculations will not be correct
             when you are returning the result cast it to required data type.*/
            totalTimeRequired += Math.ceil( (double) arr[i] / (double) days );
        }
        return (int) totalTimeRequired;
    }

    public static void main(String[] args) {
        int[] v = {7, 15, 6, 3};
        int h = 8;
        int ans = minimumRateToEatBananas(v, h);
        System.out.println("Koko should eat at least " + ans + " bananas/hr.");
    }
}
