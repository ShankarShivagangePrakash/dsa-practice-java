package greedy.easy;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/Greedy/shortest-job-first-or-sjf-cpu-scheduling
    https://www.geeksforgeeks.org/problems/shortest-job-first/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=shortest-job-first
 */
public class ShortestJobFirstCPUChedulingImplementation {

    public static int shortestJobFirstSJFOptimal(int[] arr){
        // time elapsed in executing all the process till now.
        int time = 0;
        // time waited to get executed.
        int waitingTime = 0;

        // sort the array, so that we will pick the smaller process
        Arrays.sort(arr);

        for(int i = 0; i < arr.length; i++){
            // waiting time will be time elapsed so far in executing previous processes.
            waitingTime = waitingTime + time;
            time = time + arr[i];

        }
        return (waitingTime/arr.length);
    }
}
