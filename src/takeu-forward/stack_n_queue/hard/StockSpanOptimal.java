package stack_n_queue.hard;

import java.util.Stack;

/*
Problem:
    https://leetcode.com/problems/online-stock-span/description/
 */
public class StockSpanOptimal {

    Stack<Pair> stack;
    // we are not having any array, but we need to track of number of inputs so far, so use this variable.
    int ind;

    StockSpanOptimal(){
        stack = new Stack<>();
        ind = -1;
    }

    /*
    Approach:
        Explained in notes,

        It is simple, to find number of consecutive days for which stock price was lesser than today
        can be found using previous greater element

        Time Complexity:
            we cannot give time complexity for individual calls
            but for all invokations in total O(2n)
        Space Complexity:
            we cannot give time complexity for individual calls
            but for all invokations in total O(n)
     */
    public int next(int price){
        // increment ind to indicate one more new input
        ind = ind + 1;

        // pop all elements from stack, whose price is lesser than current price
        // this is because we need to find previous greater element
        while(!stack.isEmpty() && stack.peek().val <= price)
            stack.pop();

        // once we have found pge element, compute answer.
        int ans = ind - (stack.isEmpty() ? -1 : stack.peek().ind);

        // after finding answer, add current element to stack
        stack.push(new Pair(price, ind));

        return ans;
    }

}

class Pair{
    int val;
    int ind;

    Pair(int val, int ind){
        this.val = val;
        this.ind = ind;
    }
}
