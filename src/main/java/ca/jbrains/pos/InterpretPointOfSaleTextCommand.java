package ca.jbrains.pos;

public class InterpretPointOfSaleTextCommand implements InterpretTextCommand {
    public BarcodeScannedListener barcodeScannedListener;

    public InterpretPointOfSaleTextCommand(BarcodeScannedListener barcodeScannedListener) {
        this.barcodeScannedListener = barcodeScannedListener;
    }

    @Override
    public void interpretTextCommand(String textCommand) {
        barcodeScannedListener.onBarcode(textCommand);
    }
}