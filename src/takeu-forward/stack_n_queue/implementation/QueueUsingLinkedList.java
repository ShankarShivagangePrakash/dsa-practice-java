package stack_n_queue.implementation;

/*
Problem:
    https://takeuforward.org/data-structure/implement-stack-using-linked-list/
    https://www.geeksforgeeks.org/problems/implement-queue-using-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=implement-queue-using-linked-list
 */
public class QueueUsingLinkedList {

    // front will point to head of the linked list
    QueueNode front;
    // rear will point to tail of the linked list
    QueueNode rear;

    // we have to add element at the end of the linked list, so we add element to rear.
    public void push(int x){
        QueueNode newNode = new QueueNode(x);

        // if linked list is empty it means queue is also empty
        // when we add element in such case, we have to point both front and rear to new element added.
        if(rear == null){
            rear = newNode;
            front = newNode;
        }
        else{
            // link new element to current last element
            // and make this new element as new last element.
            rear.next = newNode;
            rear = rear.next;
        }
    }

    // delete element from begining of the linked list.
    public int pop(){
        // if front is null means, linked list (queue) is empty
        if(front == null)
            return -1;

        // get the head of the linked list.
        QueueNode deletedNode = front;
        // now move to next element in the linked list
        front = front.next;

        // if there was only one element in the linked list, then after front = front.next
        // now front will be null (means queue is empty now), in that case we have to make sure even rear is pointing to null
        if(front == null)
            rear = null;

        return deletedNode.data;
    }

    // return element at the begining of the linked list
    public int peek(){
        if(front == null)
            return -1;
        return front.data;
    }

    // return is `front` null or not?
    public boolean isEmpty(){
        return front == null;
    }

    // traverse through linked list and return count of nodes
    public int size(){
        int length = 0;
        QueueNode temp = front;
        while(temp != null){
            temp = temp.next;
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        QueueUsingLinkedList queue =new QueueUsingLinkedList();
        queue.push(10);
        queue.push(20);
        queue.push(30);
        queue.push(40);
        queue.push(50);
        System.out.println("Popped element from the queue is " + queue.pop());
        System.out.println("The size of the Queue is "+queue.size());
        System.out.println("The Peek element of the Queue is "+queue.peek());
    }
}

class QueueNode{
    int data;
    QueueNode next;

    QueueNode(int data){
        this.data = data;
        this.next = null;
    }
}
