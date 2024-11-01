package sliding_window_two_pointer.hard;

import java.util.HashMap;

/*
Problem:
    https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

    /*
    Approach:
        Generate all substrings with atmost k distinct characters
        compare its length with maxLen and update accordingly

        Time Complexity: O(n^2)
        Space Complexity: O(256)
            hash map size at worst case scneario
            all 256 characters possible.
     */
    public static int longestSubStringWithAtMostKDistinctCharactersBruteforce(String s, int k){
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++){
            HashMap<Character, Integer> map = new HashMap<>();
            for(int j = i; j < s.length(); j++){
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if(map.size() <= k)
                    maxLen = Math.max(maxLen, (j-i+1));
                else
                    break;
            }
        }
        return maxLen;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2n)
            O(n) for outer while loop
            O(n) for inner while loop

        Space Complexity: O(k)
            hash map size, it can have at max k different characters.
     */
    public static int longestSubStringWithAtMostKDistinctCharacterBetterApproach(String s, int k){
        int left = 0, right = 0, maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while(right < s.length()){
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while(map.size() > k){
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                if(map.get(leftChar) == 0)
                    map.remove(leftChar);

                left++;
            }

            if(map.size() <= k)
                maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }
        return maxLen;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            O(n) for outer while loop

        Space Complexity: O(k)
            hash map size
     */
    public static int longestSubStringWithAtMostKDistinctCharacterOptimal(String s, int k){
        int left = 0, right = 0, maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while(right < s.length()){
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // current substring is not valid,
            // remove few characters from the substring, i.e left index.
            if(map.size() > k){
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                if(map.get(leftChar) == 0)
                    map.remove(leftChar);

                left++;
            }

            // map size <= K means the current substring is valid
            // current substring length is (right - left + 1)
            if(map.size() <= k)
                maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }
        return maxLen;
    }
}
