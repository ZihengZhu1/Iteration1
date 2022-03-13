/**
 * @author : boshen zhang
 * @version 2.0
 */
import java.io.*;
import java.net.*;

import data_structure.*;

public class Floor implements Runnable {

    private Scheduler scheduler;
    private RequestMsg requestMsg;
    private ArrivalMessage arrivalMessage;
    
    DatagramPacket sendPacket, receivePacket;
    DatagramSocket sendReceiveSocket, receiveSocket;
    
    String SCHEDULER_IP_ADDRESS = "";

    /**
    * Constructor for floor
    * @param scheduler
    */
    public Floor(Scheduler scheduler) {
        this.scheduler = scheduler;
        //this.requestMsg = requestMsg;
        //this.arrivalMessage = arrivalMessage;
    }
    
    public Floor(){
		
		try {
			sendReceiveSocket = new DatagramSocket();
			
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}


    public static byte[] read_event(RequestMsg requestMsg){
        System.out.println("Get message from user, go to " + requestMsg.getDestination() + " floor.");
        byte data[] = new byte[40];
        data[0] = (byte)requestMsg.getFrom();
        data[1] = (byte)requestMsg.getElevatorId();
        data[2] = (byte)requestMsg.getMovement();
        data[3] = (byte)requestMsg.getDestination();
        return data;
    }

    /**
    * send request to scheduler
    * @param requestMsg
    */
    public void floor_send(RequestMsg requestMsg) {
        scheduler.handleRequest(requestMsg);   	
        System.out.println("Report to scheduler");
    }
    
    public void sendAndReceive(byte[] data, int port){		
		try {
			sendPacket(data, data.length, InetAddress.getLocalHost(), 23, sendReceiveSocket);
		}
		catch (UnknownHostException he){
			he.printStackTrace();
			System.exit(1);
		}
	}
    public static void sendPacket(byte[]array, int len, InetAddress destadderss, int port, DatagramSocket socket){
		
		DatagramPacket packet = new DatagramPacket(array, len, destadderss, port);
		System.out.println("The Floor is sending a request:");
		System.out.println("From host: " + packet.getAddress());
		System.out.println("Destination host port: " + packet.getPort());

		try{
			socket.send(packet);
		} catch (IOException ie){
			ie.printStackTrace();
			System.exit(1);
		}
		System.out.println("floor report to scheduler");
	}
    
    public static DatagramPacket waitPacket(DatagramSocket s, String source){
		
		byte data[] = new byte[40];
		
		DatagramPacket receivedPacket = new DatagramPacket(data, data.length);
		System.out.println(source + " is waiting");
		
		try{
			System.out.println("waiting...");
			s.receive(receivedPacket);
		}catch (IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println( source + " has received arrival");
		System.out.println("From host: " + receivedPacket.getAddress());
		System.out.println("Destination host port: " + receivedPacket.getPort());
		return receivedPacket;
	}
    
    public enum FloorState {
        Idle {
            @Override
            public FloorState nextState() {
                return Requesting;
            }

            @Override
            public String currentState() {
                return "Idle";
            }
        },
        Requesting {
            @Override
            public FloorState nextState() {
                return Waiting;
            }

            @Override
            public String currentState() {
                return "Requesting";
            }
        },
        Waiting {
            @Override
            public FloorState nextState() {
                return Waiting;
            }

            @Override
            public String currentState() {
                return "Waiting";
            }
        };
        public abstract FloorState nextState();
        public abstract String currentState();
    }
    
    
    @Override
    public void run(){
    	
    	FloorState state = FloorState.Idle;
        
    	while(true) {
    		
    		while(state.currentState() == "Idle") {
    			requestMsg = new RequestMsg(1,2,1,3);
    			state = state.nextState();
    		}
    		
    		while (state.currentState() == "Requesting") {
    			byte[] msg = read_event(requestMsg);
    			sendAndReceive(msg , 23);
    			state = state.nextState();
    		}
    		while (state.currentState() == "Waiting") {
    			waitPacket(sendReceiveSocket, "Floor");
    			state = state.nextState();
    		}
            arrivalMessage = new ArrivalMessage(3,true);
            if(scheduler.arrival(requestMsg.getMovement(), arrivalMessage)) {
                System.out.println("get there");
            }
        }
    }

}
