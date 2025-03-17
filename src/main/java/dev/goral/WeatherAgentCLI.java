package dev.goral;

import dev.goral.dataProviders.RandomWeatherDataProvider;
import dev.goral.interfaces.Response;
import dev.goral.interfaces.WeatherDataProvider;

import java.util.Scanner;
import java.util.logging.Logger;

public class WeatherAgentCLI {
    private static final Logger LOGGER = Logger.getLogger(WeatherAgentCLI.class.getName());
    public static void main(String[] args) {
        // Dependency Injection
        WeatherDataProvider dataProvider = new RandomWeatherDataProvider();
        WeatherQueryAgent agent = new WeatherQueryAgent(dataProvider);

        // Interactive CLI
        Scanner scanner = new Scanner(System.in);
        System.out.println("Weather Agent is ready. Ask a question or type 'exit' to quit.");

        while (true) {
            System.out.print("> "); // prompt
            String query = scanner.nextLine().trim();
            if (query.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            // Query processing
            Response response = agent.handleQuery(query);

            if (response.getErrorCode() != 200) {
                System.out.println(response.getErrorMessage());
                continue;
            }

            System.out.println(response);
            // Interaction logging
//            LOGGER.info("Q: " + query + " | A: " + response);
//            System.out.println();
        }
        scanner.close();
    }
}
