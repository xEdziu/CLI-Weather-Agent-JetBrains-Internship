package dev.goral.tools;

import dev.goral.interfaces.Tool;

import java.util.Random;

public class MonitoringTool implements Tool {
    private final Random random = new Random();

    @Override
    public double getTemperature(String location, String timePhrase) {
        // Simulation of random actual or future temperature values
        return -5 + (35 * random.nextDouble());
    }
}
