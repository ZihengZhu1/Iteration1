/**
 * This class handles the closing operation and closing state of the elevator of the elevator doors
 * @author Jiatong Han
 *
 */
public class Closing implements ElevatorState {
    Elevator elevator;
    
    /**
     * Constructor for Closing class
     * @param newElevator
     */
    public Closing(Elevator newElevator){
        elevator=newElevator;
    }


    @Override
    public void openDoor() {

    }

    @Override
    public void closeDoor() {

    }
    
/**
 * This method tells the elevator when the 
 * time is up and has to move to next floor
 */
    @Override
    public void timeIsUp() {
        elevator.setCurrentState(elevator.getEleMove());
        System.out.println("Elevator begin to move to destination");
        elevator.timerStart(1000);//tmp hardcode,later will change to variable depend on speed. For now is 1 sec.
    }

    @Override
    public void receiveRequest()  {
        //edge case: same floor receive request, state change to opening
    }

    @Override
    public void reachFloor(int floor) {

    }
}
