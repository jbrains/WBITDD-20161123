package ca.jbrains.pos;

public class InterpretPointOfSaleTextCommand implements InterpretCommand {
    public BarcodeScannedListener barcodeScannedListener;

    public InterpretPointOfSaleTextCommand(BarcodeScannedListener barcodeScannedListener) {
        this.barcodeScannedListener = barcodeScannedListener;
    }

    @Override
    public void interpretCommand(String command) {
        barcodeScannedListener.onBarcode(command);
    }
}