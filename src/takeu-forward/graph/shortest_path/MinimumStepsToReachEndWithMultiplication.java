package graph.shortest_path;

import java.util.AbstractMap.SimpleEntry;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Problem:
    https://takeuforward.org/graph/g-39-minimum-multiplications-to-reach-end/
    https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=minimum-multiplications-to-reach-end
 */
public class MinimumStepsToReachEndWithMultiplication {

    int minimumMultiplications(int[] arr, int start, int end) {

        int range = 100000;
        int[] steps = new int[range];
        Arrays.fill(steps, Integer.MAX_VALUE);

        steps[start] = 0;

        // stores info in the form of (steps, node)
        Queue<SimpleEntry<Integer, Integer>> queue = new LinkedList<>();

        queue.add(new SimpleEntry<>(0, start));

        while(!queue.isEmpty()){
            SimpleEntry pair = queue.poll();

            int stepsTaken = (int) pair.getKey();
            int node = (int) pair.getValue();

            for(int i = 0; i < arr.length; i++){
                int newNumber = (arr[i] * node) % range;

                if(stepsTaken + 1 <  steps[newNumber]){
                    steps[newNumber] = stepsTaken + 1;
                    queue.add(new SimpleEntry(steps[newNumber], newNumber));

                    if(newNumber == end)
                        return stepsTaken + 1;
                }
            }
        }
        return -1;
    }
}

