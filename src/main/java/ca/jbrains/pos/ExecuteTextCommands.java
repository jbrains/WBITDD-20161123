package ca.jbrains.pos;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.stream.Stream;

public class ExecuteTextCommands {
    private final BarcodeScannedListener barcodeScannedListener;

    public ExecuteTextCommands(BarcodeScannedListener barcodeScannedListener) {
        this.barcodeScannedListener = barcodeScannedListener;
    }

    public void consumeBarcodeCommands(Reader commandSource) {
        sanitizeCommands(commandStream(commandSource))
                .forEach(this::interpretCommand);
    }

    public void interpretCommand(String command) {
        barcodeScannedListener.onBarcode(command);
    }

    public Stream<String> sanitizeCommands(Stream<String> commandStream) {
        return commandStream
                .map(String::trim)
                .filter((line) -> !line.isEmpty());
    }

    public Stream<String> commandStream(Reader commandSource) {
        return new BufferedReader(commandSource).lines();
    }
}