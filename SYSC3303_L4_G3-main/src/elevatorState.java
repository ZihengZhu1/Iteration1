public interface elevatorState {
    void openDoor();
    void closeDoor();
    void timeIsUp();
    void receiveRequest();

    void reachFloor(int floor);


}
