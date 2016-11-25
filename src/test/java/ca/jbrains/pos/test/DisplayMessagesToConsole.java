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
    private MessageFormat messageFormat;

    @Before
    public void setUp() throws Exception {
        canvas = new StringWriter();
        messageFormat = context.mock(MessageFormat.class);
    }

    @Test
    public void emptyBarcode() throws Exception {
        context.checking(new Expectations() {{
            allowing(messageFormat).formatScannedEmptyBarcodeMessage();
            will(returnValue("::scanned empty barcode message::"));
        }});

        new ConsoleDisplay(canvas, messageFormat).displayScannedEmptyBarcodeMessage();

        Assert.assertEquals(
                lines("::scanned empty barcode message::"),
                lines(canvas.toString()));
    }

    @Test
    public void productNotFound() throws Exception {
        context.checking(new Expectations() {{
            allowing(messageFormat).formatProductNotFoundMessage(with("::barcode not found::"));
            will(returnValue("::product not found message::"));
        }});

        new ConsoleDisplay(canvas, messageFormat).displayProductNotFoundMessage("::barcode not found::");

        Assert.assertEquals(
                lines("::product not found message::"),
                lines(canvas.toString()));
    }

    @Test
    public void price() throws Exception {
        final Price price = Price.cents(795);

        context.checking(new Expectations() {{
            allowing(messageFormat).format(with(price));
            will(returnValue("::formatted price::"));
        }});

        new ConsoleDisplay(canvas, messageFormat).displayPrice(price);

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
