import java.util.ArrayList;
import data_structure.*;

public class Scheduler {
    ArrayList<Integer> destiUp = new ArrayList<>();  // list of destinations above the elevator's current position.
    ArrayList<Integer> destiDown = new ArrayList<>(); // list of destinations below the elevator's current position.
    private Elevator elevator;
    private ArrayList<RequestMsg> request =new ArrayList<>();

    public Scheduler(){
        elevator=new Elevator(this);
        Thread elevThread;
        elevThread=new Thread(elevator);
        elevThread.start();
    }

    public synchronized void handleRequest(RequestMsg msg) {

        while (destiUp.size()==0 && destiDown.size()==0) {
            try {
                wait(); // wait when there is no destinations to go
            } catch (InterruptedException e) {
                return;
            }
        }

        if(msg.getMovement() == 1){
            destiUp.add(msg.getDestination());
        }
        else if(msg.getMovement() == -1){
            destiDown.add(msg.getDestination());
        }
        else;

        notifyAll();
    }

    public synchronized void getRequest(){
        while(request.isEmpty()){
            try{
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        elevator.receiveMsg(request.get(0));
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
