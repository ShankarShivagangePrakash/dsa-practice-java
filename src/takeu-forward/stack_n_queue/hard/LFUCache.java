package stack_n_queue.hard;

import java.util.HashMap;

/*
Problem:
    https://leetcode.com/problems/lfu-cache/description/
 */
public class LFUCache {

    private int capacity;
    int minFrequency;
    HashMap<Integer, DoubleLinkedListNodeLFU> cache;
    HashMap<Integer, DoubleLinkedList> frequencyMap;

    LFUCache(int capacity){
        this.capacity = capacity;

        minFrequency = 1;

        cache = new HashMap<>();
        frequencyMap = new HashMap<>();
    }

    /*
    Get the value by key
    and update the node frequency as well as relocate that node.
     */
    public int get(int key){
        DoubleLinkedListNodeLFU node = cache.get(key);

        if(node == null)
            return -1;

        updateNode(node);
        return node.val;
    }

    /*
        adds new node into LFU cache, as well as double linked list
     * condition 1: if LFU cache has input key, update node value and node position in list
     * condition 2: if LFU cache does NOT have input key
     *  - sub condition 1: if LFU cache does NOT have enough space, remove the Least Recent Used node
     *  in minimum frequency list, then add new node
     *  - sub condition 2: if LFU cache has enough space, add new node directly

        Time Complexity: O(1)
     */
    public void put(int key, int val){
        if(capacity == 0)
            return;

        // key already present just update its value and move to appropriate frequency map
        if(cache.containsKey(key)){
            DoubleLinkedListNodeLFU node = cache.get(key);
            node.val = val;
            updateNode(node);
        }
        else{
            if(cache.size() >= capacity){
                // get minimum frequency list
                DoubleLinkedList minimumFrequencyList = frequencyMap.get(minFrequency);

                // delete the element from the cache first
                cache.remove(minimumFrequencyList.tail.prev.key);

                // then actually remove that element from list
                minimumFrequencyList.deleteNode(minimumFrequencyList.tail.prev);

            }

            // reset min frequency to 1 because of adding new node
            minFrequency = 1;
            DoubleLinkedListNodeLFU newNode = new DoubleLinkedListNodeLFU(key, val);
            cache.put(key, newNode);

            // get the list with frequency 1, and then add new node into the list, as well as into LFU cache
            DoubleLinkedList currentList = frequencyMap.getOrDefault(1, new DoubleLinkedList());
            currentList.insertAfterHead(newNode);

            frequencyMap.put(1, currentList);
        }
    }

    // increments the frequency of a node and moves it to appropriate linked list also update minFrequency - O(1)
    public void updateNode(DoubleLinkedListNodeLFU node){
        int currFreq = node.frequency;
        DoubleLinkedList currentList = frequencyMap.get(currFreq);
        currentList.deleteNode(node);

        // if current list the the last list which has lowest frequency and current node is the only node in that list
        // we need to remove the entire list and then increase min frequency value by 1
        if (currFreq == minFrequency && currentList.listSize == 0) {
            minFrequency++;
        }

        node.frequency++;

        // add current node to another list has current frequency + 1,
        // if we do not have the list with this frequency, initialize it
        DoubleLinkedList newList = frequencyMap.getOrDefault(node.frequency, new DoubleLinkedList());
        newList.insertAfterHead(node);
        frequencyMap.put(node.frequency, newList);
    }
}

class DoubleLinkedListNodeLFU{

    int key;
    int val;
    int frequency;
    DoubleLinkedListNodeLFU next;
    DoubleLinkedListNodeLFU prev;

    DoubleLinkedListNodeLFU(int key, int val){

        this.key = key;
        this.val = val;
        this.frequency = 1;
    }
}

class DoubleLinkedList{
    int listSize;
    DoubleLinkedListNodeLFU head;
    DoubleLinkedListNodeLFU tail;

    DoubleLinkedList(){
        this.listSize = 0;

        this.head = new DoubleLinkedListNodeLFU(0, 0);
        this.tail = new DoubleLinkedListNodeLFU(0, 0);

        this.head.next = tail;
        this.tail.prev = head;
    }

    /* Adds element right after head and increment list size by 1*/
    public void insertAfterHead(DoubleLinkedListNodeLFU newNode){
        DoubleLinkedListNodeLFU currentNextNode = head.next;

        newNode.prev = head;
        head.next = newNode;

        newNode.next = currentNextNode;
        currentNextNode.prev = newNode;

        listSize++;
    }

    /* Delets the node, means just remove links and decrement listSize by 1*/
    public void deleteNode(DoubleLinkedListNodeLFU node){
        DoubleLinkedListNodeLFU before = node.prev;
        DoubleLinkedListNodeLFU after = node.next;

        before.next = after;
        after.prev = before;

        node.next = null;
        node.prev = null;

        listSize--;
    }
}

