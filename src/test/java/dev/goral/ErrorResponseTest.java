package dev.goral;
import dev.goral.responses.ErrorResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse("Invalid query", 400);
        assertEquals("Invalid query", errorResponse.getErrorMessage());
        assertEquals(400, errorResponse.getErrorCode());
    }
}
