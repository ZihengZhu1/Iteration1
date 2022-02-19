public class Closing implements elevatorState {
    Elevator elevator;
    public Closing(Elevator newElevator){
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
        elevator.setCurrentState(elevator.getEleMove());
        System.out.println("Elevator begin to move to destination");
        elevator.timerStart();
    }

    @Override
    public void receiveRequest()  {

    }

    @Override
    public void reachFloor(int floor) {

    }
}
