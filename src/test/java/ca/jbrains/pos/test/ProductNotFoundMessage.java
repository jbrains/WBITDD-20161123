package ca.jbrains.pos.test;

public class ProductNotFoundMessage implements UserDisplayMessage {
    private final String barcodeNotFound;

    public ProductNotFoundMessage(String barcodeNotFound) {
        this.barcodeNotFound = barcodeNotFound;
    }

    @Override
    public String formatWith(MessageFormat messageFormat) {
        return messageFormat.formatProductNotFoundMessage(barcodeNotFound);
    }
}
