package data_structure;

/**
 * Request message sent from Floor and Elevator
 *
 * @author Qiushuo Man
 */


public class RequestMsg {
    private int from;  // 1 if from floor, 2 if from elevator
    private int movement; // -1 if moving down, 1 if moving up, 0 if stay still
    private int destination;

    public RequestMsg(int from, int movement, int destination){
        this.from = from;
        this.movement = movement;
        this.destination = destination;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getFrom(){return from;}
    public int getMovement(){return  movement;}
    public int getDestination() { return destination; }
}
