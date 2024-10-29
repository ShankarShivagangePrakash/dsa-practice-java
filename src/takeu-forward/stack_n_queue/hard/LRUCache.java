package stack_n_queue.hard;

import java.util.HashMap;

/*
Problem:
    https://leetcode.com/problems/lru-cache/description/
 */
public class LRUCache {

    private DoubleLinkedListNode head;
    private DoubleLinkedListNode tail;
    int capacity;
    HashMap<Integer, DoubleLinkedListNode> cache;

    LRUCache(int capacity){
        this.head = new DoubleLinkedListNode(0, 0);
        this.tail = new DoubleLinkedListNode(0, 0);
        this.cache = new HashMap<>();
        this.capacity = capacity;

        // link tail and head from both the ends.
        head.next = tail;
        tail.prev = head;
    }

    /*
    Gets the value, if key is present
        else -1

        if the key is present
        we fetch that node
            we delete it from the end of the doubly linked list
            and add it right after head

        deleting means, just remove links from nodes, not actually deleting the node.

        Time Complexity: O(1)
            no traversal
            we access hash map which is O(1)
            and we directly access particular node which is also O(1)
     */
    public int get(int key){
        DoubleLinkedListNode node = cache.get(key);
        if(node == null)
            return -1;

        deleteNode(node);
        insertAfterHead(node);

        return node.val;
    }

    /*
    If the key is already present we update its value with what we have recieved in parameter
        during this operation that key is accessed
        so we remove the node from the list
        and add after the head

    if the capacity is hit
        if the hashmap size is equal to capacity means
            we have reached capacity and we have to delete LRU node before inserting new node

            least recently used node is node which is previous to tail
            we delete that node
            also, we delete this entry from hash map

            then we insert the new node after head

        Time Complexity: O(1)
     */
    public void put(int key, int val){
       if(cache.containsKey(key)){
           DoubleLinkedListNode node = cache.get(key);

           node.val = val;
           deleteNode(node);
           insertAfterHead(node);
       }
       else{
           if(cache.size() >= capacity){
               DoubleLinkedListNode nodeToDelete = tail.prev;

               deleteNode(nodeToDelete);
           }
           DoubleLinkedListNode newNode = new DoubleLinkedListNode(key, val);
           insertAfterHead(newNode);
       }
    }

    /*
        Deletes the specified node
        deletion means not actual deletion,
        we remove links

        when we delete, we remove entry from map(cache)

        Time Complexity: O(1)
     */
    public void deleteNode(DoubleLinkedListNode node){

        cache.remove(node.key);

        DoubleLinkedListNode prevNode = node.prev;
        DoubleLinkedListNode afterNode = node.next;

        prevNode.next = afterNode;
        afterNode.prev = prevNode;

        node.next = null;
        node.prev = null;
    }

    /*
    Adds an element right after head
    when we add, we add an entry to map(cache)

        Time Complexity: O(1)
     */
    public void insertAfterHead(DoubleLinkedListNode newNode){

        cache.put(newNode.key, newNode);

        DoubleLinkedListNode currentNextNode = head.next;

        head.next = newNode;
        newNode.prev = head;

        newNode.next = currentNextNode;
        currentNextNode.prev = newNode;
    }
}

/* each node will have key, value
 prev and next links.*/
class DoubleLinkedListNode{

    int key;
    int val;
    DoubleLinkedListNode prev;
    DoubleLinkedListNode next;

    DoubleLinkedListNode(int key, int val){
        this.key = key;
        this.val = val;
    }
}
