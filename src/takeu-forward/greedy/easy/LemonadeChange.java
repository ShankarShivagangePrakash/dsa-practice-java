package greedy.easy;

/*
Problem:
    https://takeuforward.org/Greedy/lemonade-change
    https://leetcode.com/problems/lemonade-change/description/
 */
public class LemonadeChange {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            array traversal
        Space Complexity: O(1)
     */
    public static boolean lemonadeChangeOptimal(int[] bills){
        int five = 0, ten = 0;

        for(int i = 0; i < bills.length; i++){
            // you have recieved 5$, no need to return any change, just increment the 5$ notes count you have.
            if(bills[i] == 5)
                five++;

            // you have recieved 10$, you have to return 5$ change.
            else if(bills[i] == 10){
                ten++;
                // check - if 5$ notes count is greater than 0, if so decrement `five` count as you are returning change.
                if(five > 0)
                    five--;
                // else means, you cannot return change return false
                else
                    return false;
            }
            else{
                // else means, we have recieved 20$ note, we have to return change.
                // check can you return 1 - 10$ and 1- 5$ note
                if(five > 0 && ten > 0){
                    five--;
                    ten--;
                }
                // check can you return 3 - 5$ notes
                else if(five >= 3)
                    five = five - 3;
                // if both the cases are not satisfied you cannot return change, return false
                else
                    return false;
            }
        }
        // if you have completed array traversal, it means you have given change for all customers return true.
        return true;
    }
}
