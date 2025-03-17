package dev.goral.responses;

import dev.goral.interfaces.Response;

public class WeatherResponse implements Response {
    private String location;
    private String timePhrase;
    private double temperature;

    public WeatherResponse(String location, String timePhrase, double temperature) {
        this.location = location;
        this.timePhrase = timePhrase;
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public String getTimePhrase() {
        return timePhrase;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTimePhrase(String timePhrase) {
        this.timePhrase = timePhrase;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        int tempRounded = (int)Math.round(temperature);
        if (timePhrase.equalsIgnoreCase("now")
                || timePhrase.equalsIgnoreCase("today")) {

            return String.format("It is %d°C in %s %s.", tempRounded, location, timePhrase);
        } else if (timePhrase.equalsIgnoreCase("tomorrow")){
            return String.format("It will be %d°C in %s %s.", tempRounded, location, timePhrase);
        }
        return String.format("It will be %d°C in %s in %s.", tempRounded, location, timePhrase);
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }
}
