import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Convoy {
    public void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int maxie = Integer.parseInt(args[0]);
        int lengthie = Integer.parseInt(args[1]);
        int nomie = Integer.parseInt(args[2]);
        Queue<Car> groupie = null;
        boolean stap = false;

        for (; !stap; ) {

            System.out.println("Weight Speed");
            groupie.add(new Car(Integer.parseInt(read.next()), Integer.parseInt(read.next())));
            System.out.println("done(y/n)?");
            if (read.next().equalsIgnoreCase("y")) stap = true;
        }


        State initialState = new State(groupie, maxie, lengthie, 0, "Initial State", null);

        ArrayList<State> seen = new ArrayList<>();

        ArrayList<State> frontier = new ArrayList<>();
        frontier.add(initialState);

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
                ArrayList<State> successorStates = currentState.expand();

                for (State state : successorStates) {
                    if (seen.indexOf(state) == -1) {
                        frontier.add(state);
                    }
                }

                maxFrontierSize = Math.max(maxFrontierSize, frontier.size());
            }
        }

        System.out.println("No Solution.");
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


class Car{
        private int speed;
        private int weight;

        public Car(int s, int w){
            this.speed = s;
            this.weight = w;
        }

        public int getSpeed(){
            return this.speed;
        }

        public int getWeight(){
            return this.weight;
        }

        @Override
        public String toString(){
            return("car info - Speed : "+getSpeed()+", Weight : "+getWeight());
        }
}

class State{
    private Queue<Car> cars;
    private int maxWeight,length;
    private double time;
    private String description;
    private State parent;

    public State(Queue<Car> cs,int mw,int lth,double t,String d,State p){
        this.cars = cs;
        this.maxWeight = mw;
        this.length = lth;
        this.time = t;// this is hour, will be converted by adding 60 when it is printed out
        this.description = d;
        this.parent = p;
    }

    public boolean isGoal(){ return cars.isEmpty();}

    public State getParent(){
        return parent;
    }

    public ArrayList<State> expand(){
        ArrayList<Car> group = new ArrayList<>();
        ArrayList<State> successors= new ArrayList<>();
        int currentWeight = 0; Car currentCar;

        for(;this.maxWeight < currentWeight;){

            currentCar = cars.remove();//a car is out from queue

            //if car is heavier than the bridge can hold , exit and give error message
            if(currentCar.getWeight()>maxWeight){
                System.out.println("there is a Car heavier than maximum, No solution is available");
                System.exit(0);
            }

            group.add(currentCar);//car joined on the group
            String des = "Following cars are joined group : "+currentCar.toString()+ " ";
            currentWeight += currentCar.getWeight();//weight of group is measured

            //finder of slower
            int slow = 0;
            for(int i=0;i<group.size();i++){
                if (group.get(i).getSpeed()<slow) slow = group.get(i).getSpeed();
            }

            //preprcss
            double timetook = time;
            timetook += (double)this.length/slow;// length divided by speed is time in h this will be

            successors.add(new State(this.cars,this.maxWeight,this.length,timetook,des,this));
            }

            return successors;
        }


    }


