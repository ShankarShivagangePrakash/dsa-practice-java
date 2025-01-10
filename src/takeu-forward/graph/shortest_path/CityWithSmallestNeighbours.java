package graph.shortest_path;

/*
Problem:
    https://takeuforward.org/data-structure/find-the-city-with-the-smallest-number-of-neighbours-at-a-threshold-distance-g-43/
    https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/
 */
public class CityWithSmallestNeighbours {

    /*
    Approach:
        Explained in the notes, check

        Time Complexity: O(V^3)
            we have three nested loops each running for V times, where V = no. of vertices.

        Space Complexity: O(V^2)
            where V = no. of vertices. This space complexity is due to storing the adjacency matrix of the given graph.
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int[][] distance = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];

            distance[u][v] = wt;
            distance[v][u] = wt;
        }

        for(int i = 0; i < n; i++)
            distance[i][i] = 0;

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++){
                    if(distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE)
                        continue;

                    distance[i][j] = Math.min(distance[i][j],
                            distance[i][k] + distance[k][j]);
                }
        }

        int cityNo = -1;
        int maxCount = n;

        for(int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (distance[i][j] <= distanceThreshold)
                    count++;
            }
            if(count <= maxCount){
                maxCount = count;
                cityNo = i;
            }
        }
        return cityNo;
    }
}
