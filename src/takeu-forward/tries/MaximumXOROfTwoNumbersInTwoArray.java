package tries;

/*
Problem:
    https://takeuforward.org/data-structure/maximum-xor-of-two-numbers-in-an-array/
    https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
 */
public class MaximumXOROfTwoNumbersInTwoArray {

    MaximumXOROfTwoNumbersInTwoArrayTrie trie;

    MaximumXOROfTwoNumbersInTwoArray(){
        trie = new MaximumXOROfTwoNumbersInTwoArrayTrie();
    }

    public int findMaximumXOR(int[] nums1, int[] nums2) {
        for(int num : nums1)
            trie.insert(num);

        int maxi = 0;
        for(int num : nums2)
            maxi = Math.max(maxi, trie.maxXOR(num));

        return maxi;
    }
}

class MaximumXOROfTwoNumbersInTwoArrayTrie{

    MaximumXOROfTwoNumbersInArrayTrieNode root;

    MaximumXOROfTwoNumbersInTwoArrayTrie(){
        root = new MaximumXOROfTwoNumbersInArrayTrieNode();
    }

    // Method to insert a number into the Trie
    // TC-> O(32)
    public void insert(int num){
        MaximumXOROfTwoNumbersInArrayTrieNode temp = root;
        // insert all 31 bits of the number
        for(int i = 31; i >= 0; i--){
            // get current bit of the number and if it not present in current trie node insert it.
            int bit = (num >> i) & 1;
            if(!temp.containsKey(bit)){
                temp.put(bit, new MaximumXOROfTwoNumbersInArrayTrieNode());
            }
            // move to next node
            temp = temp.get(bit);
        }
    }

    // Method to find the maximum XOR value for a given number
    public int maxXOR(int num){
        MaximumXOROfTwoNumbersInArrayTrieNode temp = root;

        // Initialize the maximum XOR value
        int maxNum = 0;
        // Iterate through each bit of the number (from left to right)
        for(int i = 31; i >= 0; i--){
            // get current bit of the number
            int bit = (num >> i)& 1;

            // check if the complement of the current bit exist
            // if so, then XOR will yield 1
            if(temp.containsKey(1-bit)){
                // set the current bit in maxNum as 1 (since we need max value)
                maxNum = maxNum | ( 1 << i);

                // Move to the child node corresponding to the complement of the current bit
                temp = temp.get(1-bit);
            }
            // Move to the child node corresponding to the current bit
            else
                temp = temp.get(bit);
        }
        return maxNum;
    }

}

class MaximumXOROfTwoNumbersInArrayTrieNode{
    MaximumXOROfTwoNumbersInArrayTrieNode links[];

    MaximumXOROfTwoNumbersInArrayTrieNode(){
        // node of size 2 because, we store bit it can be either 0 or 1. so size[2]
        links = new MaximumXOROfTwoNumbersInArrayTrieNode[2];
    }

    public boolean containsKey(int bit){
        return (links[bit] != null);
    }

    public MaximumXOROfTwoNumbersInArrayTrieNode get(int bit){
        return (links[bit]);
    }

    public void put(int bit, MaximumXOROfTwoNumbersInArrayTrieNode node){
        links[bit] = node;
    }
}
