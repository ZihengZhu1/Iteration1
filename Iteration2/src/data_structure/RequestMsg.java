package data_structure;

/**
 * Request message format sent from Floor and Elevator
 *
 * @author Boshen Zhang
 */


public class RequestMsg {
    private int from;  // from which floor
    private int elevatorId;
    private int movement; // -1 if moving down, 1 if moving up, 0 if stay still
    private int destination;

    public RequestMsg(int from, int elevatorId, int movement, int destination){
        this.from = from;
        this.elevatorId = elevatorId;
        this.movement = movement;
        this.destination = destination;
    }

    public void setFrom(int from) {
        this.from = from;
    }
    
    public void setElevatorId(int elevatorId) {
		this.elevatorId = elevatorId;
	}

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getFrom(){
    	return from;}
    
    public int getElevatorId() {
		return elevatorId;
	}
    public int getMovement(){return  movement;}
    public int getDestination() { return destination; }

	

}