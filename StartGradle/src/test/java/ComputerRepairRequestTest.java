import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ro.mpp2025.model.ComputerRepairRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {
    
    @Test
    @DisplayName("First test")
    public void testFirst() {
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest();
        assertEquals("", computerRepairRequest.getOwnerName());
        assertEquals("", computerRepairRequest.getOwnerAddress());
    }

    @Test
    @DisplayName("Second test")
    public void testSecond() {
        ComputerRepairRequest computerRepairRequest = new ComputerRepairRequest();
        computerRepairRequest.setOwnerName("test");
        computerRepairRequest.setOwnerAddress("test");
        assertEquals("test", computerRepairRequest.getOwnerName());
        assertEquals("test", computerRepairRequest.getOwnerAddress());
    }
      
}
