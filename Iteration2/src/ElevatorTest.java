import static org.junit.Assert.*;

public class ElevatorTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void closeDoor() {
        Elevator elevator=new Elevator();
        assertEquals(elevator.currentState,elevator.getIdle());
        elevator.receiveRequest();

        assertEquals(elevator.currentState,elevator.getIdle());

    }
}