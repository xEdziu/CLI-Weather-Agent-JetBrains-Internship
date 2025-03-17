package dev.goral.dataProviders;

import dev.goral.interfaces.WeatherDataProvider;

import java.util.Random;

public class RandomWeatherDataProvider implements WeatherDataProvider {

    private final Random rand = new Random();
    @Override
    public double getTemperature(String location, String timePhrase) {
        return -10 + (45 * rand.nextDouble());
    }
}
