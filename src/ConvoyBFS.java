import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

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
		int slowest;
		
		for(int i=0; i<cars.size() && maxWeight<currentWeight; i++) {
			currentCar = cars.remove();
			group.add(currentCar);
			currentWeight+=currentCar.getWeight();
			successors.add(new State(cars, maxWeight, length, time, description));
		}
		
		
		return successors;
	}

}


public class ConvoyBFS {
	
	
	public static void main(String[] args) {
		Scanner kbd = new Scanner(System.in);
		int speed;
		int weight;
		System.out.println("Enter the maximum capacity of the brigde, length of the bridge and number or vehicles in the convoy: ");
		int maxWeight = kbd.nextInt();
		int length = kbd.nextInt();
		int totalVehicles = kbd.nextInt();
		Queue<Car> cars = new LinkedList<Car>();
		
		
		for(int i=1; i<=totalVehicles;i++) {
			System.out.println("Enter vehicle speed and weight for vehicle " + i );
			speed = kbd.nextInt();
			weight = kbd.nextInt();
			cars.add(new Car(speed,weight));	
		}
		
		State initialState = new State(cars, maxWeight, length, 0, "Initial State");
		
		ArrayList<State> frontier = new ArrayList<>();
		frontier.add(initialState);
		
		int totalStatesVisited = 0;
		int maxFrontierSize = 1;
		
		
		while(frontier.size() > 0) {
			State currentState = frontier.remove(0);
			
			totalStatesVisited++;
			
			if(currentState.isGoal()) {
				showSolution(currentState, totalStatesVisited, maxFrontierSize);
				return;
				
			}else {
				ArrayList<State> successorStates = currentState.extend();
				frontier.addAll(successorStates);
				
				maxFrontierSize = Math.max(maxFrontierSize, frontier.size());
				
			}
		}
		
		System.out.println("No Solution");
		
		

	}
	
	public static void showSolution(State state, int totalStatesVisited, int maxFrontierSize) {
		ArrayList<State> path = new ArrayList<>();
		
		while(state != null) {
			path.add(0, state);
			state = state.getParent();
		}
		
		System.out.println("Solution: ");
		for(State st : path) {
			System.out.println(st);
			
		}
		
		System.out.printf("\nTotal States Visited: %d\n", totalStatesVisited);
		System.out.printf("Maximum Size of Frontier: %d\n", maxFrontierSize);
	}

}
