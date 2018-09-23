import java.util.*;

class State implements Comparable<State> {
    private int a, b, c, cost, depth;
    private State parent;
    private String description;
    
    public State(int a, int b, int c, int cost, int depth, State parent, String description) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.cost = cost;
        this.depth = depth;
        this.parent = parent;
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public State getParent() {
        return parent;
    }

    public boolean isGoal() {
        return a == 0 || b == 0 || c == 0;
    }

    public ArrayList<State> expand() {
        ArrayList<State> successors = new ArrayList<>();

        if (a >= b) {
            successors.add(new State(a - b, 2 * b, c, cost + b, depth + 1, this, "A -> B"));
        }

        if (a >= c) {
            successors.add(new State(a - c, b, 2 * c, cost + c, depth + 1, this, "A -> C"));
        }

        if (b >= a) {
            successors.add(new State(2 * a, b - a, c, cost + a, depth + 1, this, "B -> A"));
        }

        if (b >= c) {
            successors.add(new State(a, b - c, 2 * c, cost + c, depth + 1, this, "B-> C"));
        }

        if (c >= a) {
            successors.add(new State(2 * a, b, c - a, cost + a, depth + 1, this, "C -> A"));
        }

        if (c >= b) {
            successors.add(new State(a, 2 * b, c - b, cost + b, depth + 1, this, "C -> B"));
        }

        return successors;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %d: %s [%d, %d]", a, b, c, description, cost, depth);
    }

    public int compareTo(State other) {
        return this.getCost() - other.getCost();
    }
}



public class ConvoyUCS {
    public static void main(String[] args) {
    	Queue<Object> queue = new LinkedList<>(Arrays.asList(args));
    	double capacity = Double.parseDouble(args[0]);
    	queue.remove(); //removes the inputed capacity from the queue
//    	for (int i=1; i< args.length; i++) {
//    		System.out.println(queue.peek());
//    		queue.remove();
//    	}
    	
    	System.out.print("Maximum capacity of bridge: "+capacity);

//        State initialState = new State(a, b, c, 0, 0, null, "Initial State");
//
//        PriorityQueue<State> frontier = new PriorityQueue<>();
//        frontier.add(initialState);
//
//        int totalStatesVisited = 0;
//        int maxFrontierSize = 1;
//
//        while (frontier.size() > 0) {
//            State currentState = frontier.remove();
//
//            totalStatesVisited++;
//
//            if (currentState.isGoal()) {
//                showSolution(currentState, totalStatesVisited, maxFrontierSize);
//                return;
//
//            } else {
//                ArrayList<State> successorStates = currentState.expand();
//                frontier.addAll(successorStates);
//                
//                maxFrontierSize = Math.max(maxFrontierSize, frontier.size());
//            }
//        }
//
//        System.out.println("No Solution.");
    }


    public static void showSolution(State state, int totalStatesVisited, int maxFrontierSize) {
        ArrayList<State> path = new ArrayList<>();

        while (state != null) {
            path.add(0, state);
            state = state.getParent();
        }

        System.out.println("Solution:");
        for (State st : path) {
            System.out.println(st);
        }

        System.out.printf("\nTotal States Visited: %d\n", totalStatesVisited);
        System.out.printf("Maximum Size of Frontier: %d\n", maxFrontierSize);
    }
}
