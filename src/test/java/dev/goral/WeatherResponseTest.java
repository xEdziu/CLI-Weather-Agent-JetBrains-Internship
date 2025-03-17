package dev.goral;

import dev.goral.responses.WeatherResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherResponseTest {

    @Test
    void testToStringToday() {
        WeatherResponse response = new WeatherResponse("Wroclaw", "today", 20.4);
        assertEquals("It is 20°C in Wroclaw today.", response.toString());
    }

    @Test
    void testToStringTomorrow() {
        WeatherResponse response = new WeatherResponse("Wroclaw", "tomorrow", 18.9);
        assertEquals("It will be 19°C in Wroclaw tomorrow.", response.toString());
    }

    @Test
    void testToStringGenericTime() {
        WeatherResponse response = new WeatherResponse("Wroclaw", "2 hours", 15.3);
        assertEquals("It will be 15°C in Wroclaw in 2 hours.", response.toString());
    }
}
