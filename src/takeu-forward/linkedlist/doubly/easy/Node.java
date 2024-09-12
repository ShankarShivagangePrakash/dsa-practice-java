package linkedlist.doubly.easy;

public class Node {

    int val;
    Node next;
    Node prev;

    Node(){
        this.val = 0;
        next = null;
        prev = null;
    }

    Node(int val){
        this.val = val;
    }
}
