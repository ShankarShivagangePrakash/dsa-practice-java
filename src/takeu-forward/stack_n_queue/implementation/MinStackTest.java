package stack_n_queue.implementation;

public class MinStackTest {

    public static void main(String[] args) {

        MinStackBruteForce minStackBruteForce = new MinStackBruteForce();
        minStackBruteForce.push(5);
        minStackBruteForce.push(4);
        System.out.println("MinStackBruteForce - Minimum in stack currently: " + minStackBruteForce.getMin());
        minStackBruteForce.push(3);
        minStackBruteForce.push(2);
        minStackBruteForce.push(1);

        minStackBruteForce.pop();
        System.out.println("MinStackBruteForce - Minimum in stack currently: " + minStackBruteForce.getMin());
        System.out.println("MinStackBruteForce - Popped element from stack: " + minStackBruteForce.top());

        MinStackOptimal minStackOptimal = new MinStackOptimal();
        minStackOptimal.push(5);
        minStackOptimal.push(4);
        System.out.println("MinStackOptimal - Minimum in stack currently: " + minStackOptimal.getMin());
        minStackOptimal.push(3);
        minStackOptimal.push(2);
        minStackOptimal.push(1);

        minStackOptimal.pop();
        System.out.println("MinStackOptimal - Minimum in stack currently: " + minStackOptimal.getMin());
        System.out.println("MinStackOptimal - Popped element from stack: " + minStackOptimal.top());
    }
}
