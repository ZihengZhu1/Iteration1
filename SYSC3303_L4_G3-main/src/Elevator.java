import data_structure.*;

public class Elevator implements Runnable {
    private Scheduler scheduler;
    private boolean workToBeDone = false;
    elevatorState idle;
    elevatorState opening;
    elevatorState moveEle;
    elevatorState closing;
    elevatorState reachFloor;

    elevatorState currentState;


    public Elevator(Scheduler scheduler) {
        idle=new Idle(this);
        closing=new Closing(this);
        opening =new Opening(this);
        moveEle=new MoveEle(this);
        reachFloor=new ReachFloor(this);

        currentState =idle;


        this.scheduler = scheduler;
        scheduler.addElevator(this);
    }

    public Elevator(){
        idle=new Idle(this);
        closing=new Closing(this);
        opening =new Opening(this);
        moveEle=new MoveEle(this);
        reachFloor=new ReachFloor(this);

        currentState =idle;

    }

    void setCurrentState(elevatorState newElevatorState){
        currentState =newElevatorState;
    }

    public void timerStart() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentState.timeIsUp();
    }


    public void openDoor(){
        currentState.openDoor();
    }

    public void closeDoor(){
        currentState.closeDoor();
    }

    public void receiveRequest(){
        currentState.receiveRequest();
    }

    public void reachFloor(int floor){
        currentState.reachFloor(floor);
    }

    public elevatorState getCurrentState() {
        return currentState;
    }

    public elevatorState getClosing() {
        return closing;
    }

    public elevatorState getOpening() {
        return opening;
    }

    public elevatorState getEleMove(){
        return moveEle;
    }

    public elevatorState getIdle() {
        return idle;
    }


    //Receive message from scheduler
    public void receiveMsg(RequestMsg requestMsg) {
        System.out.println("Get message from scheduler");
        System.out.println(requestMsg.getFrom());
        System.out.println(requestMsg.getMovement());
        System.out.println(requestMsg.getDestination());
        report(new RequestMsg(2, -1, 3), new ArrivalMessage(3, true));

    }

    //Report to scheduler
    private void report(RequestMsg requestMsg, ArrivalMessage arrivalMessage) {
        if (scheduler.arrival(requestMsg.getMovement(), arrivalMessage)) {
            System.out.println("Elevator has arrived floor No." + arrivalMessage.getFloor());
        }
        System.out.println("Report to scheduler");
        System.out.println("Finish request");

    }

    @Override
    public void run() {

        while (true) {

            scheduler.getRequest();
        }
    }

    public static void main(String[] args) {
        Elevator elevator=new Elevator();
        elevator.receiveRequest();
    }
}
