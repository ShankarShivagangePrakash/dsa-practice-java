package recursion.hard;

import java.util.List;
import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/palindrome-partitioning/
    https://leetcode.com/problems/palindrome-partitioning/description/
 */
public class PalindromePartitioning {

    /*
    Approach:
        Explained in detail in notes.

        Time Complexity: Time Complexity: O( (2^n) *k*(n/2) )

            Reason:
                O(2^n) to generate every substring
                and O(n/2)  to check if the substring generated is a palindrome.
                O(k) is for inserting the palindromes in another data structure,
                    where k  is the average length of the palindrome list.

        Space Complexity: O(k * x)

            Reason: The space complexity can vary depending upon the length of the answer.
            k is the average length of the list of palindromes and if we have x such list of palindromes in our final answer.
            The depth of the recursion tree is n,

            so the auxiliary space required is equal to the O(n).
     */
    public List<List<String>> parition(String s){
        // result list will store all possible partitions.
        List<List<String>> result = new ArrayList<>();
        /* each possible partitions, In the result list
         there werer multiple lines, each line will represent path. Check notes.*/
        List<String> path = new ArrayList<>();

        parititonHelper(0, s, path, result);
        return result;
    }

    public void parititonHelper(int index, String s, List<String> path, List<List<String>> result){

        // if we have reached end of the string means, we have found a valid parititon add it to the result list.
        if(index == s.length()){
            result.add(new ArrayList<>(path));
            return;
        }

        /* we are performing splits for right sub string.
         we try spliting at every position of right sub string, so loop from index to s.length()
         if you observe we are doing ++i, because we want to split at next index.*/
        for(int i = index; i < s.length(); ++i){

            /* if the left substring from index to i (substring formed by current split)
             if it is palindrome, we can add this substring to current parition list and we move to split right half of it.*/
            if(isPalindrome(s, index, i)){

                // add current substring to path
                path.add(s.substring(index, i+1));

                /* right half is the path next to current sub string
                 current substring is from index to i
                 so right substring starts from i+1, so partitionHelper we are passing i+1*/
                parititonHelper(i+1, s, path, result);

                //backtracking step.
                path.remove(path.size()-1);
            }
        }
    }

    // checking is it palindrome or not?
    public boolean isPalindrome(String s, int start, int end){
        while(start <= end){
            if(s.charAt(start) == s.charAt(end)){
                start++;
                end--;
            }
            else
                return false;
        }
        return true;
    }
}
