package linkedlist.doubly.easy;

public class Node {

    public int val;
    public Node next;
    public Node prev;

    Node(){
        this.val = 0;
        next = null;
        prev = null;
    }

    Node(int val){
        this.val = val;
    }
}
