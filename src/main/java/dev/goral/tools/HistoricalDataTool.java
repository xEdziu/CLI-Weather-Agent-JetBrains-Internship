package dev.goral.tools;

import dev.goral.interfaces.Tool;
import java.util.HashMap;
import java.util.Map;

public class HistoricalDataTool implements Tool {

    private static final Map<String, Double> historicalAvgTemp;

    static {
        historicalAvgTemp = new HashMap<>();
        historicalAvgTemp.put("London", 12.0);
        historicalAvgTemp.put("Wroclaw", 14.0);
        historicalAvgTemp.put("Warsaw", 10.0);
        historicalAvgTemp.put("New York", 16.0);
        historicalAvgTemp.put("Los Angeles", 20.0);
        historicalAvgTemp.put("Tokyo", 18.0);
        historicalAvgTemp.put("Sydney", 22.0);
    }

    @Override
    public double getTemperature(String location, String timePhrase) {
        return historicalAvgTemp.getOrDefault(location, 15.0);
    }
}
