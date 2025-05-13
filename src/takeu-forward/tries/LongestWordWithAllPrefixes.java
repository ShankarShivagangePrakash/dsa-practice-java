package tries;

import java.util.List;

/*
Problem:
    https://takeuforward.org/plus/dsa/problems/longest-word-with-all-prefixes
 */
public class LongestWordWithAllPrefixes {

    private SimpleTrie trie1;

    LongestWordWithAllPrefixes(){
        trie1 = new SimpleTrie();
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n * len(word)) + O(n * len(word))
            O(n * len(word)) - for inserting all words to trie data structure
            O(n * len(word)) - for finding complete word

        Space Complexity: for trie data structure, we cannot clearly say space complexity
            as each trie node can have reference to other 26 trie nodes, and they can further have more references
            so, we cannot say space complexity clearly
     */
    public String completeString(List<String> words) {
        // insert each input word to trie datastructure.
        for(String word : words)
            trie1.insert(word);

        String longestCompleteString = "";
        for(String word : words){
            // all prefixes exists for the word
            if(trie1.checkIfAllPrefixExists(word)){
                // there are two cases, the current word is the longest complete word so far
                // else it is equal to longest but lexicographically smallest.
                if(word.length() > longestCompleteString.length())
                    longestCompleteString = word;
                else if(word.length() == longestCompleteString.length() && word.compareTo(longestCompleteString) < 1)
                    longestCompleteString = word;
            }
        }
        return longestCompleteString;
    }
}

class SimpleTrie{
    private TrieNode root;

    SimpleTrie(){
        root = new TrieNode();
    }

    public void insert(String word){
        TrieNode temp = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(!temp.containsKey(ch))
                temp.put(ch, new TrieNode());

            temp = temp.get(ch);
        }
        temp.setEnd();
    }

    // form all prefixes possible for a given string and check, whether that prefix exists in string/trie as a word.
    public boolean checkIfAllPrefixExists(String word){
        boolean flag = true;

        TrieNode temp = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(temp.containsKey(ch)){
                temp = temp.get(ch);
                if(temp.isEnd() == false)
                    return false;
            }
            else
                return false;
        }
        return true;
    }
}
