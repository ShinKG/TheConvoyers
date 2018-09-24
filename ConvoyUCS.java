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
    	ConvoyUCS convoy = new ConvoyUCS();
    	Scanner kbd = new Scanner(System.in);
    	System.out.print("Length of bridge (kilometers): ");
    	double bridgeLength = kbd.nextDouble();
    	System.out.print("Maximum capacity of bridge (kilograms): ");
    	double bridgeCapacity = kbd.nextDouble();
    	System.out.print("Number of vehicles in the convoy: ");
    	int numberOfVehicles = kbd.nextInt();
    	
    	ArrayList<Vehicle> list = new ArrayList<Vehicle>();
    	
    	for (int i=1; i<=numberOfVehicles; i++) {
    		System.out.print("weight of vehicle "+i+": ");
    		double weight = kbd.nextDouble();
    		System.out.print("speed of vehicle "+i+": ");
    		double speed = kbd.nextDouble();
    		Vehicle v = convoy.new Vehicle(speed, weight);
    		list.add(v);
    	}
    	
    	System.out.println(list);

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


//    public static void showSolution(State state, int totalStatesVisited, int maxFrontierSize) {
//        ArrayList<State> path = new ArrayList<>();
//
//        while (state != null) {
//            path.add(0, state);
//            state = state.getParent();
//        }
//
//        System.out.println("Solution:");
//        for (State st : path) {
//            System.out.println(st);
//        }
//
//        System.out.printf("\nTotal States Visited: %d\n", totalStatesVisited);
//        System.out.printf("Maximum Size of Frontier: %d\n", maxFrontierSize);
//    }
    
    class Vehicle{
        private double speed;
        private double weight;
        
        public Vehicle(){
            speed = 100;
            weight = 200;
        }
        
        public Vehicle(double s, double w){
            this.speed = s;
            this.weight = w;
        }

        public double getSpeed(){
            return this.speed;
        }

        public double getWeight(){
            return this.weight;
        }

        @Override
        public String toString(){
            return("vehicle info - Speed : "+getSpeed()+", Weight : "+getWeight());
        }
    }
}
