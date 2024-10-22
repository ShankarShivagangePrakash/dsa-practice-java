package stack_n_queue.monotonic;

import java.util.Stack;
import java.util.HashMap;

/*
Problem:
    https://takeuforward.org/data-structure/next-greater-element-using-stack/
    https://leetcode.com/problems/next-greater-element-i/
 */
public class NextGreaterElement {

    /*
    Approach:
        Explained in notes

    Time Complexity: O(n^2)
    Space Complexity: O(n)
     */
    public static int[] nextGreaterElementGenericBruteforce(int[] arr){
        int[] nge = new int[arr.length];
        nge[arr.length-1] = -1;

        for(int i = 0; i < arr.length-1; i++){
            boolean foundGreater = false;
            for(int j =i+1; j < arr.length && !foundGreater; j++){
                if(arr[j] > arr[i]){
                    foundGreater = true;
                    nge[i] = arr[j];
                }
            }
            if(!foundGreater)
                nge[i] = -1;
        }
        return nge;
    }

    /*
    Approach:
        Explained in notes

    Time Complexity: O(2n)
        even though thre is a for loop and inside it there is a while loop
        for loop runs for n times
        stack will store array elements, so at maximum it can have n elements
        so maximum time complexity it can take will be O(n)

        so adding both O(2)

    Space Complexity: O(2n)
        O(n) for stack and another O(n) for storing result.
     */
    public static int[] nextGreaterElementGenericOptimal(int[] arr){
        int[] nge = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for(int i = arr.length-1; i >= 0; i--){

            while(!stack.isEmpty() && stack.peek() <= arr[i])
                stack.pop();

            if(stack.isEmpty())
                nge[i] = -1;
            else
                nge[i] = stack.peek();

            stack.push(arr[i]);
        }
        return nge;
    }

    /*
    Approach:
        Explained in notes

    Time Complexity: O(2n * m), equivalent O(n^2)
        Two for loops
        O(2n) for outer for loop and a while loop
        O(m) for search an element in nums1 array.
    Space Complexity: O(n) + O(m)
        n - for hash map
        m - for storing result
     */
    public static int[] nextGreaterElementLeetCodeVariantBruteForce(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[nums1.length];

        for(int i = nums2.length-1; i>= 0; i--){

            while(!stack.isEmpty() && stack.peek() <= nums2[i])
                stack.pop();

            for(int j = 0; j < nums1.length; j++){
                if(nums1[j] == nums2[i]){
                    if(stack.isEmpty())
                        nge[j] = -1;
                    else
                        nge[j] = stack.peek();
                }
            }
            stack.push(nums2[i]);
        }
        return nge;
    }

    /*
    Approach:
        Explained in notes

    Time Complexity: O(2n) + O(m)
        O(2n) for outer for loop and a while loop
        O(m) is for seperate nums1 array for loop.
    Space Complexity: O(n) + O(m) +(2n)
        n - for hash map
        m - for storing result
        O(2n) for hash map.
     */
    public static int[] nextGreaterElementLeetCodeVariantOptimal(int[] nums1, int[] nums2){
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[nums1.length];
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = nums2.length-1; i >= 0; i--){
                while(!stack.isEmpty() && stack.peek() <= nums2[i])
                    stack.pop();

                if(stack.isEmpty())
                    map.put(nums2[i], -1);
                else
                    map.put(nums2[i], stack.peek());

                stack.push(nums2[i]);
        }

        for(int i = 0; i < nums1.length; i++){
            nge[i] = map.getOrDefault(nums1[i], -1);
        }
        return nge;
    }

    public static void main(String[] args) {
        int[] arr = {5,7,1,2,6,0};
        int[] resultBruteForce = nextGreaterElementGenericBruteforce(arr);
        int[] resultOptimal = nextGreaterElementGenericOptimal(arr);

        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        int[] resultLeetCodeBruteForce = nextGreaterElementLeetCodeVariantBruteForce(nums1, nums2);
        int[] resultLeetCodeOptimal = nextGreaterElementLeetCodeVariantOptimal(nums1, nums2);
        System.out.println();
    }
}
