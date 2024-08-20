package arrays.medium;

import java.util.ArrayList;

/*
Problem:
    https://takeuforward.org/data-structure/leaders-in-an-array/
    https://www.geeksforgeeks.org/problems/leaders-in-an-array-1587115620/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=leaders-in-an-array
 */
public class LeadersInArray {

    /*
    Approach:
        since last most element will be always greatest. we add that to result list
        and keep it as currentMax
        then,
        First, we'll start the traversal from the right. Then, we move toward the left.
        Whenever we encounter a new element, we check with the currentMax.
        If the current element is greater than the currentMax, then the current element is one of the leaders, and we update the currentMax.
        Else, we proceed with the further elements on the left.

        Time Complexity: O(n)
        Space Complexity: O(1)
        but the arraylist created is to return the result, not to solve the problem. If we want to consider that also it will become O(n)
     */
    public static ArrayList<Integer> leaders(int[] arr){
        ArrayList<Integer> result = new ArrayList<>();
        result.add(arr[arr.length - 1]);
        int currentMax = arr[arr.length - 1];

        for(int i = arr.length -2; i >=0; i--){
            if(arr[i] > currentMax){
                result.add(0, arr[i]);
                currentMax = arr[i];
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[] arr = {4, 7, 1, 0};
        ArrayList<Integer> result = leaders(arr);
        System.out.println(result.toString());
    }
}
