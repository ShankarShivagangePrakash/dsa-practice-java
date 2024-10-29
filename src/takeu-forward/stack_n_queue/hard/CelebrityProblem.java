package stack_n_queue.hard;

/*
Problem:
    https://leetcode.com/problems/find-the-celebrity/description/
 */
public class CelebrityProblem {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n*n) + O(n)
            (n*n) - for matrix traversal
            (n) for array traversal after matrix traversal

        Space Complexity: O(2n)
            we are using two arrays.
     */
    public static int celebrityProblemBruteforce(int[][] arr){
        int n = arr.length;
        int[] knowMe = new int[n];
        int[] iKnow = new int[n];

        for(int i = 0; i < n;i++){
            for(int j = 0; j < n; j++){
                if(arr[i][j] == 1){
                    knowMe[j]++;
                    iKnow[i]++;
                }
            }
        }

        for(int i = 0; i < n; i++){
            if(knowMe[i] == n-1 && iKnow[i] == 0)
                return i;
        }
        return -1;
    }

    /*
    Approach:
        Explained in detail, check

        Time Complexity: O(2n)
            O(n) for while loop
            O(n) for for loop

        Space Complexity: O(1)
            no extra space
     */
    public static int celebrityProblemOptimal(int[][] arr){

        int n = arr.length;
        // top points to first row and bottom points to last row.
        int top = 0, bottom = n-1;

        while(top < bottom){
            // top knows bottom, top cannot be a celebrity, so increment top and let's check will next element can be a celebrity.
            if(arr[top][bottom] == 1)
                top++;
            // bottom knows top, so bottom cannot be a celebrity. So decrement bottom pointer and check is its previous row is celebrity
            else if(arr[bottom][top] == 1)
                bottom--;
            // both of them know each other, so both cannot be celebrity.
            else{
                top++;
                bottom--;
            }
        }

        // if top is greater than bottom means, no celebrities are found return -1
        if(top > bottom)
            return -1;

        for(int i = 0; i < n; i++){
            // diagonal element, no need to check
            if(i == top)
                continue;

            // top should not know i and i should know top. Else it cannot be a celebrity.
            if(arr[top][i] == 0 && arr[i][top] == 1)
               continue;
            else
                return -1;
        }
        return top;
    }
}
