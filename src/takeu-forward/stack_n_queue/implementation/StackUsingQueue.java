package stack_n_queue.implementation;

import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/data-structure/implement-stack-using-single-queue/
    https://leetcode.com/problems/implement-stack-using-queues/
 */
public class StackUsingQueue {

    private Queue<Integer> queue;

    // constructor which instantiates queue.
    StackUsingQueue(){
        queue = new LinkedList<>();
    }

    /* add element to the stack
     now take the remaining elements in the stack
     pop them and push it back to the stack
     when you do this it will maintain the stack order
     and the top will point to latest element inserted
     O(n)

     For example: Assume you want to insert 1
        initially stack is empty you insert 1 and for loop doesn't execute because there are no elements in stack except 1

        Now you want to insert 2
        you insert 2 and run for loop for remaining elements in the stack
        you pop reamining element 1 and put it after 2
        in queue top will point to front of the queue, so top will point to 2

        Now you want to insert 3
        insert 3 and run for loop for remaining elements 1 & 2
        currently stack order is 2 1 3
        you pop 2 first and put it at end of the stack ---> 1 3 2
        now pop 1 and put it at end of the stack ----> 3 2 1

        don't do this operation for 3 because it is newly inserted element not part of existing stack
        now stack looks like 3 2 1
        top will point to 3
            which is the latest element inserted.
    */
    public void push(int x){
        queue.add(x);

        for(int i = 0; i < queue.size() -1; i++)
            queue.add(queue.remove());
    }

    /* queue.remove() will remove latest element inserted
     after push() operation alteration.
     O(1)*/
    public int pop(){
        return queue.remove();
    }

    /* peek() or top() both are same
     in stack top() returns the latest element inserted
     whereas in queue, it returns the first element inserted.
     now since we are implementing stack, we have to return latest element inserted
     push operation will point top to latest element inserted.
     O(1) */
    public int top(){
        return queue.peek();
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return (queue.size() == 0);
    }

    public static void main(String[] args){
        StackUsingQueue stackUsingQueue = new StackUsingQueue();
        stackUsingQueue.push(3);
        stackUsingQueue.push(2);
        stackUsingQueue.push(4);
        stackUsingQueue.push(1);
        System.out.println("Top of the stack: " + stackUsingQueue.top());
        System.out.println("Size of the stack before removing element: " + stackUsingQueue.size());
        System.out.println("The deleted element is: " + stackUsingQueue.pop());
        System.out.println("Top of the stack after removing element: " + stackUsingQueue.top());
        System.out.println("Size of the stack after removing element: " + stackUsingQueue.size());
        System.out.println("Is Queue Empty? : " + stackUsingQueue.isEmpty());
    }
}
