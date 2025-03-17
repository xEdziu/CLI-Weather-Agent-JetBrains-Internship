package dev.goral;

import dev.goral.dataProviders.RandomWeatherDataProvider;
import dev.goral.interfaces.WeatherDataProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomWeatherDataProviderTest {

    private final WeatherDataProvider provider = new RandomWeatherDataProvider();

    @Test
    void testTemperatureRange() {
        for (int i = 0; i < 100; i++) {
            double temp = provider.getTemperature("Wroclaw", "tomorrow");
            assertTrue(temp >= -10 && temp <= 35, "Temperature out of expected range");
        }
    }
}