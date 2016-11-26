package ca.jbrains.pos;

import java.util.stream.Stream;

public class ExecuteSanitizedCommands implements ExecuteTextCommands {
    private final SanitizeCommandStream sanitizeCommandStream;
    private final InterpretCommand interpretCommand;

    public ExecuteSanitizedCommands(SanitizeCommandStream sanitizeCommandStream, InterpretCommand interpretCommand) {
        this.interpretCommand = interpretCommand;
        this.sanitizeCommandStream = sanitizeCommandStream;
    }

    @Override
    public void executeCommands(Stream<String> commandStream) {
        sanitizeCommandStream.sanitizeCommands(commandStream)
                .forEach(interpretCommand::interpretCommand);
    }
}