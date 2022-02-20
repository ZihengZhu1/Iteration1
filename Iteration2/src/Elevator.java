/**This class is a representation of an elevator
 * @author Jiatong Han
 */
import data_structure.*;

public class Elevator implements Runnable {
    private Scheduler scheduler;
    private boolean workToBeDone = false;
    ElevatorState idle;
    ElevatorState opening;
    ElevatorState moveEle;
    ElevatorState closing;
    ElevatorState reachFloor;

    ElevatorState currentState;

    /**
     * Constructor for Elevator class
     * @param scheduler
     */
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
    
    /**
     * Overload constructor for Elevator class
     */
    public Elevator(){
        idle=new Idle(this);
        closing=new Closing(this);
        opening =new Opening(this);
        moveEle=new MoveEle(this);
        reachFloor=new ReachFloor(this);

        currentState =idle;

    }
    
    /**
     * setCurrentState 
     * @param newElevatorState
     */
    void setCurrentState(ElevatorState newElevatorState){
        currentState =newElevatorState;
    }
    
    /**
     * timerStart 
     */
    public void timerStart() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentState.timeIsUp();
    }

    /**
     * openDoor handles the opening of elevator doors
     */
    public void openDoor(){
        currentState.openDoor();
    }
    
    /**
     * closeDoor
     */
    public void closeDoor(){
        currentState.closeDoor();
    }
    
    /**
     * receiveRequest 
     */
    public void receiveRequest(){
        currentState.receiveRequest();
    }
    
    /**
     * reachFloor indicates when the elevator has reached a floor
     * @param floor
     */
    public void reachFloor(int floor){
        currentState.reachFloor(floor);
    }
    
    /**
     * getCurrentState returns the current state of the elevator
     * @return currentState
     */
    public ElevatorState getCurrentState() {
        return currentState;
    }
    
    /**
     * getClosing returns closing state
     * @return closing
     */
    public ElevatorState getClosing() {
        return closing;
    }
    /**
     * getOpenig returns opening state
     * @return opening
     */
    public ElevatorState getOpening() {
        return opening;
    }
    
    /**
     * getEleMove returns moveEle state
     * @return moveEle
     */
    public ElevatorState getEleMove(){
        return moveEle;
    }
    
    /**
     * getIdle returns idle state
     * @return idle
     */
    public ElevatorState getIdle() {
        return idle;
    }

    /**
     * receiveMsg receives message from the scheduler
     * @param requestMsg
     */
    public void receiveMsg(RequestMsg requestMsg) {
        System.out.println("Get message from scheduler");
        System.out.println(requestMsg.getFrom());
        System.out.println(requestMsg.getMovement());
        System.out.println(requestMsg.getDestination());
        report(new RequestMsg(2, -1, 3), new ArrivalMessage(3, true));

    }

    /**
     * report reports to the scheduler 
     * @param requestMsg
     * @param arrivalMessage
     */
    private void report(RequestMsg requestMsg, ArrivalMessage arrivalMessage) {
        if (scheduler.arrival(requestMsg.getMovement(), arrivalMessage)) {
            System.out.println("Elevator has arrived floor No." + arrivalMessage.getFloor());
        }
        System.out.println("Report to scheduler");
        System.out.println("Finish request");

    }
    
    /**
     * 
     */
    @Override
    public void run() {

        while (true) {

            scheduler.getRequest();
        }
    }
    
    /**
     * main method 
     */
    public static void main(String[] args) {
        Elevator elevator=new Elevator();
        elevator.receiveRequest();
    }
}
