package linkedlist.singly.hard;

import java.util.ArrayList;
import java.util.Collections;

/*
Problem:
    https://takeuforward.org/data-structure/flattening-a-linked-list/
    https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=flattening-a-linked-list
 */
public class LinkedListFlatten {

    /*
    Approrach:
        we go through each vertical linked list
        we put all elements in an array list
        Then we sort that array list

        We create new flattened limked list from the array list
        we return the head of the flattened array list

        Time Complexity:
            Assume there are n horizontal nodes
            and for the sake of simplicity we assume that each veritical lists are of same size having m elements

            Then to prepare the array list, we have to visit each node in the entire linked list
                we have to visit n horizontal nodes and all m nodes in each of those columns
                so this step takes O(n * m)

            Next step is to sort the array list
                array list will have n * m elements - this can be treated as space complexity for this step
                let's take x = n * m
                then sorting requires x * log x
                replacing value for x
                 = O((n * m) log (n * m))

            Last step is to create new linked list
            we traverse through the sorted linked list and keep creating new node for evey element in the array list
            finally we return the linked list we have created
            so this step, we have to traverse n * m elements
            and also we have to newly created n * m number of nodes
            time complexity for this step is O(n * m)
            space complexity for this step is also O( n * m) - this space is taken to form new linked list

            Adding time complexity of all steps
                = O(n * m) + O((n*m) log(n*m)) + O(n * m)
                = O(2 * n * m) + O((n*m) log(n*m))

         Space Complexity: O(2 * n * m)
            = O(n * m) + O(n * m)
                one O(n * m) is to form array list
                another O(n * m) is to form linked list
     */
    public static Node linkedListFlattenBruteForce(Node head){
        ArrayList<Integer> arr = new ArrayList<>();

        Node temp = head;
        // visit each horizontal nodes - O(n * m)
        while(temp != null){
            Node child = temp;
            // vist each vertical node at that level and add it to array list
            while(child != null){
                arr.add(child.data);
                // move to next vertical node at that column
                child = child.bottom;
            }
            // once all vertical nodes are visited at current column, move to next column
            temp = temp.next;
        }

        // O((n*m) log(n*m))
        Collections.sort(arr);

        Node dummyNode = new Node(-1);
        Node dummyHead = dummyNode;

        // O(n*m)
        for(int i = 0; i < arr.size(); i++){
            Node newNode = new Node(arr.get(i));
            // since we need to form vertical flattened linked list
            // attach new nodes to .bottom and move dummyNode = dummyNode.bottom so that it will act as tail of this linked list
            dummyNode.bottom = newNode;
            dummyNode = dummyNode.bottom;
        }
        // actual linkedlist starts from dummyHead.bottom return it
        // we have to return bottom here because we have attached nodes to .bottom
        return dummyHead.bottom;
    }

    static class Node{
        int data;
        Node next;
        Node bottom;

        Node(int data){
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }

    /*
    Approach:
        We will solve this problem using recursion
        we know that we have to merge entire list, sort it and return

        What if we keep merging two columns recursively till we get one flattened linked list
        That's it we can solve the problem

        So the approach is to get only two vertical linked list
        we keep moving to next column untill it is the last column
        if it is the last column, since vertical linked lists are sorted we can return it as it is
        This is the base case

            Then we move to it's previous column, we merge that column and last column ( result will be sorted single vertical linked list)
            This single vertical linked list will be again merged with it's previous column which yiedls single merged sorted vertical linked list
            The same process is repeated by moving backwards (to previous columns)
            untill we get single sorted vertical flattened linked list

        Time Complexity: O( 2 * n * m)
            Reason:
                First let's assume that there are n horizontal nodes
                each column has m nodes, this is our assumption

                Let's understand, how much time mergeLinkedList() is taking
                    we visit each node in both the vertical linked list
                    so we can say M + M = 2M

                we are following this merging process for every column in the linked list
                how many columns are present - n
                so the total time complexity is
                    = n * 2m
                    = O( 2 * n * m)

         Space Complexity: O(n)
            this O(n) is recursive stack space used.
     */
    public static Node linkedListFlattenOptimal(Node head){

        if(head == null || head.next == null)
            return head;

        Node mergedHead = linkedListFlattenOptimal(head.next);
        return mergeLinkedList(head, mergedHead);
    }

    // O(2 * m)
    public static Node mergeLinkedList(Node list1, Node list2){
        Node dummyNode = new Node(-1);
        Node dummyHead = dummyNode;

        while(list1 != null && list2 != null){
            if(list1.data <= list2.data){
                dummyNode.bottom = list1;
                list1 = list1.bottom;
            }
            else{
                dummyNode.bottom = list2;
                list2 = list2.bottom;
            }
            dummyNode = dummyNode.bottom;
            dummyNode.next = null;
        }

        if(list1 != null){
            dummyNode.bottom = list1;
            dummyNode = dummyNode.bottom;
        }
        if(list2 != null){
            dummyNode.bottom = list2;
            dummyNode = dummyNode.bottom;
        }

        return dummyHead.bottom;
    }
}
