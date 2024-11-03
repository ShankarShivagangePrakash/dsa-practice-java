package greedy.easy;

import java.util.Arrays;

/*
Problem:
    https://takeuforward.org/data-structure/job-sequencing-problem/
    https://www.geeksforgeeks.org/problems/job-sequencing-problem-1587115620/1
 */
public class JobSequencingProblem {

    /*
    Approach:
        Explained in notes in detail, check

        Time Complexity: O(n * logn) + O(2n) + O(n * deadline[i])
            O(n * logn) - for sorting the jobs array
            O(n) for finding the maximum deadline in the jobs array.
            O(n) for filling -1 for each array indices in deadline array

            O(n * deadline[i]) for finding the time slot to execute the job i
                this is equivalent to O(n^2)

        Space Complexity: O(maximum deadline)
            O(maximum deadline)
                extra array with size  `maximum deadline` to keep track of what jobs executed at what time.
     */
    public static int[] jobSequencingProblemOptimal(Job[] jobs){

        // sort array based on profit value in descending order using Comparator
        Arrays.sort(jobs, (a, b) -> ( b.profit - a.profit));

        // get the maximum deadline available.
        int maximumDeadline = -1;
        for(int i = 0; i < jobs.length; i++){
            maximumDeadline = Math.max(maximumDeadline, jobs[i].deadline);
        }

        // create a deadline array with size as maximum deadline + 1
        int[] deadlineArray = new int[maximumDeadline + 1];
        for(int i = 0; i < deadlineArray.length; i++)
            deadlineArray[i] = -1;

        // create total profit and to count number of jobs executed.
        int totalProfit = 0, countJobsExecuted = 0;

        // now iterate through each jobs sorted in descending order of their profits
        for(int i = 0; i < jobs.length; i++){

            // now check for an empty position in deadline array, where index starts from jobs deadline
            // and we keep going back in time till it is greater than 0

            // this inner for loop can be avoided using Graph's Disjoint Set Union which is a concept in Graph series - but not required for interview.
            for(int j = jobs[i].deadline; j > 0; j--){
                // if deadline[j] == -1 means, that time slot is empty and you can execute the job there
                if(deadlineArray[j] == -1){
                    // once the job is executed, mark this position as this time slot was used to execute a particular job
                    // update total profit and count of jobs executed.

                    // stop further iterating for searching time slot, as you have executed this job
                    deadlineArray[j] = jobs[i].id;
                    totalProfit = totalProfit + jobs[i].profit;
                    countJobsExecuted++;
                    break;
                }
            }
        }

        // return count of jobs executed and total profit as a new array
        return new int[]{countJobsExecuted, totalProfit};
    }
}

class Job{
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z;
    }
}
