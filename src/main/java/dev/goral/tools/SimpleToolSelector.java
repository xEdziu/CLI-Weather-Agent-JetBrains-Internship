package dev.goral.tools;

import dev.goral.interfaces.Tool;
import dev.goral.interfaces.ToolSelector;

public class SimpleToolSelector implements ToolSelector {

    private final Tool monitoringTool;
    private final Tool historicalTool;

    public SimpleToolSelector(Tool monitoringTool, Tool historicalTool) {
        this.monitoringTool = monitoringTool;
        this.historicalTool = historicalTool;
    }

    @Override
    public Tool select(String timePhrase) {
        if (timePhrase.toLowerCase().contains("ago") ||
                timePhrase.toLowerCase().contains("yesterday")) {
            return historicalTool;
        }
        return monitoringTool;
    }
}
