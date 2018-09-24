import java.util.*;

class State extends ConvoyUCS{
    private Queue<Vehicle> vehicles;
//    private double maxWeight,length;
    private double time;
    private String Description;
    private State parent;

    public State(Queue<Vehicle> cs, String d){
        this.vehicles = cs;
        this.Description = d;
    }

    public State getParent(){
        return parent;
    }

    public ArrayList<State> extend(){
        ArrayList<Vehicle> group = new ArrayList<>();
        ArrayList<State> successors= new ArrayList<>();
        int currentWeight = 0;
        Vehicle currentCar;
        int slowest;


        for(int i=0 ;i<vehicles.size() && this.bridgeCapacity < currentWeight ;i++){


            currentCar = vehicles.remove();
            group.add(currentCar);
            currentWeight += currentCar.getWeight();
//            for(i: group){
//
//            }

//            successors.add(new State(this.cars,this.maxWeight,this.length,/*time*/,/*desc*/));
        }

        return successors;
    }

}



public class ConvoyUCS {
	public static double bridgeLength;
	public static double bridgeCapacity;
    public static void main(String[] args) {
    	Scanner kbd = new Scanner(System.in);
    	System.out.print("Length of bridge (meters): ");
    	bridgeLength = kbd.nextDouble();
    	System.out.print("Maximum capacity of bridge (tonnes): ");
    	bridgeCapacity = kbd.nextDouble();
    	System.out.print("Number of vehicles in the convoy: ");
    	int numberOfVehicles = kbd.nextInt();
    	
    	Queue<Vehicle> convoy = new LinkedList<Vehicle>();
    	
    	for (int i=1; i<=numberOfVehicles; i++) {
    		System.out.print("weight of vehicle (tonnes) "+i+": ");
    		double weight = kbd.nextDouble();
    		System.out.print("speed of vehicle (meters/minute) "+i+": ");
    		double speed = kbd.nextDouble();
    		Vehicle v = new Vehicle(speed, weight);
    		convoy.add(v);
    	}
    	
    	System.out.println(convoy);

        State initialState = new State(convoy, "Initial State" );

        PriorityQueue<State> frontier = new PriorityQueue<>();
        frontier.add(initialState);
//
        int totalStatesVisited = 0;
        int maxFrontierSize = 1;
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

class Vehicle extends ConvoyUCS{
    private double speed;
    private double weight;
    private double time;
    
    public Vehicle(){
        speed = 100;
        weight = 200;
    }
    
    public Vehicle(double s, double w){
        this.speed = s;
        this.weight = w;
        time = bridgeLength/s;
    }

    public double getSpeed(){
        return this.speed;
    }

    public double getWeight(){
        return this.weight;
    }
    
    public double getTime(){
        return this.time;
    }

    @Override
    public String toString(){
        return("vehicle info - Speed : "+getSpeed()+", Weight : "+getWeight()+", Time : "+time);
    }
}
