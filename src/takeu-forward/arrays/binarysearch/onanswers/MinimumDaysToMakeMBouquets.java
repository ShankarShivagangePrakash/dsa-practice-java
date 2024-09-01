package arrays.binarysearch.onanswers;

/*
Problem:
    https://takeuforward.org/arrays/minimum-days-to-make-m-bouquets/
    https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
 */
public class MinimumDaysToMakeMBouquets {

    /*
    Approach:
        We write a function `findBouquetsPossibleOfSpecifiedSize()` which will give number of bouquets which can be made each having `k` flowers on specified day.
        Noe: to make a bouquet having `k` flowers, those `k` flowers should be present in adjacent positions and should have bloomed.

        since arr[i] represents the time required for flower `i` to bloom
        Maximum time required for any flower to bloom will be max(array)

        With this background, we try to solve problem

        If we can form `m` bouquets, each having `k` flowers, and we need to find the minimum days required to make it
        we need to find the minimum days required so that those many flowers have bloomed and are in adjacent positions to form required number of bouquets
        The answer (number of days) can range from 1 to max(array) - common sense

        so what we can do is, we can apply binary search in this range
            we initialize ans = -1; // -1 represents not possible.
            low = 1, high = max(array)
                calculate mid and find how many bouquets can be formed each having k flowers on day `mid`
                if it is greater than or equal to `m` - which represents number of bouquets required
                    then it can be our answer, but since we need to find minimum days required, move to left in sub array
                else
                    move right in sub array
            return `ans`;

        Logic to findBouquetsPossibleOfSpecifiedSize(int[] arr, int numberOfFlowersToBePresentInBouquet, int days)
            this method will return number of bouquets we can form each having k flowers on specified day.
            to form a bouquet of size `k`, then we need to have `k` adjacent bloomed flowers
                to check whether flower has bloomed or not?
                    you can simply do arr[i] <= days
                        if so bloomed
                     else
                        not bloomed

               we keep two more variables
                - bouquetsPossible
                - count // to count adjacent flowers which can bloom within specified number of days.

               we iterate through each element of the array.
               if the condition arr[i] <= days is satisfied, then we increment count
                  along with we check for the value of `count`, if it is equal to `numberOfFlowersToBePresentInBouquet`, then we increment `bouquetsPossible`
                  also we reset `count` to 0, because we have formed the bouquet using all these flowers

               else:
                    we reset count to 0, because we have to use adjacent flowers only to make bouquet. So whatever could be the count - we have to leave previous flowers
                    and check freshly from next index whether can we form bouquet.

             at the end, we return `bouquetsPossible`

        Time Complexity: O(n * log(max element in the array))
        Space Complexity: O(1)
     */
    public static int minDaysRequired(int[] arr, int m, int k){
        int low = 1, high = maximumInArray(arr);
        int res = -1;

        while(low <= high){
            int mid = (low + high)/2;

            int totalBouquetsPossible = findBouquetsPossibleOfSpecifiedSize(arr,k, mid);

            if(totalBouquetsPossible >= m){
                res = mid;
                high = mid -1;
            } else{
                low = mid + 1;
            }
        }
        return res;
    }

    public static int maximumInArray(int[] arr){
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    public static int findBouquetsPossibleOfSpecifiedSize(int[] arr, int numberOfFlowersToBePresentInBouquet, int days){
        int bouquetsPossible = 0;

        // to count adjacent flowers which can bloom within specified number of days.
        int count = 0;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] <= days){
                count++;
                if(count == numberOfFlowersToBePresentInBouquet){
                    bouquetsPossible++;
                    count = 0;
                }
            }
            else{
                count = 0;
            }
        }
        return bouquetsPossible;
    }

    public static void main(String[] args) {
        int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};
        int k = 3; // k represents number of flowers should be there in each bouquet.
        int m = 2; // m represents number of bouquets, each having k number of flowers.
        System.out.printf("Number of days required to make %d bouquets each having %d flowers is: %d\n ", m,k, minDaysRequired(arr, m, k));
    }
}
