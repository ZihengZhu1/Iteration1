import data_structure.*;

public class Elevator implements Runnable{
    private Scheduler scheduler;
    private boolean workToBeDone=false;

    public Elevator(Scheduler scheduler){
        this.scheduler=scheduler;
    }

    //Receive message from scheduler
    public void receiveMsg(RequestMsg requestMsg){
        System.out.println("Get message from scheduler");
        System.out.println(requestMsg.getFrom());
        System.out.println(requestMsg.getMovement());
        System.out.println(requestMsg.getDestination());
        report(new RequestMsg(2,-1,3), new ArrivalMessage(3,true));
    }

    //Report to scheduler
    private void report(RequestMsg requestMsg, ArrivalMessage arrivalMessage) {
        scheduler.arrival(requestMsg.getMovement(), arrivalMessage);
        System.out.println("Report to scheduler");
        System.out.println("Finish request");

    }

    @Override
    public void run() {
        while (true)
        scheduler.getRequest();

    }
}
