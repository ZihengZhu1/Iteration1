public class Idle implements elevatorState{
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
        elevator.setCurrentState(elevator.getClosing());
        System.out.println("Elevator is closing the door");
        elevator.timerStart();
    }

    @Override
    public void reachFloor(int floor) {

    }

}
