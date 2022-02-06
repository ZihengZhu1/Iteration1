import data_structure.RequestMsg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testElevatorCommunication() {
        Scheduler scheduler=new Scheduler();
        Elevator elevator=new Elevator(scheduler);
        elevator.receiveMsg(new RequestMsg(1,2,3));
    }
}