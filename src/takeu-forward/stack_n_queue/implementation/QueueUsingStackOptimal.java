package stack_n_queue.implementation;

import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/implement-queue-using-stack/
    https://leetcode.com/problems/implement-queue-using-stacks/description/
 */
public class QueueUsingStackOptimal {

    private Stack<Integer> s1;
    private Stack<Integer> s2;


    QueueUsingStackOptimal(){
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /* O(1)*/
    public void push(int x){
        s1.add(x);
    }

    // can range from O(1) to O(n)
    public int pop(){
        if(!s2.isEmpty())
            return s2.pop();

        while(!s1.isEmpty()){
            s2.add(s1.pop());
        }
        return s2.pop();
    }

    // can range from O(1) to O(n)
    public int top(){
        if(!s2.isEmpty())
            return s2.peek();

        while(!s1.isEmpty()){
            s2.add(s1.pop());
        }
        return s2.peek();
    }

    public int size(){
        return s1.size() + s2.size();
    }

    public boolean isEmpty(){
        return (s1.isEmpty() && s2.isEmpty());
    }

    public static void main(String[] args) {
        QueueUsingStackOptimal queueUsingStackOptimal = new QueueUsingStackOptimal();
        queueUsingStackOptimal.push(3);
        queueUsingStackOptimal.push(4);
        System.out.println("The element poped is " + queueUsingStackOptimal.pop());
        queueUsingStackOptimal.push(5);
        System.out.println("The top element is " + queueUsingStackOptimal.top());
        System.out.println("The size of the queue is " + queueUsingStackOptimal.size());
        System.out.println("Is Stack empty? " + queueUsingStackOptimal.isEmpty());
    }
}
