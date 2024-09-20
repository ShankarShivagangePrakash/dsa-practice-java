package linkedlist.doubly.medium;


/*
Problem:
    https://leetcode.com/problems/design-browser-history/description/
 */
public class DesignBrowserHistory {

    Node current;

    // constructor - homepage loaded when browser started - O(1)
    DesignBrowserHistory(String homePage){
        current = new Node(homePage);
    }

    // visit specified website - O(1)
    public void visit(String url){
        Node newNode = new Node(url);
        current.next = newNode;
        newNode.prev = current;
        current = current.next;
    }

    /* move specified steps back from the history list
     if steps is greater than history list size, move as much as possible and return the vale means
     history list size is 3, but steps input is 5, maximum we can move 3 steps back,
     move 3 steps back and return the data available at that node
     O(steps)*/
    public String back(int steps){
        while(steps > 0){
            if(current.prev == null)
                break;
            current = current.prev;
            steps--;
        }
        return current.val;
    }

    /* move specified steps forward from the history list
     if steps is greater than history list size, move as much as possible and return the vale means
     history list size is 3, but steps input is 5, maximum we can move 3 steps forward,
     move 3 steps forward and return the data available at that node
     O(steps)*/
    public String forward(int steps){
        while(steps > 0){
            if(current.next == null)
                break;
            current = current.next;
            steps--;
        }
        return current.val;
    }

    // Inner class to represent website visited in a browser tab
    class Node{
        String val;
        Node next;
        Node prev;

        Node(String val){
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
}
