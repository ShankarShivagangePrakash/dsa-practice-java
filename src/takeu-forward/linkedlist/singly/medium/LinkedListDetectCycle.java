package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

import java.util.HashSet;

/*
Problem:
    https://takeuforward.org/data-structure/detect-a-cycle-in-a-linked-list/
    https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListDetectCycle {

    /*
    Approach:
        Extreme brute force solution is to use a data structure which stores the data and values can be retrieved in O(1) - any element stored in data structure.
        Such data structures are Set and Map
            We will use set, because for map we have to pass key and value - we can use Node itself as key, but what to use for value
            But for Set, we can use Node itself as the data.

        Steps are as follows:
            I will create a HashSet which stores data of Node type.
                We know that insertion and deletion to hashSet takes O(1)
            we iterate through the linked list
            for every node, i will check is node.next available in the set.
                If so, it means there is a loop return false
                else, add current node to set.
                Move to next element in the linked list

            If you have traversed the entire list means, there is no cycle
            return false

        Time Complexity: O(n)
        Space Complexity: O(n)
     */
    public static boolean detectCycleBruteForce(Node head){
        HashSet<Node> set = new HashSet<>();

        Node temp = head;
        while(temp != null){

            // check for loop.
            if(set.contains(temp.next))
                return true;
            else
                set.add(temp);

            temp = temp.next;
        }
        return false;
    }

    /*
    Approach:
        Step 1: Initialise two pointers, `slow` and `fast`, to the head of the linked list.
        `slow` will advance one step at a time, while `fast` will advance two steps at a time. These pointers will move simultaneously.

        Step 2: Traverse the linked list with the `slow` and `fast` pointers.
        While traversing, repeatedly move `slow` one step and `fast` two steps at a time.

        Step 3: Continue this traversal until one of the following conditions is met:

            `fast` or `fast.next` reaches the end of the linked list (i.e., becomes null).
            In this case, there is no loop in the linked list ie. the linked list is linear, and the algorithm terminates by returning false.

            `fast` and `slow` pointers meet at the same node. This indicates the presence of a loop in the linked list,
            and the algorithm terminates by returning `true`.

       Time Complexity: O(N),
           where N is the number of nodes in the linked list.
           This is because in the worst-case scenario, the fast pointer, which moves quicker,
           will either reach the end of the list (in case of no loop) or meet the slow pointer (in case of a loop)
           in a linear time relative to the length of the list.

            The key insight into why this is O(N) and not something slower is that each step of the algorithm
            reduces the distance between the fast and slow pointers (when they are in the loop) by one.
            Therefore, the maximum number of steps needed for them to meet is proportional to the number of nodes in the list.

       Space Complexity: O(1)
     */
    public static boolean detectCycleOptimal(Node head){
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next == null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                return true;
        }
        return false;
    }
}
