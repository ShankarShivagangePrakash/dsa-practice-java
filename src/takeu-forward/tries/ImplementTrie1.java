package tries;

/*
Problem:
    https://takeuforward.org/data-structure/implement-trie-1/
    https://leetcode.com/problems/implement-trie-prefix-tree/description/
 */
public class ImplementTrie1 {

    private TrieNode root;

    /*
    Approach:
        Explained in program

        Time Complexity: Each method has same time complexity, O(len of the word)
     */
    public ImplementTrie1() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    // Time Complexity O(len), where len is the length of the word
    public void insert(String word){
        TrieNode temp = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            // Create a new node for the letter if not present
            if(!temp.containsKey(word.charAt(i))){
                temp.put(ch, new TrieNode());
            }
            // Move to the next node/letter
            temp = temp.get(ch);
        }
        // Mark the end of the word
        temp.setEnd();
    }

    // TC -> O(len of word)
    public boolean search(String word){
        TrieNode temp = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            // check, is current character present? if not return false
            if(!temp.containsKey(ch))
                return false;

            // move to next node/character
            temp = temp.get(ch);
        }
        // check is the end of the word or not?
        return temp.isEnd();
    }

    // TC -> O(len of word)
    public boolean startsWith(String prefix){
        TrieNode temp = root;
        for(int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);
            // If a letter is not found, there is no word with the given prefix
            if(!temp.containsKey(ch))
                return false;

            // Move to the next node
            temp = temp.get(ch);
        }
        // The prefix is found in the Trie
        return true;
    }
}

class TrieNode{
    TrieNode[] links = new TrieNode[26];
    boolean flag = false;

    public TrieNode() {
    }

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node){
        links[ch-'a'] = node;
    }

    public void setEnd(){
        this.flag = true;
    }

    public boolean isEnd(){
        return flag;
    }
}
