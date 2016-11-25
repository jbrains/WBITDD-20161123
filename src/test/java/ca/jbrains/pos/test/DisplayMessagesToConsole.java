package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static ca.jbrains.pos.test.Text.lines;

public class DisplayMessagesToConsole {
    @Test
    public void emptyBarcode() throws Exception {
        final StringWriter canvas = new StringWriter();

        new ConsoleDisplay(canvas).displayScannedEmptyBarcodeMessage();

        Assert.assertEquals(
                lines("Scanning error: empty barcode"),
                lines(canvas.toString()));
    }

    private static class ConsoleDisplay {
        private final PrintWriter out;

        public ConsoleDisplay(Writer canvas) {
            this.out = new PrintWriter(canvas, true);
        }

        public void displayScannedEmptyBarcodeMessage() {
            out.println("Scanning error: empty barcode");
        }
    }
}
