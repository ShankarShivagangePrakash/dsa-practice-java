package sliding_window_two_pointer;

import java.util.HashMap;

/*
Problem:
    https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/
 */
public class NumberOfSubStringContainingAllThreeCharacters {

    /*
    Approach:
        Generate all possible substrings using two for loops

        Whenever a substring containing all three characters are found
        that is a valid substring
        to that substring, whatever we add it will be valid substring
        so instead of finding all those remaining substring of this kind, we simply do (n-j)

        Time complexity: O(n^2)

        Space Complexity: O(3)
     */
    public static int numberOfSubStringContainingAllThreeCharactersBruteforce(String s){
        int count = 0;

        for(int i = 0; i < s.length(); i++){
            HashMap<Character, Integer> map = new HashMap<>();
            for(int j = i; j < s.length(); j++){
                char ch = s.charAt(j);
                map.put(ch, 1);

                if(map.size() == 3) {
                    count = count + (s.length() - j);
                    break;
                }
            }
        }
        return count;
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(3 * n)
            O(n) for right pointer to traverse the string.
            O(3) to find `subStringBeginingIndex` which is the minimum value in the sub string.

        space Complexity: O(1)
            hash map of size 3 which is equivalent to O(1)
     */
    public static int numberOfSubStringContainingAllThreeCharactersOptimal(String s){
        int right = 0, count = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while(right < s.length()){
            char ch = s.charAt(right);
            // store the latest index at which character `ch` has appeared.
            map.put(ch, right);

            /* if hash map size has become 3 means, you have found the substring containing all three characters
             among it you have to find the smallest substring at it's begining index
             then beginingIndex + 1 number of substrings can be formed using current substring containing all three characters.*/
            if(map.size() == 3){
                int subStringBeginingIndex = Integer.MAX_VALUE;
                for(HashMap.Entry<Character, Integer> temp : map.entrySet())
                    subStringBeginingIndex = Math.min(subStringBeginingIndex, temp.getValue());

                count = count + subStringBeginingIndex + 1;
            }
            right++;
        }
        return count;
    }
}
