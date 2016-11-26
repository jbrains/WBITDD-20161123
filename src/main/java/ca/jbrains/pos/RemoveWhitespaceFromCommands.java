package ca.jbrains.pos;

import java.util.stream.Stream;

public class RemoveWhitespaceFromCommands implements SanitizeCommandStream {
    @Override
    public Stream<String> sanitizeCommands(Stream<String> commandStream) {
        return commandStream
                .map(String::trim)
                .filter((line) -> !line.isEmpty());
    }
}