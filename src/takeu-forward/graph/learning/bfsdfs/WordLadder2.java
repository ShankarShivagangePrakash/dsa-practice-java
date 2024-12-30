package graph.learning.bfsdfs;

import java.util.*;

/*
Problem:
    https://takeuforward.org/graph/g-30-word-ladder-ii/
    https://leetcode.com/problems/word-ladder-ii/description/
 */
public class WordLadder2 {
    /*
    Approach:
        Explained in notes, check

        Time Complexity and Space Complexity:
            It cannot be predicted for this particular algorithm because there can be multiple sequences of transformation from startWord to targetWord
            depending upon the example,
            so we cannot define a fixed range of time or space in which this program would run for all the test cases.

        Note: This approach/code will give TLE when solved on the Leetcode platform due to the strict time constraints being put up there.
        So, you need to optimize it to a greater extent in order to pass all the test cases for LeetCode.
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        // list of list to store answer
        List<List<String>> answer = new ArrayList<>();

        // we store all wordList in the set
        Set<String> set = new HashSet<>();
        int wordListLength = wordList.size();

        for(String s : wordList)
            set.add(s);

        // we create a queue
        // inside this queue, we store all the strings we have encountered so far in each BFS Path
        Queue<ArrayList<String>> queue = new LinkedList<>();
        // initally we start with beginWord, so add this to queue
        queue.offer(new ArrayList<>(Collections.singletonList(beginWord)));

        // usedOnLevel will have what all words have been used in current queue level.
        ArrayList<String> usedOnLevel = new ArrayList<>();
        // since we start with `beginWord` add it to this list
        usedOnLevel.add(beginWord);

        // represents current level
        int level = 0;
        while(!queue.isEmpty()){
            ArrayList<String> temp = queue.poll();

            // if the popped queue list has more elements than the currentlevel it means, that we have completed previous level
            // so we need to remove the words we chose in previous level from set.
            if(temp.size() > level){
                level++;
                for(String s : usedOnLevel)
                    set.remove(s);
            }

            // we will modify the latest word formed.
            String word = temp.get(temp.size() - 1);
            // before modifying check whether, the latest word formed is same as target word or not
            // if so, check the current path followed to achieve this if it is efficient, add it to answer
            if(word.equals(endWord)){
               if(answer.size() == 0 || answer.get(0).size() == temp.size())
                   answer.add(temp);
            }

            // we haven't reached, target word, so we will try with all 26 options for each character in the word
            for(int i = 0; i < word.length(); i++){
                char[] wordCharArray = word.toCharArray();
                for(char ch = 'a'; ch <= 'z'; ch++){
                    wordCharArray[i] = ch;

                    String newWord = new String(wordCharArray);

                    // if the newWord formed is available in the set means, we have to add this as a new entry to the queue
                    // new entry --- current array list + newWord
                    if(set.contains(newWord)){
                        ArrayList<String> newAddition = new ArrayList<>(temp);
                        newAddition.add(newWord);

                        // now add this newAddition to queue
                        queue.add(newAddition);

                        // also add this newWord to usedOnLevel, so that we know have we finished current level or not?
                        usedOnLevel.add(newWord);
                    }
                }
            }
        }
        return answer;
    }
}
