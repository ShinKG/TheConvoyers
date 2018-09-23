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
        this.time = t;
        this.Description = d;
    }

    public State getParent(){
        return parent;
    }

    public ArrayList<State> extend(){
        ArrayList<Car> group = new ArrayList<>();
        ArrayList<State> successors= new ArrayList<>();
        int currentWeight = 0; Car currentCar; int slowest;


        for(int i=0 ;i<cars.size() && this.maxWeight < currentWeight ;i++){


            currentCar = cars.remove();
            group.add(currentCar);
            currentWeight += currentCar.getWeight();
            for(i: group){

            }

            successors.add(new State(this.cars,this.maxWeight,this.length,/*time*/,/*desc*/));
        }

        return successors;
    }

}

