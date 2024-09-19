package linkedlist.singly.hard;

import linkedlist.singly.easy.Node;

/*
Problem:
    https://takeuforward.org/data-structure/reverse-linked-list-in-groups-of-size-k/
    https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 */
public class LinkedListReverseNodesInKGroups {

    /*
    Approach:
        Approach is simple, we keep forming small linked list of size k, we reverse it
        we link each of them

        Steps to follow:
            temp = head;
            start traversing,
            get kthNode from temp - this will act as a small group of linked list of size k

                Now we will reverse this small k sized linked list
                to reverse this linked list, we store the kthNode.next reference in a pointer `nextNode`
                and we make kthNode.next = null
                so reverse() method will treat it as a individual linked list and can reverse it

                the reversed linked list can be of two types
                    - first k elements of the original linked list
                        in this case head has to be updated
                        to check for this condition, we simply do is temp == head
                        if so we set head = newHead
                        where newhead is the head of the reversed linked list of k sized group
                        idea behind this is, if temp == head means it is the first k elements group
                        since we have reversed this group, we have to update head to head of the reversed group
                    - group somewhere else,
                        in this case previous groups last node has to be linked to newhead of the current reversed linked list
                        we keep track of previous groups last node using `previousGroupLastNode`
                        if that is not null, then we make previousGroupLastNode.next = newHead
                        newhead is the head of the current group reversed linked list
                once this assignment is done

                we have to move previousGroupLastNode to current group last node
                    that can be done using previousGroupLastNode = temp
                    since temp was the first element of the current group before reversal
                    after reversal it will become current groups last element

                similarly we move temp to next group first element
                using nextNode, temp = nextNode

                One edge case you need to consider
                now at a given point, there are nodes left in the linked list
                but not equal to k
                in that case we have to link those to last element of the previous linked list group so linked list will be complete
                to do that just do `previousGroupLastNode`.next = temp
                and break

        Time Complexity: O(2n)
            Reason: ignore the main while loop inside linkedListReverseNodesInKGroups()
                    actual logic is inside finding the kthNode
                    reversing it and relinking
                    relinking takes O(1)
                    let's analyse reversing and finding kthNode
                    kthNode will take O(K), but we will form x groups covering all elements of the linked list
                    which is x * O(k) - which is almost same as O(n)
                        in other words we are forming x groups each having k elements by traversing linked list

                    Other operation is reversal,
                        same here to reverse k elements it takes O(K), but x groups
                        combining all it will take O(n) - same logic as above

         Space Complexity: O(1)
     */
    public static Node linkedListReverseNodesInKGroups(Node head, int k){

        Node temp = head;
        // to keep track of previous group last node
        Node previousGroupLastNode = null;
        while(temp != null){
            Node kthNode = getKthNode(temp, k);
            // If kth node is null means, there are no enough nodes to form group of k starting from temp
            // simply link temp to previous group last node and break, because we cannot form group of k nodes further
            if(kthNode == null){
                if(previousGroupLastNode != null)
                    previousGroupLastNode.next = temp;
                break;
            }
            else{
                // we store the first element of the next group
                Node nextNode = kthNode.next;
                // we reset kthNode.next = null, so that current group can be treated as a inidividual linked list
                // so that we can reverse it
                kthNode.next = null;
                // reverse the individual linked list, which returns newHead of this reversed linked list and it is stored in `newHead`
                Node newHead = reverse(temp);

                // If temp == head means, this is the first group
                // since first group is reversed, even we need to update head of the original linked list.
                if(temp == head)
                    head = newHead;
                else {
                    // else means, we have to link previous group last node with the current group reversed linked list head.
                    previousGroupLastNode.next = newHead;
                }

                // move previous group last node to current group last node
                previousGroupLastNode = temp;
                // move temp to next group first node
                temp = nextNode;
            }
        }
        return head;
    }

    public static Node getKthNode(Node temp, int k){
        k--;
        while(temp != null && k > 0)
            temp = temp.next;
        return temp;
    }

    public static Node reverse(Node head){
        if(head == null || head.next == null)
            return head;
        Node newHead = reverse(head.next);
        Node after = head.next;
        after.next = head;
        head.next = null;
        return newHead;
    }
}
