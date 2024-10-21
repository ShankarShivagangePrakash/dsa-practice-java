package stack_n_queue.implementation;

import java.util.Stack;
/*
Problem:
    https://takeuforward.org/data-structure/implement-min-stack-o2n-and-on-space-complexity/
    https://leetcode.com/problems/min-stack/
 */
public class MinStackBruteForce {

    Stack<Pair> stack;

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(1) - for all operations
        Space Complexity: O(2n) - we insert pairs, so we are additionally inserting one more element in each push()
        so extra O(n)
        another O(n) is for adding elements.
     */
    MinStackBruteForce(){
        stack = new Stack<>();
    }

    public void push(int val){
        if(stack.isEmpty()){
            stack.push(new Pair(val, val));
        }
        else{
            stack.push(new Pair(val, Math.min(val, stack.peek().y)));
        }
    }

    public void pop(){
       stack.pop();

    }

    public int top(){
        return stack.peek().x;

    }

    public int getMin(){
        return stack.peek().y;
    }

}

class Pair{
    int x;
    int y;

    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
