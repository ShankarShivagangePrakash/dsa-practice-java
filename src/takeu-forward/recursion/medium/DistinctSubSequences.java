package recursion.medium;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/*
Problem:
    https://www.geeksforgeeks.org/problems/better-string/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=better-string

Problem Statement:
    Given two strings, form unique all possible sub sequences for both strings
    and return the string which is having greater number of sub sequences.
 */
public class DistinctSubSequences {

    /*
    Approach:
        We can use the same approach of finding all sub sequences for bot the strings.
        If you want to revise how to find all sub sequences for a string, refer to `GetAllSubSequence.java`

        But there are few differences
            - In the previous problem input was Integer array, but here it is String,
                To store sub sequences we use Character array List<Character>
                in previous problem it was List<Integer>
            - the problem statement is find unique sub sequences, so to store unique sub sequences,
                we will use Set<List<Character>>, because set will store only unique entries, so we can avoid duplicate sub sequences
                in previous problem it was List<List<Integer>>

        Rest everthing is same

        Time Complexity: O(2^n)
            Check notes of `GetAllSubSequence.java`
        Space Complexity: O(n)
            Check notes of `GetAllSubSequence.java`
     */
    public static String getStringWithMoreSubsequence(String s1, String s2){

        HashSet<List<Character>> result1 = new HashSet<List<Character>>();
        HashSet<List<Character>> result2 = new HashSet<List<Character>>();

        getUniqueSubsequences(0, new ArrayList<Character>(), s1, result1);
        getUniqueSubsequences(0, new ArrayList<Character>(), s2, result2);

        if(result1.size() >= result2.size())
            return s1;
        return s2;
    }

    public static void getUniqueSubsequences(int ind, List<Character> temp, String s, Set<List<Character>> result){

        if(ind >= s.length()){
            result.add(new ArrayList<>(temp));
            return;
        }

        temp.add(s.charAt(ind));
        getUniqueSubsequences(ind+1, temp, s, result);

        temp.remove(temp.size()-1);
        getUniqueSubsequences(ind+1, temp, s, result);
    }

    public static void main(String[] args){
        String s1 = "abcd";
        String s2 = "efg";

        String result = getStringWithMoreSubsequence(s1, s2);
        System.out.printf("String with larger sub sequence %s\n", result);

    }
}
