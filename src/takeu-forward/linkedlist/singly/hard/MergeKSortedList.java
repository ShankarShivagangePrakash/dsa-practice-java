package linkedlist.singly.hard;

import linkedlist.singly.easy.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/*
Problem:
    https://leetcode.com/problems/merge-k-sorted-lists/description/
 */
public class MergeKSortedList {

    /*
    Approach:
        In brute force approach, we traverse every list
        we put all elements to an array list
        we sort the array list
        we create new linked list from the array list and return its head

        Time complexity:
            Assume there are k sorted list each of length n
               To form an array list, we have to iterate every element in all lists which takes O( k * n)

            Sorting takes x log x
                where x = k * n
                so (k * n) log( k * n)

            Again forming linked list takes
                O( k * n)

            Combining all O( k * n) + (k * n) og( k * n) + O( k * n)

        Space Complexity: O(2 * k * n)
            one O( k * n) for array list formation
            another O( k * n) for linked list formation from array list
     */
    public static Node linkedListMergekSortedListBruteForce(Node[] lists){
        if(lists.length == 0)
            return null;

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < lists.length; i++){
            Node temp = lists[i];
            while(temp != null){
                arr.add(temp.val);
                temp = temp.next;
            }
        }

        Collections.sort(arr);

        Node dummyNode = new Node(-1);
        Node dummyHead = dummyNode;
        for(int i = 0; i < arr.size(); i++){
            Node newNode = new Node(arr.get(i));
            dummyNode.next = newNode;
            dummyNode = dummyNode.next;
        }
        return dummyHead.next;
    }

    /*
    Approach:
        Similar to `LinkedListFlatten.java` optimal solution
        we consider first row as the head
        then we keep merging next rows with it, but while merging we maintain sorting order

        finally when we finish all rows, we will have one single linked list which is sorted

        Time Complexity:
            Assume there are k sorted list, each list has n elements
            initally you merge first and second row means you are merging n + n elements
            then you merge this list with third row means 2n + n = 3n
            ...

            you merge kth row which has n elements means = kn

            total time for merge = n + 2n + 3n + ...kn

            but also we are running a loop from first row to last row
            that is k
            total time = k * (n + 2n + 3n + ...kn)
                       = k * n * (1 + 2 + 3 ....+ K)
                       = k * n * (k * K+1)/2
                       almost equivalent to k^3

         Space Complexity: O(1)
     */
    public static Node linkedListMergeKSortedListBetterApproach(Node[] lists){
        if(lists.length == 0)
            return null;

        Node head = lists[0];
        for(int i = 1; i < lists.length; i++){
            head = mergeSortedList(head, lists[i]);
        }
        return head;
    }

    public static Node mergeSortedList(Node list1, Node list2){
        Node dummyNode = new Node(-1);
        Node dummyHead = dummyNode;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                dummyNode.next = list1;
                list1 = list1.next;
            } else{
                dummyNode.next = list2;
                list2 = list2.next;
            }
            dummyNode = dummyNode.next;
        }

        if(list1 != null){
            dummyNode.next = list1;
            dummyNode = dummyNode.next;
        }
        if(list2 != null){
            dummyNode.next = list2;
            list2 = list2.next;
        }
        return dummyHead.next;
    }

    /*
    Approach:
        We use min heap to further reduce the time and space Complexity
        We know that min heap will return top which is the smallest in the entire heap
        and insertion, deletion time complexity is log k
            where k is the length of the linked list

        First we create a Pair class, that will store two data
            one node value, second parameter actual node itself

        We will create a priority Queue of pair type
        check pair inner class and its compareTo()

        First we add the head of each sorted k lists to the priotiry Queue

        create dummyNode which acts as a tail to merged Sorted list
        create another pointer dummyHead which points to head of merged sorted list

        Then follow below steps untill priority Queue is not empty
            take the top element of the priority Queue
            since it is min heap, every time we get top element it will be the smallest element
             link this popped node to dummyNode.next
             move dummyNode to dummyNode.next - so it will be at tail of the sorted list

             if the popped node .next is not null
                means there is a node after popped node in the original list
                then add that to the priority Queue
                upon insertion again min heap will alter and top element will be the smalles

             same process repeats again, that node will be again added to the dummyNode.next and it moves
             if the popped node has next, again add it to the priority Queue

             repeat the same process till priority queue gets empty

         then return dummyHead.next

         Time complexity: K * log k + (n * k) log k
                Reason: Read the code and its comments, i have explained there

          Space Complexity: O(k)
                we are using priority queue at max at any point it will have k elements
     */
    public static Node linkedListMergeKSortedListOptimal(Node[] lists){
        if(lists == null || lists.length == 0)
            return null;

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        // First we add the head of each sorted k lists to the priotiry Queue
        // each insertion will take log (size of the heap) heap at max can have k elements
        // we are inserting all linked lists head, there are k lists
        // so k elements - each insertion will take log k
        // to insert all k heads O( k * log k)
        for(int i = 0; i < lists.length; i++){
            // do not add if some sorted list is empty
            if(lists[i] != null)
                pq.add(new Pair(lists[i].val, lists[i]));
        }

        Node dummyNode = new Node(-1);
        Node dummyHead = dummyNode;
        /*
        while loop runs till the priotiry Queue gets emptied
        but priority queue will have all elements of all the sorted list ( not together at once)
        Assume there are k lists and each list has n elements
        so we can say while loop will run for k * n times = O(k * n)
        but also you consider few more things
        inside the loop you are doing
            - priority queue.poll - gets priority queue top element which takes O(log k)
            - prority queue add - which takes O(log k)

        so total time complexity at this step is O(k * n * log k)
         */
        while(!pq.isEmpty()){
            Pair currentSmallestPair = pq.poll();
            Node currentSmallestNode = currentSmallestPair.node;

            dummyNode.next = currentSmallestNode;
            dummyNode = dummyNode.next;

            if(currentSmallestNode.next != null)
                pq.add(new Pair(currentSmallestNode.next.val, currentSmallestNode.next));
        }

        return dummyHead.next;
    }

    /* The reason this class implements Comparable interface is
     we are using this class as data type for Priority Queue
     when an element is inserted or removed, the min heap nature should be retained
     so upon add of Pair, we have to sort the priority Queue
     but sort on which parameter, because the data type we are storing is of Pair class
     so we implement Comparable interface, we implement comparTo()
     and say on which variable of this Pair objects, two Pair objects has to be compared*/
    static class Pair implements Comparable<Pair>{
        int val;
        Node node;

        Pair(int val, Node node){
            this.val = val;
            this.node = node;
        }

        // we are comparing node val, just like string comparision
        public int compareTo(Pair otherPair){
            return this.val - otherPair.val;
        }
    }
}
