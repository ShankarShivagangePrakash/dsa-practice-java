package starpatterns;

import java.util.Scanner;

/*
Problem1:
    https://takeuforward.org/pattern/pattern-1-rectangular-star-pattern/
    https://www.geeksforgeeks.org/problems/square-pattern/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=pattern_1
    Solution method name: pattern1()

Problem2:
    https://takeuforward.org/pattern/pattern-2-right-angled-triangle-pattern/
    https://www.geeksforgeeks.org/problems/right-triangle/1
    Solution method name: pattern2()

Problem3:
    https://takeuforward.org/pattern/pattern-3-right-angled-number-pyramid/
    https://www.geeksforgeeks.org/problems/triangle-number/1
    Solution method name: pattern3()

Problem4:
    https://takeuforward.org/pattern/pattern-4-right-angled-number-pyramid-ii/
    https://www.geeksforgeeks.org/problems/triangle-number-1661428795/1
    Solution method name: pattern4()

Problem5:
    https://takeuforward.org/pattern/pattern-5-inverted-right-pyramid/
    https://www.geeksforgeeks.org/problems/triangle-pattern/1
    Solution method name: pattern5()

Problem6:
    https://takeuforward.org/pattern/pattern-6-inverted-numbered-right-pyramid/
    https://www.geeksforgeeks.org/problems/triangle-number-1661489840/1
    Solution method name: pattern6()

 */
public class StarPattern1 {

    public static void pattern1(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print("* ");
            }
            System.out.println();
        }

    }

    public static void pattern2(int n){
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++){
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void pattern3(int n){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void pattern4(int n){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void pattern5(int n){
        // This is the outer loop which will loop for the rows.
        for (int i = 0; i < n; i++)
        {
            // This is the inner loop which loops for the column's
            // no. of columns = (N - row index) for each line here.
            for (int j = n; j > i; j--)
            {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void pattern6(int n){
        // This is the outer loop which will loop for the rows.
        for (int i = n; i > 0; i--)
        {
            // This is the inner loop which loops for the column's
            for (int j = 1; j <= i; j++)
            {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        System.out.println("Enter any number");
        int n = in.nextInt();

        System.out.println("\nProblem 1 Output");
        pattern1(n);
        System.out.println("\nProblem 2 Output");
        pattern2(n);

        System.out.println("\nProblem 3 Output");
        pattern3(n);

        System.out.println("\nProblem 4 Output");
        pattern4(n);

        System.out.println("\nProblem 5 Output");
        pattern5(n);

        System.out.println("\nProblem 6 Output");
        pattern6(n);


    }
}
