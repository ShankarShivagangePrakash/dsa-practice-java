package arrays;

import java.util.ArrayList;
import java.util.HashSet;

/*
Problem:
    https://takeuforward.org/data-structure/union-of-two-sorted-arrays/
    https://www.geeksforgeeks.org/problems/union-of-two-sorted-arrays-1587115621/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=union-of-two-sorted-arrays
 */
public class UnionOfSortedArrays {

    /*
    Approach:
        Have hash set ( this will be ordered)
        iterate through every element of both arrays.
        Push elements to the set.

        Since set will not store duplicates, after iterating we have one copy of every element of both the arrays. Thus, union achieved.
        Convert that set into array and return.

        Note: This approach works even if inputs given are unsorted arrays.

        Note: Inserting an element to set will be O(log n) - where n is the size of the set.
              If you want to insert `m` elements into the set, then it will be  O( m * log n)

        Time complexity:
            = O(n * log n + m * log m) + O ( m + n)
                where
                    n * log n is the time complexity to insert elements of first array to set.
                    m * log m is the time complexity to insert elements of second array to set.

                    O(m + n) is the time required to create array by iterating through elements of the set.

             But above equation can be deduced to

                 = O((m + n ) log( m + n ))
                    let's understand how, just remember, n * log n + m * log m will be always lesser than (n+m)log(n+m)
                    similarly,  O(m + n) will be lesser than O(n+m)log(n+m)

                    so we can say, O((m + n ) log( m + n ))

          Space complexity:

            = O(m + n)
                  union size, it is required because we need to return the union back, it is not algorithm space complexity.

     */
    public static ArrayList findUnionBruteForce(int[] arr1, int[] arr2, int n, int m) {

        HashSet<Integer> temp = new HashSet<>();

        for(int i : arr1) {
            temp.add(i);
        }

        for(int i : arr2) {
            temp.add(i);
        }

        ArrayList<Integer> union = new ArrayList<>();
        for(int i : temp) {
            union.add(i);
        }
        return union;
    }

    /*
    Approach:
        Take two pointers let’s say i,j pointing to the 0th index of arr1 and arr2.
        Create an empty vector for storing the union of arr1 and arr2.

        Let’s traverse the arr1 and arr2 using pointers i and j and insert the distinct elements found into the union vector.
        While traversing, we may encounter three cases.

        Case - 1:
            arr1[ i ] == arr2[ j ]
            Here we found a common element, so insert only one element in the union. Let’s insert arr[i] in union and increment i.

        Case - 2:
            arr1[ i ]  < arr2[ j ]
            arr1[ i ] < arr2[ j ] so we need to insert arr1[ i ] in union.
            IF last element in  union vector is not equal to arr1[ i ],
            then insert in union else don’t insert.
            After checking Increment i.

        Case - 3:
            arr1[ i ] > arr2[ j ]
            arr1[ i ] > arr2[ j ] so we need to insert arr2[ j ] in union.
            IF the last element in the union vector is not equal to arr2[ j ],
            then insert in the union, else don’t insert. After checking Increment j.

        After traversing if any elements are left in arr1 or arr2 check for condition and insert in the union.

        while implementation, we combine case 1 and case 2 to one condition and case three in else part.

        NOTE:
            There may be cases like the element to be inserted is already present in the union,
            in that case, we are inserting duplicates which is not desired.
            So while inserting always check whether the last element in the union vector is equal or not to the element to be inserted.
            If equal we are trying to insert duplicates, so don’t insert the element, else insert the element in the union.
            This makes sure that we are not inserting any duplicates in the union because we are inserting elements in sorted order.

       Time complexity:
            = O( m + n)
                we iterate both the arrays completely only once, so it is O( m + n)

       Space complexity: O(m + n)
                to store and return union.

            */
    public static ArrayList findUnionOptimal(int[] arr1, int[] arr2, int n, int m){
        int i = 0, j = 0;

        ArrayList<Integer> union = new ArrayList<>();

        // we keep iterating till we reach end of at least one array.
        while( i < n && j < m){
            /* if array 1 element is less than or equal to that of array 2 element.
             check is that element present in the array list, you need not search the entire list since it is sorted, just check last element is equal or not.
             if array list is empty or last element is not equal then inset.
             increment `i` array 1 index.*/
            if(arr1[i] <= arr2[j]){ // Case 1 and 2
                if(union.size() == 0 || union.get(union.size() -1) != arr1[i]){
                    union.add(arr1[i]);
                }
                i++;
            }
            // case 3, array 2 element is lesser, insert it and move `j` which is array 2 index.
            else {
                if(union.size() == 0 || union.get(union.size() -1) != arr2[j]){
                    union.add(arr2[j]);
                }
                j++;
            }
        }

        /* when you come out of above while loop, you might have traversed at least one array completely.
         Now if other array is not traversed completed, you need to traverse and add those elements if it doesn't exist in union array list.
         Note: since at least one array would have traversed completely in the above while loop.
         worst case scenario is either of below while loop will get executed,
         best case, if the size of both arrays are equal, then both the below while loops will not be executed.*/
        while( i < n){
            if(union.size() == 0 || union.get(union.size() -1) != arr1[i]){
                union.add(arr1[i]);
            }
            i++;
        }

        while( j < m){
            if(union.size() == 0 || union.get(union.size() -1) != arr2[j]){
                union.add(arr2[j]);
            }
            j++;
        }

        return union;
    }

    public static void main(String[] args) {
        int n = 10, m = 7;
        int arr1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int arr2[] = {2, 3, 4, 4, 5, 11, 12};

        ArrayList<Integer> Union = findUnionBruteForce(arr1, arr2, n, m);
        System.out.println("Union of arr1 and arr2 using brute force approach:");
        for (int val: Union)
            System.out.print(val + " ");

        ArrayList<Integer> Union2 = findUnionOptimal(arr1, arr2, n, m);
        System.out.println("Union of arr1 and arr2 using optimal approach:");
        for (int val: Union2)
            System.out.print(val + " ");


    }
}
