package sliding_window_two_pointer;

import java.util.HashSet;
import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/length-of-longest-substring-without-any-repeating-character/
    https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n^2)
        Space Complexity: O(256)
            we are using a hashmap, in worst case scenario it might have all characters in it
            number of unique characters in english are 256
     */
    public static int longestSubstringWithoutRepeatingCharacterBruteforce(String s){
        int n = s.length();
        int maxLen = Integer.MIN_VALUE;

        if(s.length() == 0)
            return 0;

        for(int i = 0; i < n; i++){
            HashSet<Character> set = new HashSet<>();
            for(int j = i; j < n; j++) {
                if (set.contains(s.charAt(j))) {
                    maxLen = Math.max(maxLen, j - i);
                    break;
                }
                set.add(s.charAt(j));
            }
        }

        return maxLen;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            we traverse using `r` pointer it might traverse the entire string So O(n)

        Space Complexity: O(256)
            same we have a hashmap, worst case hashmap can store all english characters
            we have 256 different characters in english
     */
    public static int longestSubstringWithoutRepeatingCharacterOptimal(String s){
        if(s.length() == 0)
            return 0;

        int n = s.length();

        int l = 0, r = 0;
        int maxLen = Integer.MIN_VALUE;

        HashMap<Character, Integer> map = new HashMap<>();

        while( r < n){
            if(map.containsKey(s.charAt(r))){
                if(map.get(s.charAt(r)) >= l)
                    l = map.get(s.charAt(r)) + 1;
            }

            maxLen = Math.max(maxLen, (r-l) + 1);

            map.put(s.charAt(r), r);

            r++;

        }

        return maxLen;
    }
}
