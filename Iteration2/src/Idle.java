public class Idle implements ElevatorState{
    Elevator elevator;
    public Idle(Elevator newElevator){
        elevator=newElevator;
    }

    @Override
    public void openDoor() {

    }

    @Override
    public void closeDoor() {

    }

    @Override
    public void timeIsUp() {

    }

    @Override
    public void receiveRequest() {
        System.out.println("Current state is idle");
        System.out.println("Received request");
        elevator.setCurrentState(elevator.getClosing());
        System.out.println("Elevator is closing the door");
        System.out.println("Timer started");
        elevator.timerStart(1000);

    }

    @Override
    public void reachFloor(int floor) {

    }

}
