package arrays.hard;

/*
Problem:
    https://takeuforward.org/data-structure/find-the-repeating-and-missing-numbers/
    https://www.geeksforgeeks.org/problems/find-missing-and-repeating2512/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find-missing-and-repeating
 */
public class RepeatingNMissingNumber {

    /*
    ApproachL
        Assume the repeating number to be X and the missing number to be Y.

        The steps are as follows:

        First, find out the values of S and Sn and then calculate S - Sn (Using the above formulas).
        Then, find out the values of S2 and S2n and then calculate S2 - S2n.
        After performing steps 1 and 2, we will be having the values of X + Y and X - Y. Now, by substitution of values, we can easily find the values of X and Y.

        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    public static int[] findMissingRepeatingNumberOptimal1(int[] arr){

        int n = arr.length;

        long s1n = (n * (n+1))/2;
        long s2n = (n * (n+1) * (2*n+1))/6;
        long s1 = 0, s2 = 0;

        for(int i = 0; i < arr.length; i++){
            s1 += arr[i];
            s2 = s2 + (arr[i] * arr[i]);
        }

        // s1n - s1 = will give equation x-y = (some value)  ----> (1)
        long val1 = s1n - s1;

        /* s2n - s2 (sum of squares) will give equation (x^2 - y^2) = (some value)
         but that can be further reduced as (x+y)(x-y) = some value
         value from first equation can be replaced in second equation for (x-y) and compute x+y*/
        long val2 = s2n - s2;

        /* Here i'm doing
         (x+y) = (some value)/(x-y)
         but (x-y) is known, and it is equation 1 - val1*/
        val2 = val2/val1;

        /* now (x+y) is known
         (x+Y) = (some other value) -----> (2)

         now add (1) and (2)
         y and -y cancels out and left side x and x will get added becomes 2x
         right side numbers will get added.
         so in order to get x, divide by 2; since we have 2x on left side*/
        long x = (val1 + val2)/2;

        // now from equation (2) y = (some other value) - x;
        long y = val2 - x;

        return new int[]{(int)y, (int)x};
    }

    /*
    Note:
        This is to improve out problem-solving skill
        In interview explain findMissingRepeatingNumberOptimal1()

        Approach:

            The steps are as follows:

            For the first step, we will run a loop and
            calculate the XOR of all the array elements and the numbers between 1 to N. Let’s call this value xr.
            In order to find the position of the first set a bit from the right,
            we can either use a loop or we can perform AND of the xr and negation of (xr-1) i.e. (xr & ~(xr-1)).
            Now, we will take two variables i.e. zero and one.
            Now, we will check the bit of that position for every element (array elements as well as numbers between 1 to N).
            If the bit is 1: We will XOR that element with variable one.
            If the bit is 0: We will XOR that element with variable zero.
            Finally, we have two variables i.e. two numbers zero and one.
            Among them, one is repeating and the other is missing. It’s time to identify them.
            We will traverse the entire array and check how many times variable zero appears.
            If it appears twice, it will be the repeating number, otherwise, it will be the missing.
            Now, based on variable zero’s identity, we can easily identify in which category, variable one belongs.

        Time Complexity: O(N), where N = the size of the given array.
        Reason: We are just using some loops running for N times. So, the time complexity will be approximately O(N).

        Space Complexity: O(1) as we are not using any extra space to solve this problem.

     */
    public static int[] findMissingRepeatingNUmberOptimal2(int[] arr){
        int n = arr.length; // size of the array
        int xr = 0;

        //Step 1: Find XOR of all elements:
        for (int i = 0; i < n; i++) {
            xr = xr ^ arr[i];
            xr = xr ^ (i + 1);
        }

        //Step 2: Find the differentiating bit number:
        // below we are using the bit manipulation technique to find that differentiating bit
         int number = (xr & ~(xr - 1));


        //Step 3: Group the numbers:
        int zero = 0;
        int one = 0;
        for (int i = 0; i < n; i++) {
            //part of 1 group:
            // this will do Logical AND multiplication bitwise,
            // if the corresponding bit is not zero, then multiplication result will not be zero.
            if ((arr[i] & number) != 0) {
                // instead of inserting the elements into the array and wasting space
                // we can do XOR.
                one = one ^ arr[i];
            }
            //part of 0 group:
            else {
                zero = zero ^ arr[i];
            }
        }

        // do the same thing for every element in the range of (1, N)
        for (int i = 1; i <= n; i++) {
            //part of 1 group:
            if ((i & number) != 0) {
                one = one ^ i;
            }
            //part of 0 group:
            else {
                zero = zero ^ i;
            }
        }

        // Last step: Identify the numbers:
        // one group will have repeating number and other will have missing number,
        // but we don't know which one has which number
        // Note: Only these two numbers will be left in the group, this is because of same numbers will get cancelled in XOR
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == zero) cnt++;
        }

        if (cnt == 2) return new int[] {zero, one};
        return new int[] {one, zero};
    }

    public static void main(String[] args) {
        int[] a = {3, 1, 2, 5, 4, 6, 7, 5};
        int[] ans = findMissingRepeatingNumberOptimal1(a);
        int[] ans2 = findMissingRepeatingNUmberOptimal2(a);
        System.out.println("The repeating and missing numbers  using optimal Approach 1 " +
                "(Tell this in coding interview - Maths) are: {"
                + ans[0] + ", " + ans[1] + "}");

        System.out.println("The repeating and missing numbers  using optimal Approach 2 " +
                "(Don't tell in coding interview - XOR) are: {"
                + ans2[0] + ", " + ans2[1] + "}");
    }
}
