package arrays.binarysearch.onanswers.minmax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem:
    https://takeuforward.org/data-structure/allocate-minimum-number-of-pages/
    https://www.naukri.com/code360/problems/allocate-books_1090540?utm_source=youtube&utm_medium=affiliate&utm_campaign=codestudio_Striver_BinarySeries
 */
public class BookAllocation {

    /*
    Approach:
        Read notes

       Time Complexity: O(N * (sum(arr[])-max(arr[])+1)),
            where N = size of the array, sum(arr[]) = sum of all array elements, max(arr[]) = maximum of all array elements.
            Reason: We are using a loop from max(arr[]) to sum(arr[]) to check all possible numbers of pages.
            Inside the loop, we are calling the countStudents() function for each number.
            Now, inside the countStudents() function, we are using a loop that runs for N times.

       Space Complexity: O(1)
     */
    public static int findPagesBruteForce(ArrayList<Integer> arr, int n, int noOfStudents){
        // if number of students is more than number of books, we can distribute books among all, return -1
        if(noOfStudents > n)
            return -1;

        // maximum in the array, is our lowest possible answer
        int low = Collections.max(arr);
        // maximum possible answer will be summation of the array.
        int high = arr.stream().mapToInt(i-> i.intValue()).sum();

        for(int i = low; i <= high; i++){
            if(countStudents(arr, i) == noOfStudents){
                return i;
            }
        }
        return high;
    }

    /*
    Approach:
        Read notes

        Time Complexity: O(N * log(sum(arr[])-max(arr[])+1)),
            where N = size of the array, sum(arr[]) = sum of all array elements, max(arr[]) = maximum of all array elements.
            Reason: We are applying binary search on [max(arr[]), sum(arr[])]. Inside the loop, we are calling the countStudents() function for the value of ‘mid’.
            Now, inside the countStudents() function, we are using a loop that runs for N times.

        Space Complexity: O(1)
     */
    public static int findPagesOptimal(ArrayList<Integer> arr, int n, int noOfStudents){
        // if number of students is more than number of books, we can distribute books among all, return -1
        if(noOfStudents > n)
            return -1;

        int low = arr.stream().reduce((a, b) -> a > b ? a : b).orElseThrow(()-> new RuntimeException("Error while finding max"));
        int high = arr.stream().reduce(0, (sum, i)-> sum + i);

        int ans = -1;

        while(low <= high){
            int mid = (low + high)/2;

            int studentsCount = countStudents(arr, mid);
            if(studentsCount == noOfStudents){
                // this can be our answer, but we have to find minimum. so move left
                ans = mid;
                high = mid - 1;
            }
            // if condition means we are on the right side of the solution, so we have to move left
            else if(studentsCount < noOfStudents){
                high = mid - 1;
            }
            // else means, no of students among which book has been distributed is greater than or equal to `noOfStudents` - so we have to move right
            else{
                low = mid + 1;
            }
        }
        return ans;
    }

    public static int countStudents(ArrayList<Integer> arr, int pagesAllocationLimit){
        int studentCount = 1, pagesAllocatedToStudent = 0;

        for(int i = 0; i < arr.size(); i++){
            // we check can we allocate current book to current student by checking `pagesAllocationLimit`
            // if we can we allocate the book by adding current book pages to `pagesAllocationLimit`
            if(pagesAllocatedToStudent + arr.get(i) <= pagesAllocationLimit){
                pagesAllocatedToStudent += arr.get(i);
            }
            // else means we cannot allocate, so we have to assign the current book to a new student, so increment `studentCount`
            // and reset `pagesAllocatedToStudent` to arr[i] - because the new student has only arr[i] pages.
            else{
                studentCount++;
                pagesAllocatedToStudent = arr.get(i);
            }
        }
        return studentCount;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(25, 46, 28, 49, 24));
        int n = 5;
        int m = 4;
        System.out.printf("Minimum of maximum number of pages a student can get using brute force approach is: %d\n", findPagesBruteForce(arr, n, m));
        System.out.printf("Minimum of maximum number of pages a student can get using optimal approach is: %d\n",findPagesOptimal(arr, n, m));
    }
}
