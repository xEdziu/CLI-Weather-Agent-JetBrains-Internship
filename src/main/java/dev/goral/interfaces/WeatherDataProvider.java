package dev.goral.interfaces;

public interface WeatherDataProvider {
    /**
     * Get the temperature for a given location and time phrase.
     *
     * @param location   the location to get the temperature for
     * @param timePhrase the time phrase to get the temperature for
     * @return the temperature in Celsius
     */
    double getTemperature(String location, String timePhrase);
}
