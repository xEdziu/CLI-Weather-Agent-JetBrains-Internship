package dev.goral;

import dev.goral.interfaces.Response;
import dev.goral.interfaces.Tool;
import dev.goral.interfaces.ToolSelector;
import dev.goral.tools.HistoricalDataTool;
import dev.goral.tools.MonitoringTool;
import dev.goral.tools.SimpleToolSelector;

import java.util.Scanner;
import java.util.logging.Logger;

public class WeatherAgentCLI {
    private static final Logger LOGGER = Logger.getLogger(WeatherAgentCLI.class.getName());

    public static void main(String[] args) {
        // Tools initialization
        Tool monitoringTool = new MonitoringTool();
        Tool historicalTool = new HistoricalDataTool();

        // ToolSelector initialization
        ToolSelector toolSelector = new SimpleToolSelector(monitoringTool, historicalTool);

        // WeatherQueryAgent using ToolSelector
        WeatherQueryAgent agent = new WeatherQueryAgent(toolSelector);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Weather Agent is ready. Ask a question or type 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            String query = scanner.nextLine().trim();
            if (query.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            Response response = agent.handleQuery(query);
//            LOGGER.info(response.toString()); // Uncomment this line to log responses

            if (response.getErrorCode() != 200) {
                System.out.println(response.getErrorMessage());
                continue;
            }

            System.out.println(response);

        }
        scanner.close();
    }
}
