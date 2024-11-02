package sliding_window_two_pointer.hard;

import java.util.HashMap;

/*
Problem:
    https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumWindowSubString {

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(m * n^2)
            where m is the length of the String t
                  n is the length of the string s

        Space Complexity: O(256)
            in worst case scenario, if the characters in t and s are different
            we end up storing all characters in it
            but maximum number of characters available are 256
     */
    public static String minimumWindowSubStringBruteforce(String s, String t){
        int maxLen = Integer.MAX_VALUE, startinIndex = -1;

        for(int i  = 0; i < s.length(); i++){
            HashMap<Character, Integer> map = new HashMap<>();

            // compute the frequencies of character in String `t` and insert into hash map
            for(int j = 0; j < t.length(); j++) {
                char ch = t.charAt(j);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }

            int count = 0;
            for(int j = i; j < s.length(); j++){
                char ch = s.charAt(j);

                // charcter part of current substring is a character in String `t`
                // which means, we have found one character in string `t` so increment count.
                if(map.containsKey(ch) && map.get(ch).intValue() > 0)
                    count++;

                // since we have found a character decrement the frequency of the character in map
                map.put(ch, map.getOrDefault(ch, 0) - 1);

                // we have found all characters of string `t`
                if(count == t.length()){
                    // check is the length of the substring we have found currently is it lesser than `maxlen`
                    // if so update maxLen and startingIndex
                    if((j - i + 1) < maxLen){
                        maxLen = (j - i + 1);
                        startinIndex = i;
                        // break is required because, if you add anything to current substring, it might be valid but not be minimal
                        break;
                    }
                }
            }
        }

        return startinIndex == -1 ? "" : s.substring(startinIndex, startinIndex + maxLen);
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(2n)
            O(n) for outer while loop
            O(n) for inner while loop
        Space Complexity: O(256)
            hash map can have all characters available
     */
    public static String minimumWindowSubStringOptimal(String s, String t) {
        int left = 0, right = 0, minLen = Integer.MAX_VALUE, startingIndex = -1;
        int count = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        // insert the frequency of characters in string `t` to hash map
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        while (right < s.length()) {
            char ch = s.charAt(right);

            // if there is an entry for a character, then you need to decrement its frequency.
            if (map.containsKey(ch)) {
                /* if the frequency value is greater than 0 means, this character is present in string `t`
                 and we have found this character in current substring, so we can say we have found a character of string `t` in current substring
                 so to keep number of characters found, increment `count`*/
                if (map.get(ch) > 0)
                    count++;

                // decrement frequency.
                map.put(ch, map.get(ch) - 1);
            } else {
                /* else means, this character is not found in hash map, basically this character is not part of string `t`
                 initialize with -1 for the method to work*/
                map.put(ch, -1);
            }

            /* if count has become equal to t.length means our current substring has all characters of string `t`
             so we can proceed to update minLen and startingIndex*/
            while (count == t.length()) {
                if ((right - left + 1) < minLen) {
                    minLen = (right - left + 1);
                    startingIndex = left;
                }

                // now we check, can we shrink the current substring window and find minimum length substring containing all characters of string `t`
                char leftChar = s.charAt(left);
                // so we rollback the changes done, in main while loop we decrement frequency, here we increment frequency
                map.put(leftChar, map.get(leftChar) + 1);

                /* if the frequency has become greater than 0 means we have dropped a character from our current substring which was part of string `t`
                 so count has to decrement.*/
                if (map.get(leftChar) > 0)
                    count--;

                left++;
            }
            right++;
        }
        /* if we were not able to find substring containing all characters of string `t` in string `s`
         then we have to return ""
         else substring from startingIndex to minLen*/
        return (startingIndex == -1) ? "" : s.substring(startingIndex, startingIndex + minLen);
    }
}
