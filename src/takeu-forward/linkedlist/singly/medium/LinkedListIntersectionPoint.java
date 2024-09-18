package linkedlist.singly.medium;

import linkedlist.singly.easy.Node;

import java.util.HashSet;

/*
Problem:
    https://takeuforward.org/data-structure/find-intersection-of-two-linked-lists/
    https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 */
public class LinkedListIntersectionPoint {

    /*
    Approach:
        Brute force approach is use an additional data structure

        Steps are as follows:
            Create a hash set
            Traverse first linked list, put all nodes in the hash set

            Now traverse second linked list,
            while traversing check is current node present in the hash set, if so that is the intersection point return
            else move to next node in the second linked list

            if you have completely traversed second linked list means, there is no common node between both
            return null

        Time Complexity: O(n1) + O(n2)
        Space Complexity: O(n1)
            where n1 = size of the first linked list
                  n2 = size of the second linked list
     */
    public static Node linkedListIntersectionPointBruteForce(Node headA, Node headB){
        HashSet<Node> set = new HashSet<>();

        Node temp = headA;
        while(temp != null){
            set.add(temp);
            temp = temp.next;
        }

        temp = headB;
        while(temp != null){
            if(set.contains(temp))
                return temp;
            temp = temp.next;
        }
        return null;
    }

    /*
    Approach:
        Input linked list can be of three types
            - both the linked list are of same size
            - linked list 1 size is greater than two
            - linked list 2 size is greater than one

       To solve the problem, when the size of both the linked list is equal then it is very simple
            traverse both the linked list simultaneously, compare whether both the nodes are same, if so
                that is the intersection point, return
            if you have completely traversed linked lists means, there is no intersection return null

       Can we extend this approach to remaning two input types
       Answer is yes,
        assume size of linked list one is 5 and linked list two size is 7
            then we start the linked list1 traversal from its first element
            but for second linked list, we start traversing from 3rd element
            then in both the linked list, i have to traverse for exacly same number of elements i.e 5 noes traversal
            while traversing in the same way, if we find temp1 == temp2 return it

       Steps are as follows:
            Travese linked list one and two and compute their lengths;

            if n1 < n2:
                then in linked list two, we have to move d steps forward before starting combined traversal
                    d = n2-n1
            else
                then in linked list 1, we have to move d steps forward before starting combined traversal
                    d = n1-n2

        Time Complexity: O(n1) + O(n2) + O(n2-n1) + O(n1)
        Space Complexity: O(1)
     */
    public static Node linkedListIntersectionPointBetterApproach(Node headA, Node headB){

        int n1 = 0, n2 = 0;

        Node temp = headA;
        while(temp != null){
            n1++;
            temp = temp.next;
        }

        temp = headB;
        while(temp != null){
            n2++;
            temp = temp.next;
        }

        if(n1 < n2)
            return collisionFound(headA, headB, n2-n1);
        else
            return collisionFound(headB, headA, n1-n2);
    }

    /*
    We first move headB pointed linkedList by d positions
    from that position, we start combined traversal
    if temp1 == temp2 occurs, we return that node as intersection point
    else return null
     */
    private static Node collisionFound(Node headA, Node headB, int d){
        Node temp1 = headA, temp2 = headB;

        while(d > 0){
            temp2 = temp2.next;
            d--;
        }

        while(temp1 != null && temp2 != null){
            if(temp1 == temp2)
                return temp1;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return null;
    }

    /*
    Approach:

        The difference of length method requires various steps to work on it.
        Using the same concept of difference of length, a different approach can be implemented. The process is as follows:-

        Take two dummy nodes for each list. Point each to the head of the lists.
        Iterate over them. If anyone becomes null, point them to the head of the opposite lists and continue iterating until they collide.

        summary is if we align temp1 and temp2 on the same vertical positions, then we can compare for intersection
        to do that we follow below strategy
            first temp1 and temp2 will point to headA and headB respectively

            untill temp1 != temp2
               is satisfied, we move temp1 and temp2 by one step ahead
               if temp1 becomes null (reached end of the linked list1) we set it to headB
               if temp2 becomes null (reached end of the linked list2) we set it to headA

               we repeat the same process
               this way we align temp1 and temp2 vertically, then we search for intersection, if found we return it

           Possible scenario's:
                linked list A and B intersection is the first node itself.
                linked list A and B are of same size and intersection is somewhere in the middle
                linked list A and B are of different size and intersection is somewhere in the middle
                linked list A and B intersection is not present

                first condition mentioned above helps us to find the while stopping condition
                    we can say keep running while loop till temp1 != temp2
                        since temp1 = headA and temp2 = headB
                            and headA = headB
                            so temp1 = temp2, so it doesn't enter while loop, we execute the statement after while loop - return temp1

                second condition:
                    we keep moving both temp1 and temp2 by one step
                    before temp1 or temp2 becomes null, we find intersection point and we return the result

                Third condition:
                    assume linked list 1 size is smaller than the linked list.
                        linked lis 1 will reach null before linked list 2. so in this case temp1 will be set as headB
                    in sometime, even temp2 will become null and we set it to headA
                    by the time temp2 set to headA, temp1 will have moved by the distance `d`, where d is the difference in length between linked list 1 and 2
                    so again they both are verically aligned, becomes second condition
                    once intersection is found return it.

                Fourth condition:
                    assume, there is no intersection
                    whether both linked list are of same size or not doesn't matter
                    we keep running the while loop, till temp1 = temp2
                    but when this will happen in fourth case
                    when both temp1 and temp2 have traversed and have become null
                        so inner if condition if(temp1 == temp2)
                            will holds good and return temp1 value which will be null

          Time Complexity: O(n1 + n2)
            Reason: each temp1 and temp2 both will traverse entire linked list 1 and 2(simultaneously)
                    so  O(n1 + n2)
          Space Complexity: O(1)
     */
    public static Node linkedListIntersectionPointOptimal(Node headA, Node headB){

        Node temp1 = headA, temp2 = headB;

        while(temp1 != temp2){
            temp1 = temp1.next;
            temp2 = temp2.next;

            if(temp1 == temp2)
                return temp1;

            if(temp1 == null) temp1 = headB;
            if(temp2 == null) temp2 = headA;
        }
        return temp1;
    }
}
