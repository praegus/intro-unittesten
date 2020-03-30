package vakantieplanner.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReserveResponseTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void mappingToXmlWorksAsExpected() throws Exception {
        ReserveResponse reserveResponse = new ReserveResponse();
        reserveResponse.setResult(true);
        String serializedResponse = objectMapper.writeValueAsString(reserveResponse);

        assertTrue(serializedResponse.contains("\"reserveringIsGoedgekeurd\":true"));
        assertTrue(serializedResponse.contains("\"blockingUser\":null"));
    }
}
