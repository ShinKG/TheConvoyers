import java.util.Queue;
import java.util.ArrayList;

class Car{
	private int speed;
	private int weight;
	
	public Car(int speed, int weight) {
		this.speed=speed;
		this.weight=weight;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public String toString() {
		return("CarInfo-Speed:" + getSpeed() + "Weight:" + getWeight());
	}
	
}

class State {
	private int maxWeight, length;
	private double time;
	private Queue<Car> cars;
	private State parent;
	private String description;
	
	public State(Queue<Car> cars, int maxWeight, int length, double time, String description) {
		this.cars=cars;
		this.maxWeight=maxWeight;
		this.length=length;
		this.time=time;
		this.description=description;
	}

	public State getParent() {
		return parent;
	}

	
	public boolean isGoal() {
		return cars.isEmpty();
	}
	
	
	public ArrayList<State> extend(){
		Queue<Car> group = new LinkedList<>();
		ArrayList<State> successors = new ArrayList<>();
		int currentWeight = 0;
		Car currentCar;
		//int slowest;
		
		for(int i=0; i<cars.size() && maxWeight<currentWeight; i++) {
			currentCar = cars.remove();
			group.add(currentCar);
			currentWeight+=currentCar.getWeight();
			successors.add(new State(cars, maxWeight, length, time, description));
		}
		
		return successors;
	}

}

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class ConvoyDFS {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
    	int weight;
    	int speed;
    	
    	System.out.println("Enter the maximum capacity of the bridge(in tonnes): ");
    	int maxWeight = kbd.nextInt();
    	System.out.println("Enter the length of the bridge(in meters): ");
    	int length = kbd.nextInt();
    	System.out.println("Enter the number of vehicles in the convoy: ");
    	int totalVehicles = kbd.nextInt();
    	Queue<Car> cars = new LinkedList<Car>();
    	
    	for(int i=1; i<=totalVehicles;i++) {
    		System.out.println("Enter the vehicle's maximum speed capacity(in kpm): ");
			speed = kbd.nextInt();
			System.out.print("Enter the vehicle's weight(in tonnes): ");
			weight = kbd.nextInt();
			cars.add(new Car(speed,weight));
    	
        State initialState = new State(cars, maxWeight, length, 0, "Initial State");

        //needs fixing from here :(
        ArrayList<State> frontier = new ArrayList<>();
        frontier.add(initialState);
        
        ArrayList<State> seen = new ArrayList<>();

        int totalStatesVisited = 0;
        int maxFrontierSize = 1;

        while (frontier.size() > 0) {
            State currentState = frontier.remove(frontier.size() - 1);
            
            totalStatesVisited++;

            seen.add(currentState);

            if (currentState.isGoal()) {
                showSolution(currentState, totalStatesVisited, maxFrontierSize);
                return;

            } else {
                ArrayList<State> successorStates = currentState.extend();

                for (State state : successorStates) {
                    if (seen.indexOf(state) == -1) {
                        frontier.add(state);
                    }
                }

                maxFrontierSize = Math.max(maxFrontierSize, frontier.size());
            }
        }

        System.out.println("There is no solution :(");
    }
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