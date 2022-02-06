import data_structure.*;

public class Floor implements Runnable {
	
	private Scheduler scheduler;
	private RequestMsg requestMsg;
	private ArrivalMessage arrivalMessage;

	public Floor(Scheduler scheduler) {
		this.scheduler = scheduler;
		//this.requestMsg = requestMsg;
		//this.arrivalMessage = arrivalMessage;
	}
	
	//read case/events from user
	public void read_event(RequestMsg requestMsg){
		this.requestMsg = requestMsg;
        System.out.println("Get message from user, go to " + requestMsg.getDestination() + "floor.");
    }
	
	// send to scheduler
	private void floor_send(RequestMsg requestMsg) {
        scheduler.handleRequest(requestMsg);
        System.out.println("Report to scheduler");
    }
	 @Override
	public void run(){
		 while(true) {
			 read_event(requestMsg);
			 floor_send(requestMsg);
			 if(scheduler.arrival(requestMsg.getMovement(), arrivalMessage)) {
				 System.out.println("get there");
			 }
	     }
	}	
	
}
