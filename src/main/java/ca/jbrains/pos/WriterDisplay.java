package ca.jbrains.pos;

import java.io.PrintWriter;
import java.io.Writer;

public class WriterDisplay implements Display {
    private final PrintWriter out;
    private final MessageFormat messageFormat;

    public WriterDisplay(Writer canvas, MessageFormat messageFormat) {
        this.out = new PrintWriter(canvas, true);
        this.messageFormat = messageFormat;
    }

    public void displayScannedEmptyBarcodeMessage() {
        out.println(messageFormat.formatScannedEmptyBarcodeMessage());
    }

    public void displayProductNotFoundMessage(String barcodeNotFound) {
        out.println(messageFormat.formatProductNotFoundMessage(barcodeNotFound));
    }

    public void displayPrice(Price price) {
        out.println(messageFormat.formatProductFoundMessage(price));
    }
}
