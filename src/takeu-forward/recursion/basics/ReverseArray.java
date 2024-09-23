package recursion.basics;

/*
Problem:
    https://leetcode.com/problems/reverse-string/description/
 */
public class ReverseArray {

    /*
    Approach:
        We keep two pointers i and j
        i will point to first index, j will point to last index of the array
            swap(s[i],s[j])
            increment i
            decrement j
        keep doing the same thing untill i crosses j

        Time Complexity: O(n/2)
        Space Complexity: O(1)
     */
    public void reverseStringIterative(char[] s) {
        int i = 0, j = s.length - 1;
        while( i < j){
            char k = s[i];
            s[i] = s[j];
            s[j] = k;

            i++;
            j--;
        }
    }

    public void reveseStringRecursive(char[] s){
        int i = 0, j = s.length - 1;

        reveseStringParameterizedRecursive(s, i, j);
    }

    /*
    Approach:
        It is parameterized recusrive method, in each recursive call
        along with making the problem smaller, we are also solving some portion of it
        in each recursive call, we are reversing two elements
        and moving to next index

        Time Complexity: O(n/2)
        Space Complexity: O(n/2) - recursive stack space for recursive methods
     */
    public void reveseStringParameterizedRecursive(char[] s, int i, int j){
        if( i >= j)
            return;

        char k = s[i];
        s[i] = s[j];
        s[j] = k;

        i++;
        j--;

        reveseStringParameterizedRecursive(s, i, j);

    }
}
