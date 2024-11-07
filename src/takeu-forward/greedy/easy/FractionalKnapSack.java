package greedy.easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Problem:
    https://takeuforward.org/data-structure/fractional-knapsack-problem-greedy-approach/
    https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
 */
public class FractionalKnapSack {

    public static double fractionalKnapsack(List<Integer> val, List<Integer> wt, int capacity) {
        int n = val.size();
        Item[] arr = new Item[n];

        // store the values in two separate array to one single Item object array.
        for(int i = 0; i < n; i++){
            arr[i] = new Item(val.get(i), wt.get(i));
        }

        // sort the values in decreasing order of value/weight
        Arrays.sort(arr, new ItemComparator());

        int currentCapacity = 0;
        double finalValue = 0;
        for(int i = 0; i < n; i++){
            // if the current bag's weight can be completely added add its full value to finalValue
            if(currentCapacity + arr[i].weight <= capacity){
                currentCapacity = currentCapacity + arr[i].weight;
                finalValue = finalValue + arr[i].value;
            }
            else{
                // we cannot add current bag's full weight, we can add fraction of it, how much we can add means (value /weight) * remaining weight
                int remainingWeight = capacity - currentCapacity;
                finalValue = finalValue + ( (double) arr[i].value/ (double) arr[i].weight * (double) remainingWeight);
                break;
            }
        }
        return finalValue;
    }
}

class Item{
    int value;
    int weight;

    Item(int value, int weight){
        this.value = value;
        this.weight = weight;
    }
}

class ItemComparator implements Comparator<Item> {
    // Sorting in decreasing order of value/weight ratio
    @Override
    public int compare(Item a, Item b) {
        double r1 = (double) a.value / a.weight;
        double r2 = (double) b.value / b.weight;
        if (r1 > r2) return -1;
        else if (r1 < r2) return 1;
        return 0;
    }
}
