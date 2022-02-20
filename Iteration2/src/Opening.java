public class Opening implements ElevatorState {
    Elevator elevator;
    public Opening(Elevator newElevator){
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
        elevator.setCurrentState(elevator.getIdle());
        System.out.println("Elevator back to idle");
        elevator.timerStart();
    }

    @Override
    public void receiveRequest() {

    }

    @Override
    public void reachFloor(int floor) {

    }
}