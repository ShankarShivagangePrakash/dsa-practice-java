package greedy.easy;

/*
Problem:
    https://leetcode.com/problems/candy/
 */
public class Candy {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(3n)
            O(n) for computing left array
            O(n) for computing right array
            O(n) for computing sum

        Space Complexity: O(2n)
            left and right array.
     */
    public static int candyBruteforce(int[] arr){
        int n = arr.length;
        int sum = 0;
        int[] left = new int[n];
        int[] right = new int[n];

        // initialize minimum possible values for first element, when you move from either direction
        left[0] = 1;
        right[n-1] = 1;

        for(int i = 1; i < n; i++){
            if(arr[i] > arr[i-1])
                left[i] = left[i-1] + 1;
            else
                left[i] = 1;
        }

        for(int i = n-2; i >= 0; i--){
            if(arr[i] > arr[i+1])
                right[i] = right[i+1] + 1;
            else
                right[i] = 1;
        }

        for(int i = 0; i < n; i++){
            sum = sum + Math.max(left[i], right[i]);
        }
        return sum;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2n)
            O(n) for computing left array
            O(n) for computing right array and sum

        Space Complexity: O(n)
            left array.
     */
    public static int candyBetterApproach(int[] arr){
        int n = arr.length;
        int sum = 0;
        int[] left = new int[n];

        // initialize minimum possible values for first element, when you move from either direction
        left[0] = 1;

        for(int i = 1; i < n; i++){
            if(arr[i] > arr[i-1])
                left[i] = left[i-1] + 1;
            else
                left[i] = 1;
        }

        /* if you notice, we will start next for loop from (n-2) index
         so we have to add its value to sum*/
        sum = sum + Math.max(left[n-1], 1);
        /* right represents the value for right neighbor initially i = n-1, so right value will be 1
         current represents the value for right[i], initially i = n-1, so current will also be 1*/
        int current = 1, right = 1;
        for(int i = n-2; i >= 0; i--){
            // arr[i] > right neighbor
            if(arr[i] > arr[i+1]) {
                // you are assigning one more candy than it's right neigbor
                current = right + 1;
                // assign current value to right because in next iteration current becomes its right neighbor
                right = current;
            }
            else
                current = 1;

            sum = sum + Math.max(left[i], current);
        }
        return sum;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static int candyOptimal(int[] arr){
        int sum = 1, i = 1;
        int n = arr.length;

        while(i < n){

            // if current rating is equal to previous rating give 1 candy
            if(arr[i] == arr[i-1]){
                sum = sum + 1;
                i++;
                continue;
            }

            // intialize peak to 1
            int peak = 1;
            /* this while loop represents upward slope
             peak current value represents the number of candies we should give for the current child in upward slope*/
            while(i < n && arr[i] > arr[i-1]){
                peak++;
                sum = sum + peak;
                i++;
            }

            /* once above while loop is completed, peak will have number of candies assigned to child in the top of upward slope
             down represents the number of candies assigned to current child in downward slope*/
            int down = 1;
            while(i < n && arr[i] < arr[i-1]){
                sum = sum + down;
                down++;
                i++;
            }

            /* after above while loop is completed, down will have maximum number of candies assigned to child in downard slope
             if down > peak, then for the child in top of upward peak should get more candies
             how many more, the difference*/
            if(down > peak){
                sum = sum + (down - peak);
            }
        }
        return sum;
    }
}
