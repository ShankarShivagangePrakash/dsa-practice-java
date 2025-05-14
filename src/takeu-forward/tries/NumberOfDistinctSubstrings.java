package tries;

/*
Problem:
    https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/description/
 */
public class NumberOfDistinctSubstrings {

    private NumberOfDistinctSubStringsTrieNode root;

    NumberOfDistinctSubstrings(){
        root = new NumberOfDistinctSubStringsTrieNode();
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n^2)
            to add one word to trie we require O(len of the word) in this case it will become O(n)
            but we keep adding words removing characters from the word, the size keeps reducing
            so, on average another O(n) for rest of the string

            O(n^2)

        Space Complexity: We cannot determine space complexity in Trie data structure.
     */
    public int countDistinctSubstring(String s) {
        int counter = 0;

        // Nested loops to iterate through all possible substrings of the input string
        for(int i = 0; i < s.length(); i++){
            // reset trie iterator to root node after for new index of i
            NumberOfDistinctSubStringsTrieNode temp = root;
            for(int j = i; j < s.length(); j++){
                char ch = s.charAt(j);
                // Iterate through each character of the substring If the current character is not a child of the current node, insert it as a new child node
                // so it becomes new substring, increment counter.
                if(!temp.containsKey(ch)){
                    counter++;
                    temp.put(ch, new NumberOfDistinctSubStringsTrieNode());
                }
                temp = temp.get(ch);
            }
        }
        // counter will have all unique possible substring except empty string "", so add +1 for it.
        return counter + 1;
    }
}

class NumberOfDistinctSubStringsTrieNode{
    NumberOfDistinctSubStringsTrieNode[] links;

    NumberOfDistinctSubStringsTrieNode(){
        links = new NumberOfDistinctSubStringsTrieNode[26];
    }

    public boolean containsKey(char ch){
        return (links[ch-'a'] != null);
    }

    public NumberOfDistinctSubStringsTrieNode get(char ch){
        return links[ch-'a'];
    }

    public void put(char ch, NumberOfDistinctSubStringsTrieNode node){
        links[ch-'a'] = node;
    }
}
