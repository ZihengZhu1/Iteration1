/**This class is a representation of an elevator
 * @author Jiatong Han
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.*;
import java.net.*;
import data_structure.*;
import data_structure.*;

public class Elevator implements Runnable {
    private Scheduler scheduler;
    private boolean workToBeDone = false;
    ElevatorState idle;
    ElevatorState opening;
    ElevatorState moveEle;
    ElevatorState closing;
    ElevatorState reachFloor;

    ElevatorState currentState;
    private static DatagramPacket receivePacket;
	private static DatagramSocket sendSocket, receiveSocket;
	private final int Id = 2;
	private byte request[] = new byte[40];
	
    /**
     * Constructor for Elevator class
     * @param scheduler
     */
    public Elevator(Scheduler scheduler) {
        idle=new Idle(this);
        closing=new Closing(this);
        opening =new Opening(this);
        moveEle=new MoveEle(this);
        reachFloor=new ReachFloor(this);

        currentState =idle;

        this.scheduler = scheduler;
        scheduler.addElevator(this);
    }


    /**
     * Overload constructor for Elevator class
     */
    public Elevator(){
    	try{
			sendSocket = new DatagramSocket();
			receiveSocket = new DatagramSocket(Id);
		}catch (SocketException se){
			se.printStackTrace();
			System.exit(1);
		}
        idle=new Idle(this);
        closing=new Closing(this);
        opening =new Opening(this);
        moveEle=new MoveEle(this);
        reachFloor=new ReachFloor(this);

        currentState =idle;

    }
    
    private void receviveFromScheduler(){
		
		receivePacket = Floor.waitPacket(receiveSocket, "Elevator NO 2");
		
		byte[] data = receivePacket.getData();
		request = data;
		
		System.out.println("Elevator sending");
		System.out.println("Elevator No 2 send user to floor" + request[3]);
		sendPacket(data, data.length, receivePacket.getAddress(), 23, sendSocket);
		System.out.println("Elevator has sent");
	}
    
    public static void sendPacket(byte[]array, int len, InetAddress destadderss, int port, DatagramSocket socket){
		
		DatagramPacket packet = new DatagramPacket(array, len, destadderss, port);
		
		System.out.println("The Elevator is sending arrival:");
		System.out.println("From host: " + packet.getAddress());
		System.out.println("Destination host port: " + packet.getPort());
		
		try{
			socket.send(packet);
		} catch (IOException ie){
			ie.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Elevator: sent to scheduler");
	}
    
    /**
     * setCurrentState 
     * @param newElevatorState
     */
    void setCurrentState(ElevatorState newElevatorState){
        currentState =newElevatorState;
    }
    
    /**
     * timerStart 
     */
    public void timerStart() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentState.timeIsUp();
    }

    /**
     * openDoor handles the opening of elevator doors
     */
    public void openDoor(){
        currentState.openDoor();
    }
    
    /**
     * closeDoor
     */
    public void closeDoor(){
        currentState.closeDoor();
    }
    
    /**
     * receiveRequest 
     */
    public void receiveRequest(){
        currentState.receiveRequest();
    }
    
    /**
     * reachFloor indicates when the elevator has reached a floor
     * @param floor
     */
    public void reachFloor(int floor){
        currentState.reachFloor(floor);
    }
    
    /**
     * getCurrentState returns the current state of the elevator
     * @return currentState
     */
    public ElevatorState getCurrentState() {
        return currentState;
    }
    
    /**
     * getClosing returns closing state
     * @return closing
     */
    public ElevatorState getClosing() {
        return closing;
    }
    /**
     * getOpenig returns opening state
     * @return opening
     */
    public ElevatorState getOpening() {
        return opening;
    }
    
    /**
     * getEleMove returns moveEle state
     * @return moveEle
     */
    public ElevatorState getEleMove(){
        return moveEle;
    }
    
    /**
     * getIdle returns idle state
     * @return idle
     */
    public ElevatorState getIdle() {
        return idle;
    }

    /**
     * receiveMsg receives message from the scheduler
     * @param requestMsg
     */
    public void receiveMsg(RequestMsg requestMsg) {
        System.out.println("Get message from scheduler");
        System.out.println(requestMsg.getFrom());
        System.out.println(requestMsg.getMovement());
        System.out.println(requestMsg.getDestination());
        report(new RequestMsg(2,2, -1, 3), new ArrivalMessage(3, true));

    }

    /**
     * report reports to the scheduler 
     * @param requestMsg
     * @param arrivalMessage
     */
    private void report(RequestMsg requestMsg, ArrivalMessage arrivalMessage) {
        if (scheduler.arrival(requestMsg.getMovement(), arrivalMessage)) {
            System.out.println("Elevator has arrived floor No." + arrivalMessage.getFloor());
        }
        System.out.println("Report to scheduler");
        System.out.println("Finish request");

    }
    
    /**
     * 
     */
    @Override
    public void run() {

        while (true) {

            scheduler.getRequest();
        }
    }
    
    /**
     * main method 
     */
    public static void main(String[] args) {
        Elevator elevator=new Elevator();
        while(true) {
        	elevator.receviveFromScheduler();
        }
        //elevator.receiveRequest();
    }
}
