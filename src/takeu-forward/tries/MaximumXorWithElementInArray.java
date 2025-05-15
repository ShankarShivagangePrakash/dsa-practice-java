package tries;

import java.util.*;

/*
Problem:
    https://takeuforward.org/trie/maximum-xor-queries-trie/
    https://leetcode.com/problems/maximum-xor-with-an-element-from-array/description/
 */
public class MaximumXorWithElementInArray {

    /*
    Approach:
        Explained in program

        Time Complexity: O(32*N + Q(logQ) + 32*Q) where N is the size of the input array and Q is the number of queries.
            For each number in the input array, we traverse its bits from left to right (total of 32 bits).
            Since there are ‘N’ numbers in the array, the total time complexity is O(32*N).
            Sorting the offline queries based on their endpoints requires O(Q*log(Q) time using the inbuilt library for sorting.
            For each query, we traverse the bits of the numbers in the Trie to find the maximum XOR value.
            Since each number has 32 bits and there are Q Queries, the total time complexity for processing is O(32*Q).

        Space Complexity: O(32*N + Q) where N is the size of the input array and Q is the number of queries.
            The space complexity of the Trie depends on the number of bits required to represent the numbers in the input array.
            Each number is represented as a sequence of 32 bits hence the space required by the Trie is O(32*N).
            We store the queries and sort them based on the endpoint of each query.
            This requires an additional space complexity of O(Q).
     */
    public List<Integer> maximizeXor(int[] nums, int[][] queries) {

        int m = queries.length;
        ArrayList<ArrayList<Integer>> queriesWithOrder = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int xi = queries[i][0];
            int ai = queries[i][1];
            int ind = i;
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(xi);
            temp.add(ai);
            temp.add(i);

            queriesWithOrder.add(temp);
        }

        Collections.sort(queriesWithOrder, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o1.get(1) - o2.get(1);
            }
        });

        Arrays.sort(nums);

        int ind = 0;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < m; i++)
            result.add(-1);

        MaximumXorWithElementInArrayTrie trie = new MaximumXorWithElementInArrayTrie();
        for (int i = 0; i < m; i++) {
            int xi = queriesWithOrder.get(i).get(0);
            int ai = queriesWithOrder.get(i).get(1);
            int index = queriesWithOrder.get(i).get(2);

            while (ind < nums.length && nums[ind] <= ai) {
                trie.insert(nums[ind]);
                ind++;
            }
            if (ind > 0) {
                result.set(index, trie.getMaxXor(xi));
            }
        }
        return result;
    }
}

class MaximumXorWithElementInArrayTrie{
    private MaximumXorWithElementInArrayTrieNode root;

    public MaximumXorWithElementInArrayTrie(){
        this.root = new MaximumXorWithElementInArrayTrieNode();
    }

    public void insert(int num){
        MaximumXorWithElementInArrayTrieNode node = root;
        for(int i = 31; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(!node.containsKey(bit)){
                node.put(bit, new MaximumXorWithElementInArrayTrieNode());
            }
            node = node.get(bit);
        }
    }

    public int getMaxXor(int num){
        MaximumXorWithElementInArrayTrieNode node = root;
        int maxXor = 0;
        for(int i = 31; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(node.containsKey(1 - bit)){
                maxXor |= (1 << i);
                node = node.get(1 - bit);
            }else{
                node = node.get(bit);
            }
        }
        return maxXor;
    }
}

class MaximumXorWithElementInArrayTrieNode{
    MaximumXorWithElementInArrayTrieNode[] links;

    MaximumXorWithElementInArrayTrieNode(){
        links = new MaximumXorWithElementInArrayTrieNode[2];
    }

    public boolean containsKey(int bit){
        return links[bit] != null;
    }

    public void put(int bit, MaximumXorWithElementInArrayTrieNode node){
        links[bit] = node;
    }

    public MaximumXorWithElementInArrayTrieNode get(int bit){
        return links[bit];
    }
}
