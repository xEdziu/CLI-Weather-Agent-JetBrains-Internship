package dev.goral;

import dev.goral.interfaces.Response;
import dev.goral.interfaces.Tool;
import dev.goral.interfaces.ToolSelector;
import dev.goral.responses.ErrorResponse;
import dev.goral.responses.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherQueryAgentTest {

    private ToolSelector toolSelector;
    private Tool monitoringTool;
    private Tool historicalTool;
    private WeatherQueryAgent agent;

    @BeforeEach
    void setup() {
        toolSelector = mock(ToolSelector.class);
        agent = new WeatherQueryAgent(toolSelector);
    }

    @Test
    void testHandleFutureQuery_UsesMonitoringTool() {
        // Given
        Tool monitoringTool = mock(Tool.class);
        when(toolSelector.select("tomorrow")).thenReturn(monitoringTool);
        when(monitoringTool.getTemperature("London", "tomorrow")).thenReturn(15.5);

        // When
        Response response = agent.handleQuery("Determine the temperature in London tomorrow");

        // Then
        assertInstanceOf(WeatherResponse.class, response);
        WeatherResponse weatherResponse = (WeatherResponse) response;
        assertEquals("London", weatherResponse.getLocation());
        assertEquals("tomorrow", weatherResponse.getTimePhrase());
        assertEquals(15.5, weatherResponse.getTemperature());

        verify(toolSelector).select("tomorrow");
        verify(monitoringTool).getTemperature("London", "tomorrow");
    }

    @Test
    void testHandlePastQueryUsesHistoricalTool() {
        // Given
        Tool historicalTool = mock(Tool.class);
        when(toolSelector.select("yesterday")).thenReturn(historicalTool);
        when(historicalTool.getTemperature("Warsaw", "yesterday")).thenReturn(10.0);

        // When
        Response response = agent.handleQuery("Determine the temperature in Warsaw yesterday");

        // Then
        assertInstanceOf(WeatherResponse.class, response);
        WeatherResponse weatherResponse = (WeatherResponse) response;
        assertEquals("Warsaw", weatherResponse.getLocation());
        assertEquals("yesterday", weatherResponse.getTimePhrase());
        assertEquals(10.0, weatherResponse.getTemperature());

        verify(toolSelector).select("yesterday");
        verify(historicalTool).getTemperature("Warsaw", "yesterday");
    }

    @Test
    void testHandleInvalidQueryReturnsErrorResponse() {
        // When
        Response response = agent.handleQuery("What's the temperature?");

        // Then
        assertInstanceOf(ErrorResponse.class, response);
        assertEquals(401, response.getErrorCode());
        assertEquals("I'm sorry, I can only handle queries of the form: 'Determine the temperature in [location] in [time]'.",
                response.getErrorMessage());

        verifyNoInteractions(toolSelector);
    }
}
