package graph.learning.bfsdfs;

import javafx.util.Pair;

import java.util.*;

/*
Problem:
    https://takeuforward.org/graph/word-ladder-i-g-29/
    https://leetcode.com/problems/word-ladder/description/
 */
public class WordLadder1 {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(N * M * 26)

            Where N = size of wordList Array and M = word length of words present in the wordList..

           we run the while loop,
           at max, we will insert all strings in wordList so we would run while loop for `n` times

           inside while loop, we will try to replace each character in the given word with all possible 26 characters
            so it takes m * 26
            m is the size of the word.

        Space Complexity:  O( N )
           we create set of wordList size

            Where N = size of wordList Array.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        int wordListLength = wordList.size();

        // move wordList contents to hashSet to effective access and remove words.
        Set<String> set = new HashSet<>();
        for(String i : wordList)
            set.add(i);

        set.remove(beginWord);

        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(beginWord, 1));

        while(!queue.isEmpty()){
            Pair temp = queue.poll();

            String word = (String) temp.getKey();
            int level = (int) temp.getValue();

            // if the word we have formed is matching with the target word,
            // return the level, we require that many changes to achieve target word.
            if(word.equals(endWord))
                return level;

            // replace each character in the word with all possible 26 character options.
            for(int i = 0; i < word.length(); i++) {
                char[] wordCharArray = word.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    wordCharArray[i] = ch;

                    String newWord = new String(wordCharArray);

                    // if the formed word is present in the wordList means, we can go in this direction of BFS traversal
                    if(set.contains(newWord)){
                        // usually we have visited array in BFS/DFS traversal to avoid entering already visited path
                        // since we don't have that visited array here, we can just remove that word from set.
                        // we will simply remove that word from the set to avoid traversing to same path again.
                        set.remove(newWord);
                        queue.offer(new Pair<>(newWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }
}
