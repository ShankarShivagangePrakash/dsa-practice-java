package sliding_window_two_pointer;

import java.util.HashMap;

/*
Problem:
    https://leetcode.com/problems/longest-repeating-character-replacement/description/
 */
public class LongestRepeatingCharacterReplacement {

    /*
    Approach:
        Explained in detail, in notes

        Time Complexity: O(n^2)
            Two for loops to form all possible substring
        Space Complexity: O(26)
            it can store all english alphabets at worst case, so 26 english characters
     */
    public static int longestRepeatingCharacterReplacementBruteforce(String s, int k){
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++){
            // hash map to store the frequency of all characters in the current substring.
            HashMap<Character, Integer> map = new HashMap<>();
            // to store the frequency of the majority character in the current substring.
            int maxFrequency = 0;
            // form all possible substring
            for(int j = i; j < s.length(); j++){
                char ch = s.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                // to get the character with maximum frequency in the hashmap, you need not iterate through
                // entire hash map, you can compare current maxFrequency value and the recent entry you modified in the hash map
                maxFrequency = Math.max(maxFrequency, map.get(ch));
                // count of other characters in the current substring.
                int changes = (j-i + 1) - maxFrequency;

                // count of other characters is less than or equal to k means, we can find substring with same character. Else means not a valid substring ignore
                if(changes <= k)
                    maxLen = Math.max(maxLen, (j-i+1));
                else
                    break;
            }
        }
        return maxLen;
    }

    /*
    Approach:
        Explained in notes.

        we keep forming substring
            for the current substring we keep computing `maxFrequency`
            which represents the frequency of the majority character in the current substring

            also, we keep calculating the count of other characters in the current substring
                which is represented as `changes`
            as this  `changes` exceeds `k`, the substring we are considering will become invalid
            so we have to update substring boundaries
                so we shrink, substring by moving left pointer
                in this process, we update hash map and `maxFrequency` accordingly

             once substring becomes valid,
                we compute `maxLen`

            we return `maxLen` after completing while loop.

        Time Complexity: O( 2 * 26 * n)
            O(n + n) * 26
                - O(n) for outer while loop
                - O(n) for inner while loop for left pointer
                    inside inner while loop,
                    we are iterating hash map to update `maxFrequency`
                        hash map can have maximum of 26 characters entry - so we might iterate for 26 times
                        so multiply by 26

        Space Complexity: O(26)
            hash map size
     */
    public static int longestRepeatingCharacterReplacementBetter(String s, int k){
        int left = 0, right = 0, maxLen = 0, maxFrequency = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while (right < s.length()) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0)+1);

            maxFrequency = Math.max(maxFrequency, map.get(ch));

            // below changes expression, you put in while loop condition
            //int changes = (right - left + 1) - maxFrequency;
            while((right - left + 1) - maxFrequency > k){
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                /* but you should also this that maxFrequency value what we have might not be valid, after this while loop executes
                 because it might have removed some frequency from maxFrequency character
                 in that case, maxFrequency value will not be valid anymore
                 so we have to recompute maxFrequency
                 reset maxFrequency to zero, so that it will get set with correct value*/
                maxFrequency = 0;
                for(HashMap.Entry<Character, Integer> temp : map.entrySet())
                    maxFrequency = Math.max(maxFrequency, temp.getValue());

                left++;
            }

            // condition checks is number of changes `changes` required is <= k for the current substring,
            // if so we can update maxLen
            if((right - left + 1) - maxFrequency <= k)
                maxLen = Math.max(maxLen, (right - left + 1));

            right++;

        }
        return maxLen;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n)
            only single while loop

        Space Complexity: O(26)
            to store frequencies of all english alphabets
     */
    public static int longestRepeatingCharacterReplacementOptimal(String s, int k){
        int left = 0, right = 0, maxLen = 0, maxFrequency = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while(right < s.length()){
            char ch = s.charAt(right);

            map.put(ch, map.getOrDefault(ch, 0) + 1);

            maxFrequency = Math.max(maxFrequency, map.get(ch));

            if((right - left + 1) - maxFrequency > k){
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                left++;
            }

            if((right - left + 1) - maxFrequency <= k)
                maxLen = Math.max(maxLen, (right - left + 1));

            right++;
        }
        return maxLen;
    }
}
