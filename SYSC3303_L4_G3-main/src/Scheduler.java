import java.util.ArrayList;
import java.util.LinkedList;

import data_structure.*;

public class Scheduler {
    ArrayList<Integer> destiUp = new ArrayList<>();  // list of destinations above the elevator's current position.
    ArrayList<Integer> destiDown = new ArrayList<>(); // list of destinations below the elevator's current position.
    private Elevator elevator;
    private LinkedList<RequestMsg> request =new LinkedList<>();


    public synchronized void handleRequest(RequestMsg msg) {

        while (!request.isEmpty()) {
            try {
                wait(); // wait when there is no destinations to go
            } catch (InterruptedException e) {
                return;
            }
        }

//        if(msg != null){
//            destiUp.add(msg.getDestination());
//        }
//        else if(msg.getMovement() == -1){
//            destiDown.add(msg.getDestination());
//        }
//        else;
        request.add(msg);

        notifyAll();
    }

    public void addElevator(Elevator elevator) {
        this.elevator=elevator;
    }

    public synchronized void getRequest(){
        while(request.isEmpty()){
            try{
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        RequestMsg a=request.pop();
        System.out.println(a);
        elevator.receiveMsg(a);

        notifyAll();
    }

    public synchronized boolean arrival(int upOrDown,ArrivalMessage arrMsg){
        if(arrMsg.isArrived()){
            if(upOrDown==-1){destiDown.remove(arrMsg.getFloor());}
            else if(upOrDown==1){destiUp.remove(arrMsg.getFloor());}
            return true;
        }
        return false;
    }
}
