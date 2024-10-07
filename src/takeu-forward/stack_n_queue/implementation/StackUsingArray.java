package stack_n_queue.implementation;

/*
Problem:
    https://takeuforward.org/data-structure/implement-stack-using-array/
    https://www.geeksforgeeks.org/problems/implement-stack-using-array/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=implement-stack-using-array
 */
public class StackUsingArray {

    public static void main(String[] args){
        Stack s = new Stack();
        s.push(6);
        s.push(3);
        s.push(7);
        System.out.println("Top of the stack before deleting any element " + s.top());
        System.out.println("Size of the stack before deleting any element " + s.size());
        System.out.println("The element deleted is " + s.pop());
        System.out.println("Size of the stack after deleting an element " + s.size());
        System.out.println("Top of the stack after deleting an element " + s.top());
    }
}

class Stack{

    int arr[];
    int top;

    Stack() {
        arr = new int[1000];
        top = -1;
    }

    public void push(int x){
        arr[++top] = x;
    }

    public int pop(){
        if(top == -1)
            return -1;
        return arr[top--];
    }

    public int top(){
        if(top == -1)
            return -1;
        return arr[top];
    }

    public int size(){
        return top + 1;
    }
}
