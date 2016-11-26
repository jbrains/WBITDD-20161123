package ca.jbrains.pos;

import java.util.stream.Stream;

public class ExecuteSanitizedCommands implements ExecuteTextCommands {
    private final SanitizeCommandStream sanitizeCommandStream;
    private final InterpretTextCommand interpretTextCommand;

    public ExecuteSanitizedCommands(SanitizeCommandStream sanitizeCommandStream, InterpretTextCommand interpretTextCommand) {
        this.interpretTextCommand = interpretTextCommand;
        this.sanitizeCommandStream = sanitizeCommandStream;
    }

    @Override
    public void executeCommands(Stream<String> commandStream) {
        sanitizeCommandStream.sanitizeCommands(commandStream)
                .forEach(interpretTextCommand::interpretTextCommand);
    }
}