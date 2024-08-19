package arrays.medium;

/*
Problem:
    https://takeuforward.org/data-structure/stock-buy-and-sell/
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class StockBuyAndSell {

    /*
    Approach:
        Intuition: We can simply use 2 loops and track every transaction and maintain a variable maxPro to contain the max value among all transactions.

        Steps are as follows:

        Use a for loop of ‘i’ from 0 to n.
        Use another for loop of j from ‘i+1’ to n.
        If arr[j] > arr[i] , take the difference and compare  and store it in the maxPro variable.
        Return maxPro.

        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */
    public static int maxProfitBruteForce(int[] arr){
        int maxProfit = 0;

        for(int i = 0; i < arr.length -1; i++){
            for(int j = i+1; j < arr.length; j++){
                maxProfit = Math.max(maxProfit, arr[j] - arr[i]);
            }
        }
        return maxProfit;
    }

    /*
    Approach:
        Intuition: We will linearly travel the array.
        We can maintain a minimum from the start of the array and compare it with every element of the array,
        if it is greater than the minimum then take the difference and maintain it in max, otherwise update the minimum.

        Steps:

        Create a variable maxProfit and store 0 initially.
        Create a variable minPrice and store some larger value(ex: MAX_VALUE) value initially.
        Run a for loop from 0 to n.
        Update the minPrice if it is greater than the current element of the array
        Take the difference of the minPrice with the current element of the array and compare and maintain it in maxProfit.
        Return the maxPro.

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static int maxProfitOptimal(int[] arr){
        int maxProfit = 0;

        int minValue = Integer.MAX_VALUE;

        for(int i = 0; i < arr.length; i++){
            minValue = Math.min(minValue, arr[i]);
            maxProfit = Math.max(maxProfit, arr[i] - minValue);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int arr[] = {7,1,5,3,6,4};
        int maxProfit = maxProfitBruteForce(arr);
        int maxProfit2 = maxProfitOptimal(arr);
        System.out.println("Max profit using Brute force approach: " + maxProfit);
        System.out.println("Max profit using optimal approach: " + maxProfit2);
    }
}
