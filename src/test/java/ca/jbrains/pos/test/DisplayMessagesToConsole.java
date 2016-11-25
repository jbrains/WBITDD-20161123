package ca.jbrains.pos.test;

import ca.jbrains.pos.Price;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static ca.jbrains.pos.test.Text.lines;

public class DisplayMessagesToConsole {
    private StringWriter canvas;
    private JUnitRuleMockery context = new JUnitRuleMockery();

    @Before
    public void setUp() throws Exception {
        canvas = new StringWriter();
    }

    @Test
    public void emptyBarcode() throws Exception {
        new ConsoleDisplay(canvas, new EnglishLanguageMessageFormat()).displayScannedEmptyBarcodeMessage();

        Assert.assertEquals(
                lines("Scanning error: empty barcode"),
                lines(canvas.toString()));
    }

    @Test
    public void productNotFound() throws Exception {
        new ConsoleDisplay(canvas, new EnglishLanguageMessageFormat()).displayProductNotFoundMessage("::barcode not found::");

        Assert.assertEquals(
                lines("Product not found for ::barcode not found::"),
                lines(canvas.toString()));
    }

    @Test
    public void price() throws Exception {
        final MessageFormat messageFormat = context.mock(MessageFormat.class);

        context.checking(new Expectations() {{
            allowing(messageFormat).format(with(any(Price.class)));
            will(returnValue("::formatted price::"));
        }});

        new ConsoleDisplay(canvas, messageFormat).displayPrice(Price.cents(795));

        Assert.assertEquals(
                lines("::formatted price::"),
                lines(canvas.toString()));
    }

    private static class ConsoleDisplay {
        private final PrintWriter out;
        private final MessageFormat messageFormat;

        public ConsoleDisplay(Writer canvas, MessageFormat messageFormat) {
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
            out.println(messageFormat.format(price));
        }

    }
}
