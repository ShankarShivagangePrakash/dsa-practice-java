package tries;

/*
Problem:
    https://leetcode.com/problems/longest-common-prefix/description/
 */
public class LongestCommonPrefix {

    Trie trie;

    LongestCommonPrefix(){
        trie = new Trie();
    }

    /*
    Approach:
        Explained in notes

        Time Complexity: O(n * len(words))
     */
    public String longestCommonPrefix(String[] words) {
        for(String word : words)
            trie.insert(word);

        return trie.getLongestPrefix(words[0], words.length);
    }
}

class Trie{

    LongestCommonPrefixTrieNode root;

    Trie(){
        root = new LongestCommonPrefixTrieNode();
    }

    public void insert(String word){
        LongestCommonPrefixTrieNode temp = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(!temp.containsKey(ch))
                temp.put(ch, new LongestCommonPrefixTrieNode());
            temp = temp.get(ch);
            temp.incrementPrefix();
        }
        temp.setEnd();
    }

    public String getLongestPrefix(String word, int len){
        StringBuilder result = new StringBuilder();

        LongestCommonPrefixTrieNode temp = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(temp.containsKey(ch)){
                temp = temp.get(ch);

                if(temp.getPrefixCount() != len)
                    return result.toString();
                result = result.append(ch);
            }
            else
                break;
        }
        return result.toString();
    }
}

class LongestCommonPrefixTrieNode {
    LongestCommonPrefixTrieNode[] links;
    int countPrefix;
    int countEndsWith;

    LongestCommonPrefixTrieNode(){
        links = new LongestCommonPrefixTrieNode[26];
        countEndsWith = 0;
        countPrefix = 0;
    }

    public boolean containsKey(char ch){
        return (links[ch-'a'] != null);
    }

    public LongestCommonPrefixTrieNode get(char ch){
        return links[ch-'a'];
    }

    public void put(char ch, LongestCommonPrefixTrieNode node){
        links[ch-'a'] = node;
    }

    public void incrementPrefix(){
        countPrefix++;
    }

    public int getPrefixCount(){
        return countPrefix;
    }

    public void setEnd(){
        countEndsWith++;
    }
}
