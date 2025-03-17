package dev.goral;

import dev.goral.dataProviders.RandomWeatherDataProvider;
import dev.goral.interfaces.Response;
import dev.goral.interfaces.WeatherDataProvider;
import dev.goral.responses.ErrorResponse;
import dev.goral.responses.WeatherResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherQueryAgentTest {

    private final WeatherDataProvider provider = new RandomWeatherDataProvider();
    private final WeatherQueryAgent agent = new WeatherQueryAgent(provider);

    @Test
    void testValidQuery() {
        String query = "Determine the temperature in Wroclaw tomorrow";
        Response response = agent.handleQuery(query);

        assertInstanceOf(WeatherResponse.class, response);
        WeatherResponse weatherResponse = (WeatherResponse) response;
        assertEquals("Wroclaw", weatherResponse.getLocation());
        assertEquals("tomorrow", weatherResponse.getTimePhrase());
        assertTrue(weatherResponse.getTemperature() >= -10 && weatherResponse.getTemperature() <= 35);
    }

    @Test
    void testInvalidQuery() {
        String query = "What's the temperature in Wroclaw?";
        Response response = agent.handleQuery(query);

        assertInstanceOf(ErrorResponse.class, response);
        assertEquals(401, response.getErrorCode());
        assertEquals("I'm sorry, I can only handle queries of the form: 'Determine the temperature in [location] in [time]'.",
                response.getErrorMessage());
    }

    @Test
    void testEdgeCases() {
        String query = "Determine the temperature in Łódź now";
        Response response = agent.handleQuery(query);

        assertInstanceOf(WeatherResponse.class, response);
        WeatherResponse weatherResponse = (WeatherResponse) response;
        assertEquals("Łódź", weatherResponse.getLocation());
        assertEquals("now", weatherResponse.getTimePhrase());
    }
}