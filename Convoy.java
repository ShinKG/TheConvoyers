import java.util.ArrayList;
import java.util.Queue;
public class Convoy {
    public static void main(String[] args){

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
    private String Description;
    private State parent;

    public State(Queue<Car> cs,int mw,int lth,double t,String d){
        this.cars = cs;
        this.maxWeight = mw;
        this.length = lth;
        this.time = t;// this is hour, will be converted by adding 60 when it is printed out
        this.Description = d;
    }

    public State getParent(){
        return parent;
    }

    public ArrayList<State> extend(){
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
            double time += (double)this.length/slow;// length divided by speed is time in h this will be

            successors.add(new State(this.cars,this.maxWeight,this.length,time,des));
            }

            return successors;
        }


    }


