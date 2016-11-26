package ca.jbrains.pos;

import java.util.stream.Stream;

public class ExecutePointOfSaleTextCommands implements ExecuteTextCommands {
    private BarcodeScannedListener barcodeScannedListener;

    public ExecutePointOfSaleTextCommands(BarcodeScannedListener barcodeScannedListener) {
        this.barcodeScannedListener = barcodeScannedListener;
    }

    public void interpretCommand(String command) {
        barcodeScannedListener.onBarcode(command);
    }

    public Stream<String> sanitizeCommands(Stream<String> commandStream) {
        return commandStream
                .map(String::trim)
                .filter((line) -> !line.isEmpty());
    }

    @Override
    public void executeCommands(Stream<String> commandStream) {
        sanitizeCommands(commandStream)
                .forEach(this::interpretCommand);
    }
}