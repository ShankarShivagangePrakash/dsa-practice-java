package arrays;

/*
Problem:
    https://takeuforward.org/arrays/find-the-missing-number-in-an-array/
    https://leetcode.com/problems/missing-number/description/
 */
public class MissingNumber {

    /*
    Approach:
        Using the hashing technique, we will store the frequency of each element of the given array.
        Now, the number( i.e. between 1 to N) for which the frequency will be 0, will be returned.

        The algorithm steps are as follows:

            The range of the number is 1 to N. So, we need a hash array of size N+1 (as we want to store the frequency of N as well).
            Now, for each element in the given array, we will store the frequency in the hash array.
            After that, for each number between 1 to N, we will check the frequencies. And for any number, if the frequency is 0, we will return it.

        Time complexity: O(n)
            to be specific, it is O(n) + O(n) = O(2n)
        Space complexity: O(n)

     */
    public static int missingNumberBruteForce(int[] arr, int N){
        // hash array to store frequencies of all elements of the array and the missing number.
        // we store frequencies of all numbers from 1 to N, and we don't use index 0, so array size is N + 1.
        int[] hash = new int[N + 1];

        for(int i = 0; i < arr.length; i++){
            hash[arr[i]]++;
        }

        for(int i =1; i < hash.length; i++){
            if(hash[i] == 0){
                return i;
            }
        }
        return -1;
    }

    /*
    Approach:
        We know that the summation of the first N numbers is (N*(N+1))/2. We can say this Sum1.
        Now, in the given array, every number between 1 to N except one number is present.
        So, if we add the numbers of the array (say Sum2), the difference between Sum1 and Sum2 will be the missing number.
         Because, while adding all the numbers of the array, we did not add that particular number that is missing.

        The steps are as follows:

            We will first calculate the summation of first N natural numbers(i.e. 1 to N) using the specified formula.
            Then we will add all the array elements using a loop.
            Finally, we will consider the difference between the summation of the first N natural numbers and the sum of the array elements.

        Time complexity: O(n)
        Space complexity: O(1)
     */
    public static int missingNumberOptimal1(int[] arr, int N){
        int sum1 = (N * (N + 1))/2;

        int sum2 = 0;
        for(int i = 0; i < arr.length; i++){
            sum2 += arr[i];
        }
        return sum1 - sum2;
    }

    /*
    Approach:
        We use XOR logic operator to solve
        XOR will return 0, if both inputs are same, 1 if they are different ( means if one is zero and other is 1)
        Also remember XOR of any number with 0, is that number itself.

        XOR of two same numbers is always 0 i.e. a ^ a = 0. ← Property 1.
        XOR of a number with 0 will result in the number itself i.e. 0 ^ a = a.  ← Property 2

        Now, let’s XOR all the numbers between 1 to N.
    xor1 = 1^2^.......^N

    Let’s XOR all the numbers in the given array.
    xor2 = 1^2^......^N (contains all the numbers except the missing one).

    Now, if we again perform XOR between xor1 and xor2, we will get:
    xor1 ^ xor2 = (1^1)^(2^2)^........^(missing Number)^.....^(N^N)

    Here all the numbers except the missing number will form a pair and each pair will result in 0 according to the XOR property.
    The result will be = 0 ^ (missing number) = missing number (according to property 2).

    Time complexity: O(2n)
    space complexity: O(1)
     */
    public static int missingNumberOptimal2EasyCode(int[] arr, int N){

        int xor1 = 0, xor2 = 0;
        // find XOR of all numbers from 1 to N
        for(int i = 1; i <=N; i++){
            xor1 = xor1 ^ i;
        }

        // condition N -1, means arr length will be always one less than N
        // and since we are starting at `i` value 0, it is ` < (less than) N -1 `
        // find XOR of numbers in the given array.
        for(int i = 0; i < N - 1; i++){
            xor2 = xor2 ^ arr[i];
        }

        // XOR1 ^ XOR2
        // it means (1^1)^(2^2)^........^(missing Number)^.....^(N^N) which returns missing number
        return xor1 ^ xor2;
    }

    /*
    Approach:
        We use XOR logic operator to solve
        XOR will return 0, if both inputs are same, 1 if they are different ( means if one is zero and other is 1)
        Also remember XOR of any number with 0, is that number itself.

        XOR of two same numbers is always 0 i.e. a ^ a = 0. ← Property 1.
        XOR of a number with 0 will result in the number itself i.e. 0 ^ a = a.  ← Property 2

        Now, let’s XOR all the numbers between 1 to N.
    xor1 = 1^2^.......^N

    Let’s XOR all the numbers in the given array.
    xor2 = 1^2^......^N (contains all the numbers except the missing one).

    Now, if we again perform XOR between xor1 and xor2, we will get:
    xor1 ^ xor2 = (1^1)^(2^2)^........^(missing Number)^.....^(N^N)

    Here all the numbers except the missing number will form a pair and each pair will result in 0 according to the XOR property.
    The result will be = 0 ^ (missing number) = missing number (according to property 2).

    Algorithm:
        In this method, instead of having two for loops we will find xor1 and xor2 in single for loop itself.

    Time complexity: O(n)
    space complexity: O(1)
     */
    public static int missingNumberOptimal2CorrectCode(int[] arr, int N){
        int xor1 = 0, xor2 = 0;

        for(int i = 0; i < N-1; i++){
            xor1 = xor1 ^ arr[i];
            xor2 = xor2 ^ (i +1);
        }
        // from the above loop, xor2 is calculated for complete input array.
        // xor2 is calculated starting from 1 (when i is zero, i + 1 means, 1) till N-1
        // now we need to calculate xor2 for N as well

        xor2 = xor2 ^ N;

        return xor1 ^ xor2;
    }

    public static void main(String[] args) {
        int N = 5;
        int a[] = {1, 2, 4, 5};

        int ans1 = missingNumberBruteForce(a, N);
        int ans2 = missingNumberOptimal1(a, N);
        int ans3 = missingNumberOptimal2EasyCode(a, N);
        int ans4 = missingNumberOptimal2CorrectCode(a, N);
        System.out.println("The missing number using brute force approach: " + ans1);
        System.out.println("The missing number using Optimal approach (summation technique): " + ans2);
        System.out.println("The missing number using Optimal approach (XOR approach correct code to explain ): " + ans3);
        System.out.println("The missing number using Optimal approach (XOR approach correct code to explain to interviewer): " + ans4);
    }
}
