public class MoveEle implements ElevatorState {
    Elevator elevator;
    public MoveEle(Elevator newElevator){
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
        elevator.setCurrentState(elevator.getOpening());
        System.out.println("Elevator is opening the door");
        elevator.timerStart();
    }

    @Override
    public void receiveRequest() {

    }

    @Override
    public void reachFloor(int floor) {

    }
}