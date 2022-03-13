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
        if (elevator.getCurrentDirection()==Elevator.STILL){
            System.out.println("ERROR");
        }
        if(elevator.getCurrentDirection()==Elevator.UP){
            elevator.increaseHeight();
        }
        if(elevator.getCurrentDirection()==Elevator.DOWN){
            elevator.decreaseHeight();
        }

    }

    @Override
    public void receiveRequest() {

    }

    @Override
    public void reachFloor(int floor) {
       elevator.isDestination();
    }
}
