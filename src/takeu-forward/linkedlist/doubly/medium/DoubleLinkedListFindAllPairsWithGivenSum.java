package linkedlist.doubly.medium;

import linkedlist.doubly.easy.Node;

import java.util.ArrayList;

/*
Problem:
    https://www.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find-pairs-with-given-sum-in-doubly-linked-list
 */
public class DoubleLinkedListFindAllPairsWithGivenSum {

    /*
    Approach:
        Same as `TwoSum.java` but here input is linked list instead of arrays

        Since linked list is sorted, we can use two pointer approach
        left will point to first node in the linked list and right will point to last node of the linked list
            we calculate sum = left.val + right.val
            sum can have three possiblities
                - sum == target:
                    in this case add the pair to result
                    move left pointer as left = left.next
                    move right pointer as right = right.prev
                - sum < target:
                    we have to move towards right, so left = left.next
                - sum > target:
                    we have to move towards left, so right = right.prev;

            return result

        Time Complexity: O(2n)
            one O(n) for finding right initally
            another O(n) for main while loop traversal

        Space Complexity: O(1)
     */
    public static ArrayList<ArrayList<Integer>> doubleLinkedListFindAllPairsWithGivenSum(int target, Node head){

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(head == null || head.next == null)
            return result;

        Node left = head;
        Node right = findRight(head); // O(n)

        // O(n)
        while(left.val < right.val){
            int sum = left.val + right.val;

            if(sum == target){
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(left.val);
                pair.add(right.val);

                left = left.next;
                right = right.prev;
            }
            else if(sum < target)
                left = left.next;
            else
                right = right.prev;
        }
        return result;
    }

    // O(n)
    public static Node findRight(Node head){
        Node temp = head;
        while(temp.next != null)
            temp = temp.next;
        return temp;
    }
}
