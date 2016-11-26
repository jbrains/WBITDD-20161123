package ca.jbrains.pos;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.stream.Stream;

public class ConsumeTextCommands {
    private final ExecuteTextCommands executeTextCommands;

    public ConsumeTextCommands(ExecuteTextCommands executeTextCommands) {
        this.executeTextCommands = executeTextCommands;
    }

    public void fromReader(Reader commandSource) {
        executeTextCommands.executeCommands(commandStream(commandSource));
    }

    public Stream<String> commandStream(Reader commandSource) {
        return new BufferedReader(commandSource).lines();
    }
}