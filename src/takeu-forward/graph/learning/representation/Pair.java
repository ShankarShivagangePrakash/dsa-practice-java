package graph.learning.representation;

public class Pair {

    int edge;
    int weight;

    public Pair(int edge, int weight){
        this.edge = edge;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + edge + ", " + weight + ")";
    }
}
