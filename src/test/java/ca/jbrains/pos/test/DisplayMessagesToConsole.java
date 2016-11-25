package ca.jbrains.pos.test;

import ca.jbrains.pos.Price;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static ca.jbrains.pos.test.Text.lines;

public class DisplayMessagesToConsole {

    private StringWriter canvas;

    @Before
    public void setUp() throws Exception {
        canvas = new StringWriter();
    }

    @Test
    public void emptyBarcode() throws Exception {
        new ConsoleDisplay(canvas).displayScannedEmptyBarcodeMessage();

        Assert.assertEquals(
                lines("Scanning error: empty barcode"),
                lines(canvas.toString()));
    }

    @Test
    public void productNotFound() throws Exception {
        new ConsoleDisplay(canvas).displayProductNotFoundMessage("::barcode not found::");

        Assert.assertEquals(
                lines("Product not found for ::barcode not found::"),
                lines(canvas.toString()));
    }

    @Ignore("Refactoring")
    @Test
    public void price() throws Exception {
        new ConsoleDisplay(canvas).displayPrice(Price.cents(795));

        Assert.assertEquals(
                lines("EUR 7.95"),
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

        public void displayProductNotFoundMessage(String barcodeNotFound) {
            out.println(String.format(
                    "Product not found for %s", barcodeNotFound));
        }

        public void displayPrice(Price price) {
        }
    }
}
