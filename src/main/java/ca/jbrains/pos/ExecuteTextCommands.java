package ca.jbrains.pos;

import java.util.stream.Stream;

public interface ExecuteTextCommands {
    void executeCommands(Stream<String> commandStream);
}
