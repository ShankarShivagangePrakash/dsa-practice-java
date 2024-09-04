package arrays.binarysearch.onanswers.minmax;

import java.util.PriorityQueue;
/*
Problem:
    https://takeuforward.org/arrays/minimise-maximum-distance-between-gas-stations/
    https://www.naukri.com/code360/problems/minimise-max-distance_7541449?utm_source=striver&utm_medium=website&utm_campaign=codestudio_a_zcourse
 */
public class PlaceGasStations {

    //have made the class static because, i need to create object of this class from static method.
    static class Pair{
        // first refers to distance section length
        double first;
        // second represents the index at which section length is occuring.
        int second;

         Pair(double first, int second){
            this.first = first;
            this.second = second;
        }
    }

    /*
    Approach:
        First, we will declare an array ‘howMany[]’ of size n-1, to keep track of the number of placed gas stations.
        Next, using a loop we will pick k gas stations one at a time.
        Then, using another loop, we will find the index 'i' where the distance (arr[i+1] - arr[i]) is the maximum and insert the current gas station between arr[i] and arr[i+1]
         (i.e. howMany[i]++).
        Finally, after placing all the new stations, we will find the distance between two consecutive gas stations. For a particular section,
        distance = section_length / (number_of_stations_ inserted+1)
            = (arr[i+1]-arr[i]) / (howMany[i]+1)
        Among all the distances, the maximum one will be the answer.

        Time Complexity: O(k*n) + O(n),
            n = size of the given array, k = no. of gas stations to be placed.
            Reason: O(k*n) to insert k gas stations between the existing stations with maximum distance. Another O(n) for finding the answer i.e. the maximum distance.

        Space Complexity: O(n-1) as we are using an array to keep track of placed gas stations.
     */
    public static double minimiseMaxDistanceBruteForce(int[] arr, int k){
        int n = arr.length;
        int[] howMany = new int[n-1];

        // pick each new gas stations,
        // check the index which has maximum distanced with adjacent gas station and insert the gas station there
        // keep doing the same process for all new gas stations we have to insert.
        for(int i = 0; i < k; i++){
            double sectionMaximumDistance = -1;
            int maximumDistanceSectionIndex = -1;
            for(int j = 0; j < n-1; j++){
                // distance between current and next gas station in original array(not considering the gas station we have inserted)
                double numerator = arr[i+1] - arr[i];
                // check, how many sections have formed between them by inserting new gas stations.
                double denominator = (howMany[i] + 1);
                // then find new section length
                double sectionLength = numerator/denominator;

                if(sectionLength > sectionMaximumDistance){
                    sectionMaximumDistance = sectionLength;
                    maximumDistanceSectionIndex = i;
                }
            }
            // we have found the index which has the maximum distance with adjacent gas station, so we have to insert new gas station there.
            howMany[maximumDistanceSectionIndex]++;
        }

        // now we have inserted the gas station, but we have to find the maximum distance between any two adjacent gas stations after inserting new gas stations
        double maxDistance = -1;
        for(int i = 0; i < n-1; i++){
            // you have to consider the distance between two adjacent gas stations(don't include the gas stations you have inserted)
            double numerator = arr[i+1] - arr[i];
            // no of sections formed, by your new gas stations insertion.
            double denominator = howMany[i]+1;
            double distance = numerator/denominator;
            if(distance > maxDistance)
                maxDistance = distance;
        }
        return maxDistance;
    }

    /*
    Approach:
        Algorithm:
            First, we will declare an array ‘howMany[]’ of size n-1, to keep track of the number of placed gas stations and a priority queue that uses max heap.
            We will insert the first n-1 indices with the respective distance value, arrr[i+1]-arr[i] for every index.
            Next, using a loop we will pick k gas stations one at a time.
            Then we will pick the first element of the priority queue as this is the element with the maximum distance. Let’s call the index ‘secInd’.
            Now we will place the current gas station at ‘secInd’(howMany[secInd]++) and calculate the new section length,
            new_section_length = initial_section_length / (number_of_stations_ inserted+1)
                        = (arr[secInd+1] - arr[secInd]) / (howMany[i] + 1)
            After that, we will again insert the pair <new_section_length, secInd> into the priority queue for further consideration.
            After performing all the steps for k gas stations, the distance at the top of the priority queue will be the answer as we want the maximum distance.

        Time Complexity: O(n *log n + k * log n),
            n = size of the given array, k = no. of gas stations to be placed.
            Reason: Insert operation of priority queue takes log n time complexity.
            O(n* log n) for inserting all the indices with distance values and O(k * log n) for placing the gas stations.

        Space Complexity: O(n-1)+O(n-1)
            Reason: The first O(n-1) is for the array to keep track of placed gas stations and the second one is for the priority queue.
     */
    public static double minimiseMaxDistanceBetter(int[] arr, int k){
        int n = arr.length;
        int[] howMany = new int[n-1];

        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((a, b)-> Double.compare(b.first, a.first));

        // insert first n-1 elements and their adjacent distance to priorityQueue
        for(int i = 0; i < n-1; i++){
            double distance = arr[i+1] - arr[i];
            priorityQueue.add(new Pair(distance, i));
        }

        for(int i = 0; i < k; i++){
            // get the top element from the priorityQueue - which will have maximum section length
            Pair p = priorityQueue.poll();

            int maximumDistanceSectionIndex = p.second;

            // we know the index at which we need to insert new gas station. i.e. `maximumDistanceSectionIndex`
            howMany[maximumDistanceSectionIndex]++;

            // now calculate the new section length
            // actual original distance between the original new gas stations.
            double numerator = arr[maximumDistanceSectionIndex + 1] - arr[maximumDistanceSectionIndex];
            double denominator = howMany[maximumDistanceSectionIndex] + 1;

            double newSectionLength = numerator/denominator;

            // insert this new section length and the current index as an entry to priority Queue
            priorityQueue.add(new Pair(newSectionLength, maximumDistanceSectionIndex));
        }
        // top element of priority queue will have maximum distanced.
        return priorityQueue.peek().first;
    }

