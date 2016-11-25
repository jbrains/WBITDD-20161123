package ca.jbrains.pos.test;

import ca.jbrains.pos.Price;
import org.junit.*;

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

    @Test
    public void price() throws Exception {
        new ConsoleDisplay(canvas, new MessageFormat() {
            @Override
            public String format(Price price) {
                return "::formatted price::";
            }
        }).displayPrice(Price.cents(795));

        Assert.assertEquals(
                lines("::formatted price::"),
                lines(canvas.toString()));
    }

    private static class ConsoleDisplay {
        private final PrintWriter out;
        private final MessageFormat messageFormat;

        public ConsoleDisplay(Writer canvas) {
            this(canvas, new EnglishLanguageMessageFormat());
        }

        public ConsoleDisplay(Writer canvas, MessageFormat messageFormat) {
            this.out = new PrintWriter(canvas, true);
            this.messageFormat = messageFormat;
        }

        public void displayScannedEmptyBarcodeMessage() {
            out.println("Scanning error: empty barcode");
        }

        public void displayProductNotFoundMessage(String barcodeNotFound) {
            out.println(String.format(
                    "Product not found for %s", barcodeNotFound));
        }

        public void displayPrice(Price price) {
            out.println(messageFormat.format(price));
        }
    }
}
