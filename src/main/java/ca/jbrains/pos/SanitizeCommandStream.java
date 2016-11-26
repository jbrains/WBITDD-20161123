package ca.jbrains.pos;

import java.util.stream.Stream;

public interface SanitizeCommandStream {
    Stream<String> sanitizeCommands(Stream<String> commandStream);
}
