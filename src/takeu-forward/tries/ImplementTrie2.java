package tries;

/*Problem:
    https://takeuforward.org/data-structure/implement-trie-ii/
    https://leetcode.com/problems/implement-trie-ii-prefix-tree/description/
 */
public class ImplementTrie2 {
    private TrieNode2 root;

    ImplementTrie2(){
        root = new TrieNode2();
    }

    // O(len)
    public void insert(String word){
        TrieNode2 node = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);

            if(!node.containsKey(ch))
                node.put(ch, new TrieNode2());

            node = node.get(ch);
            // since we have added character, we have to increment prefix value
            node.incrementCountPrefix();
        }
        // word is added, so increment ends with counter
        node.incrementCountEndsWith();
    }

    // O(len)
    public int countWordsEqualTo(String word){
        TrieNode2 node = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);

            if(!node.containsKey(ch))
                return 0;

            node = node.get(ch);
        }
        // word is found, return counts with end value
        return node.getCntEndWith();
    }

    // O(len)
    public int countStartsWith(String prefix){
        TrieNode2 node = root;
        for(int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);

            if(!node.containsKey(ch))
                return 0;

            node = node.get(ch);
        }
        // prefix found, return count prefix so far
        return node.getCntPrefix();
    }

    // O(len)
    public void erase(String word){
        TrieNode2 node = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);

            if(!node.containsKey(ch))
                return;

            node = node.get(ch);
            // you are removing the character, so decrement prefix value
            node.decrementCountPrefix();
        }
        // word is deleted, so decrement countsWith End
        node.decrementCountsWithEnd();
    }
}

class TrieNode2{
    TrieNode2[] links;
    // Counter for number of words that end at this node
    int cntEndWith;
    // Counter for number of words that have this node as a prefix
    int cntPrefix;

    TrieNode2(){
        links = new TrieNode2[26];
        cntEndWith = 0;
        cntPrefix = 0;
    }

    public boolean containsKey(char ch){
        return (links[ch-'a'] != null);
    }

    public TrieNode2 get(char ch){
        return links[ch-'a'];
    }

    public void put(char ch, TrieNode2 node){
        links[ch-'a'] = node;
    }

    public int getCntEndWith(){
        return cntEndWith;
    }

    public int getCntPrefix(){
        return cntPrefix;
    }

    public void incrementCountEndsWith(){
        cntEndWith++;
    }

    public void incrementCountPrefix(){
        cntPrefix++;
    }

    public void decrementCountsWithEnd(){
        cntEndWith--;
    }

    public void decrementCountPrefix(){
        cntPrefix--;
    }

}
