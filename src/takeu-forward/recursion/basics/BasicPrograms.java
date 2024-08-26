package recursion.basics;

import java.util.Scanner;

public class BasicPrograms {

    /*
    Approach:
        since the ask is to print all numbers from 1 to N
        base condition or stopping condition will be when number becomes smaller than 1 or greater than N
        since, we are accepting N as the parameter to the method, keeping base condition as number is smaller than 1 is better option.
        i.e. number == 0 stop/return

        Also, we have to print numbers from 1 to N. But the call to recursive function is in the order
        initially n = 200, then n= 199, 198... so on in decreasing order
        so that's why we call the recursive function by decrementing n

        then after recursive call, have added print statement
        That's how, the recursive call keeps happening, when base condition n == 0 is met
        it will return the control to n = 1, that will print 1
        next n = 2, that call stack's print statement will execute it will print 2
        in that way, we achieve printing elements in ascending order.
     */
    static void print1toN(int n){
        if(n == 0)
            return;

        print1toN(n - 1);
        System.out.printf(n + " ");

    }

    static void printNto1(int n){
        if(n == 0)
            return;

        System.out.printf(n + " ");
        printNto1(n-1);
    }

    static int sumOfFistNNumbers(int n){
        if(n == 1)
            return 1;
        return n + sumOfFistNNumbers(n - 1);
    }

    static long factorial(int n){
        if(n == 1)
            return 1;

        return n* factorial(n-1);
    }

    static void reverseArray(int[] arr, int start, int end){
        if(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            System.out.printf("Sorted index %d and %d\n", start, end);
            reverseArray(arr, start+1, end-1);
        }
    }

    static boolean checkPalindrome(String text){
        return isPalindrome(text.toCharArray(), 0, text.length() -1);
    }

    static boolean isPalindrome(char[] text, int start, int end){
        if(start < end){
            if(text[start] == text[end])
                isPalindrome(text, start +1, end-1);
            else
                return false;
        }
        return true;
    }

    static int fibonacci(int n){

        // base condition, if n is 0 or 1, then we have to return that number itself 0 or 1.
        if( n <= 1) {
            return n;
        }
        // else sum of previous two elements in fibonacci sequence.
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number");
        int n = scanner.nextInt();

        System.out.println("\nPrinting numbers from 1 to " + n);
        print1toN(n);
        System.out.println();

        System.out.println("\nPrinting numbers from N to 1");
        printNto1(n);
        System.out.println();

        System.out.printf("\nSum of first %d natural numbers is %d\n", n, sumOfFistNNumbers(n));

        System.out.printf("\nFactorial of %d is %d\n", n, factorial(n));

        System.out.println("\nReversing the array");
        int[] arr = {1, 2, 3, 4, 5};
        reverseArray(arr, 0, arr.length - 1);

        System.out.println("Printing array after reversed");
        for(int i = 0; i < arr.length; i++){
            System.out.printf(arr[i] + " ");
        }
        System.out.println();

        System.out.println("\nChecking for Palindrome");
        System.out.println("Enter a word");
        String input = scanner.next();
        System.out.println("Is " + input + " Palindrome? " + checkPalindrome(input));

        System.out.printf("Fibonacci element at position %d is %d\n", 5, fibonacci(5));
    }
}
