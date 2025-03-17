# CLI Java Weather Agent

This is a project for applying to **JetBrains Internship 2025 3.0**.

Task was to develop a command-line interface (CLI) application using Java or Kotlin that simulates an AI agent.
The twist about it is to don't use any LLM model - I had to imitate the interaction manually.

### Requirements

- An input request is sent to the agent in text format. For example, "Determine the temperature in London in an hour".
- The agent provides the output in text format as well. For instance, "It will be 10°C in London in an hour".
- The agent should only be able to handle one type of request. If you try to send it different types of requests, it will not be able to process them. For example, the agent from the example above can process queries like `"Determine the temperature in $location in $time"`, but cannot process queries like `"Determine the temperature in $location"` or `"Determine the water temperature in $location in $time"`.
- The agent should have some tools. For instance, the agent could monitor its environment, analyze data, or perform some actions. Both data and actions could be synthetic if you wish.
