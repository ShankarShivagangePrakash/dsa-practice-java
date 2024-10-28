package stack_n_queue.hard;

import java.util.ArrayList;

/*
Problem:
    https://leetcode.com/problems/online-stock-span/description/
 */
public class StockSpannerBruteforce {

    ArrayList<Integer> arr;

    StockSpannerBruteforce(){
        arr = new ArrayList<>();
    }

    /*
    Approach:
        Explained in notes, check

        Time Complexity: O(n) for each call
        Space Complexity: total number of times method was invoked
            where n - number of days
     */
    public int next(int price){
        arr.add(price);
        int count = 1;

        // since we have initialized count with 1 means, we have considered today
        // let's start from previous day
        for(int i = arr.size()-2; i >=0; i--){
            if(arr.get(i) <= price)
                count++;
            else
                break;
        }
        return count;
    }
}
