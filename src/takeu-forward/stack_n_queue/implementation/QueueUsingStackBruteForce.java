package stack_n_queue.implementation;

import java.util.Stack;

/*
Problem:
    https://takeuforward.org/data-structure/implement-queue-using-stack/
    https://leetcode.com/problems/implement-queue-using-stacks/description/
 */
public class QueueUsingStackBruteForce {

    private Stack<Integer> s1;
    private Stack<Integer> s2;


    QueueUsingStackBruteForce(){
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /* O(2n)
     we are transferring all elements from s1 to s2
     add new element to s1
     transfer back all elements from s2 to s1*/
    public void push(int x){
        while(s1.size() != 0){
            s2.add(s1.pop());
        }

        s1.add(x);

        while(!s2.isEmpty()){
            s1.add(s2.pop());
        }
    }

    public int pop(){
        return s1.pop();
    }

    public int top(){
        return s1.peek();
    }

    public int size(){
        return s1.size();
    }

    public boolean isEmpty(){
        return (s1.size() == 0);
    }

    public static void main(String[] args) {
        QueueUsingStackBruteForce queueUsingStackBruteForce = new QueueUsingStackBruteForce();
        queueUsingStackBruteForce.push(3);
        queueUsingStackBruteForce.push(4);
        System.out.println("The element poped is " + queueUsingStackBruteForce.pop());
        queueUsingStackBruteForce.push(5);
        System.out.println("The top element is " + queueUsingStackBruteForce.top());
        System.out.println("The size of the queue is " + queueUsingStackBruteForce.size());
        System.out.println("Is Stack empty? " + queueUsingStackBruteForce.isEmpty());
    }
}
