package linkedlist.doubly.easy;

/*
Problem:
    https://takeuforward.org/linked-list/introduction-to-doubly-linked-list
    https://www.geeksforgeeks.org/problems/introduction-to-doubly-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=introduction-to-doubly-linked-list
 */
public class DoubleLinkedListCreation{

    /*
    Approach:
        First assign first element of the array as the head
        Also we need to link the current element to new node we will add - so keep track of it using `previous`

        since array[0] has already been added as head of the linked list
        start traversing from array[1] - create new node for every array element
        link it with previous node, since it is doubly linked list you have to link from both the ways
            - link from previous node to new node
            - link from new node to previous node
        once it's done mark new node as `previous`, move to next array element, for that this new node will act as previous node.

    Time Complexity: O(n)
    Space Complexity: O(n) - to return answer, not for solving the problem.
     */
    public static Node constructDLL(int arr[]) {

        if(arr.length == 0)
            return null;

        Node head = new Node(arr[0]);
        Node previous = head;

        for(int i = 1; i < arr.length; i++){
            Node newNode = new Node(arr[i]);

            previous.next = newNode;
            newNode.prev = previous;

            previous = newNode;
        }
        return head;
    }

    public static void printList(Node head){
        Node temp = head;
        while(temp != null){
            System.out.printf("---> %d ", temp.val);
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        Node head = constructDLL(new int[]{1, 2, 3, 4, 5});
        printList(head);
    }
}