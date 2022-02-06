import data_structure.RequestMsg;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @Test
    void testFloorCommunication() {
        Scheduler scheduler=new Scheduler();
        Floor floor =new Floor(scheduler);
        floor.read_event(new RequestMsg(1,2,3));
        floor.floor_send(new RequestMsg(1,2,3));
    }

}