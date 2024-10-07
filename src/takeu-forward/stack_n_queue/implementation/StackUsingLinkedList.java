package stack_n_queue.implementation;
/*
Problem:
    https://takeuforward.org/data-structure/implement-stack-using-linked-list/
    https://www.geeksforgeeks.org/problems/implement-stack-using-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=implement-stack-using-linked-list
 */
public class StackUsingLinkedList {

    private StackNode top;

    // add element to the begining of the list
    public void push(int x){
        StackNode newNode = new StackNode(x);
        newNode.next = top;
        top = newNode;
    }

    // delete element from the begining of the list
    public int pop(){
        if(top == null)
            return -1;

        StackNode deletedNode = top;
        top = top.next;
        return deletedNode.data;
    }

    // return element on the begining of the list
    public int top(){
        if(top == null)
            return -1;
        return top.data;
    }

    // traverse entire linked list, count number of nodes and return it.
    public int size(){
        int length = 0;
        StackNode temp = top;
        while(temp != null){
            temp = temp.next;
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        StackUsingLinkedList s = new StackUsingLinkedList();
        s.push(10);
        s.push(20);
        System.out.println("Element popped " + s.pop());
        System.out.println("Stack size: " + s.size());
    }
}

class StackNode{
    int data;
    StackNode next;

    StackNode(int data){
        this.data = data;
        this.next = null;
    }
}
