package dev.goral;

import dev.goral.interfaces.Response;
import dev.goral.interfaces.WeatherDataProvider;
import dev.goral.responses.ErrorResponse;
import dev.goral.responses.WeatherResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherQueryAgent {
    private final WeatherDataProvider dataProvider;
    // Regex to determine "Determine the temperature in {city} in {time}"
    private static final Pattern QUERY_PATTERN = Pattern.compile(
            "(?i)^determine the temperature in ([\\p{L}\\-' ]+)(?: in (.+)| (tomorrow|today|now))$"
    );

    public WeatherQueryAgent(WeatherDataProvider provider) {
        this.dataProvider = provider;
    }

    public Response handleQuery(String query) {
        // Check if the query matches the expected pattern
        Matcher matcher = QUERY_PATTERN.matcher(query);
        if (!matcher.matches()) {
            return new ErrorResponse("I'm sorry, I can only handle queries of the form: 'Determine the temperature in [location] in [time]'.", 401);
        }

        // Extract location and time phrase from the query
        String location = matcher.group(1).trim();
        String timePhrase = matcher.group(2) != null ? matcher.group(2).trim() : matcher.group(3).trim();

        // Download the temperature data
        double temp = dataProvider.getTemperature(location, timePhrase);

        return new WeatherResponse(location, timePhrase, temp);
    }
}
