package stack_n_queue.implementation;

/*
Problem:
    https://takeuforward.org/data-structure/implement-queue-using-array/
    https://www.geeksforgeeks.org/problems/implement-queue-using-array/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=implement-queue-using-array
 */
public class QueueUsingArray {

    public static void main(String[] args) {
        Queue q = new Queue();
        q.push(4);
        q.push(14);
        q.push(24);
        q.push(34);
        System.out.println("The peek of the queue before deleting any element " + q.top());
        System.out.println("The size of the queue before deletion " + q.size());
        System.out.println("The first element to be deleted " + q.pop());
        System.out.println("The peek of the queue after deleting an element " + q.top());
        System.out.println("The size of the queue after deleting an element " + q.size());
    }
}

class Queue{
    int[] arr;
    int front;
    int rear;
    int currentSize;

    Queue(){
        arr = new int[5];
        front = -1;
        rear = -1;
        currentSize = 0;
    }

    public void push(int x){
        if(currentSize == arr.length) {
            System.out.println("Queue completely filled, cannot insert");
            return;
        }

        if(rear == -1){
            front = 0;
            rear = 0;
        }

        /* if rear has reached end of the array
         but there are empty spaces formed by pop() in the begining of the array means, you can insert elements at the begining of the array
         so we are doing modulus operation by array length*/
        else{
            rear = (rear + 1) % arr.length;
        }
        arr[rear] = x;
        currentSize++;
        System.out.println("The element pushed is " + x);

    }

    public int pop(){
        if(front == -1){
            System.out.println("Empty Queue");
            return -1;
        }

        int poppedElement = arr[front];
        // if there is only one element in the queue, then make front and rear -1 to indicate queue will be emptied
        if(currentSize == 1){
            front = -1;
            rear = -1;
        }
        // handle edge case when front reaches end of the array.
        else
            front = (front + 1) % arr.length;

        currentSize--;
        return poppedElement;
    }

    public int top(){
        if(front == -1)
            return -1;

        return arr[front];
    }

    public int size(){
        return currentSize;
    }
}
