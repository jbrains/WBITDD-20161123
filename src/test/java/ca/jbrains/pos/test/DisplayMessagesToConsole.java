package ca.jbrains.pos.test;

import ca.jbrains.pos.MessageFormat;
import ca.jbrains.pos.Price;
import ca.jbrains.pos.Text;
import ca.jbrains.pos.WriterDisplay;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

public class DisplayMessagesToConsole {
    private StringWriter canvas;
    private JUnitRuleMockery context = new JUnitRuleMockery();
    private MessageFormat messageFormat;
    private WriterDisplay writerDisplay;

    @Before
    public void setUp() throws Exception {
        canvas = new StringWriter();
        messageFormat = context.mock(MessageFormat.class);
        writerDisplay = new WriterDisplay(canvas, messageFormat);
    }

    @Test
    public void emptyBarcode() throws Exception {
        context.checking(new Expectations() {{
            allowing(messageFormat).formatScannedEmptyBarcodeMessage();
            will(returnValue("::scanned empty barcode message::"));
        }});

        writerDisplay.displayScannedEmptyBarcodeMessage();

        Assert.assertEquals(
                Text.lines("::scanned empty barcode message::"),
                Text.lines(canvas.toString()));
    }

    @Test
    public void productNotFound() throws Exception {
        context.checking(new Expectations() {{
            allowing(messageFormat).formatProductNotFoundMessage(with("::barcode not found::"));
            will(returnValue("::product not found message::"));
        }});

        writerDisplay.displayProductNotFoundMessage("::barcode not found::");

        Assert.assertEquals(
                Text.lines("::product not found message::"),
                Text.lines(canvas.toString()));
    }

    @Test
    public void price() throws Exception {
        final Price price = Price.cents(795);

        context.checking(new Expectations() {{
            allowing(messageFormat).formatProductFoundMessage(with(price));
            will(returnValue("::formatted price::"));
        }});

        writerDisplay.displayPrice(price);

        Assert.assertEquals(
                Text.lines("::formatted price::"),
                Text.lines(canvas.toString()));
    }

}
