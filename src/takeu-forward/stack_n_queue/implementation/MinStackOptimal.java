package stack_n_queue.implementation;

import java.util.Stack;

public class MinStackOptimal {

    Stack<Integer> stack;
    int mini;

    MinStackOptimal(){
        stack = new Stack<>();
        mini = -1;
    }

    /*
        If stack is empty we set the insertion element as minimum and push into stack
        else
            if the `val` is greater than `mini` no need to update minimum in the stack,
            so we simply insert val to stack and do not update `mini`

            else
                means `val` is less than `mini`, then we will set `val` as the `mini` - minimum in the stack
                and to stack we insert a modified value
                i.e (2.val - mini)

        O(1)
     */
    public void push(int val){
        if(stack.isEmpty()){
            stack.push(val);
            mini = val;
        }
        else{
            if(val < mini) {
                stack.push(2 * val - mini);
                mini = val;
            }
            else
                stack.push(val);
        }
    }

    /*
    If stack is empty, then don't return anything

    Else, pop the element
        if the popped element is lesser than the `mini` then we need to update minimum in the stack

        we have to update `mini` with the previous minimum
        to get that use = 2 * mini - val

        here `val` is the popped element from the stack

    O(1)
     */
    public void pop(){

        if(stack.isEmpty())
            return;

        int val = stack.pop();

        if(val < mini){
            mini = 2 * mini - val;
        }
    }

    /*
        While insertion, if the element to be inserted is greater than `mini`
        we were not updating `mini`
        in other case we were modifying `mini`

        so top can be either the peek element in the stack or `mini`
        so we have to check, is the stack.peek() is greater than `mini`
            if so, we can return peek element of the stack as top

            else,
                mini will be having top element value, return it

        O(1)
     */
    public int top(){
        if(stack.isEmpty())
            return -1;

        if(stack.peek() < mini)
            return mini;
        return stack.peek();
    }

    public int getMin(){
        return mini;
    }
}