    /*
    Approach:
        First, we will find the maximum distance between two consecutive gas stations i.e. max(dist).

        Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to 0 and the high will point to max(dist).
        Now, we will use the ‘while’ loop like this: while(high - low > 10^(-6)).

        Calculate the ‘mid’: Now, inside the loop, we will calculate the value of ‘mid’ using the following formula:
        mid = (low+high) / 2.0

        Eliminate the halves based on the number of stations returned by numberOfGasStationsRequired():
        We will pass the potential value of ‘dist’, represented by the variable 'mid', to the ‘numberOfGasStationsRequired()' function.
        This function will return the number of gas stations we can place.

        If result > k: On satisfying this condition, we can conclude that the number ‘mid’ is smaller than our answer.
        So, we will eliminate the left half and consider the right half(i.e. low = mid).

        Otherwise, the value mid is one of the possible answers. But we want the minimum value.
        So, we will eliminate the right half and consider the left half(i.e. high = mid).

        Finally, outside the loop, we can return either low or high as their difference is beyond 10^(-6).
        They both can be the possible answer. Here, we have returned the ‘high’.

        The steps from 4-5 will be inside a loop and the loop will continue until (low-high <= 10^(-6)).

        Time Complexity: O(n*log(n)) + O(n),
            n = size of the given array, Len = length of the answer space.
            Reason: We are applying binary search on the answer space. For every possible answer - O(log n)
            we are calling the function numberOfGasStationsRequired() that takes O(n) time complexity. = O(n * log n)
            And another O(n) for finding the maximum distance initially.

        Space Complexity: O(1)
     */
    public static double minimiseMaxDistanceOptimal(int[] arr, int k){
        int n = arr.length;
        double low = 0;
        double high = getMaxDistance(arr);

        double diff = 1e-6;

        while(high-low > diff){
            double mid = (low + high)/2.0;

            int count = numberOfGasStationsWeCanPlaceWithDistance(arr, mid);

            // we are on the left side of the answer, move right
            if(count > k){
                low = mid;
            }
            // else means we are on the right side or on the answer, this can be one possible answer
            // but we to find minimum answer, so move left
            else{
                // ans = mid;
                high = mid;
            }
        }
        // you know the trick, print both high and low, whichever matches with high or low, return it
        // if nothing matches keep a variable `ans` to update it inside while() loop.
        return high;
    }

    public static double getMaxDistance(int[] arr){
        double maxDistance = -1;
        for(int i = 0; i < arr.length - 1; i++){
            double difference = (double)arr[i+1] - arr[i];
            if( difference > maxDistance){
                maxDistance = difference;
            }
        }
        return maxDistance;
    }

    public static int numberOfGasStationsWeCanPlaceWithDistance(int[] arr, double distance){
        int n = arr.length;

        int noOfGasStationsWeCanPlace = 0;

        for(int i = 0; i < n-1; i++){
            // actual distance between two adjacent gas stations in original array
            double numerator = arr[i+1] - arr[i];
            // here denominator is the section length we have to maintain
            // when we divide numerator and denominator we will get number of gas stations we have to place
            int noOfGasStations = (int)( numerator/distance);

            // handle edge case, mentioned in notes, check
            if(numerator == (distance * noOfGasStations))
                noOfGasStations--;

            noOfGasStationsWeCanPlace += noOfGasStations;
        }
        return noOfGasStationsWeCanPlace;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        System.out.printf("Maximum distance between the gas stations after inserting new %d gas stations using brute force approach is: %f\n", k, minimiseMaxDistanceBruteForce(arr, k));
        System.out.printf("Maximum distance between the gas stations after inserting new %d gas stations using Better Approach (explain this approach in interview) is: %f\n", k, minimiseMaxDistanceBetter(arr, k));
        System.out.printf("Maximum distance between the gas stations after inserting new %d gas stations using optimal approach is: %f\n", k, minimiseMaxDistanceOptimal(arr, k));
    }
}
