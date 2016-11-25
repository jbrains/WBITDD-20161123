package ca.jbrains.pos.test;

import ca.jbrains.pos.EnglishLanguageMessageFormat;
import ca.jbrains.pos.Price;
import ca.jbrains.pos.Text;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DisplayMessagesToConsoleLegacyCodeTechnique {
    private PrintStream productionStdout;
    private ByteArrayOutputStream canvas;

    @Before
    public void simulateStdout() throws Exception {
        productionStdout = System.out;
        canvas = new ByteArrayOutputStream(100);
        System.setOut(new PrintStream(canvas));
    }

    @After
    public void resetStdout() {
        System.setOut(productionStdout);
    }

    @Test
    public void emptyBarcode() throws Exception {
        displayScannedEmptyBarcodeMessage();

        Assert.assertEquals(
                Text.lines("Scanning error: empty barcode"),
                Text.lines(canvas.toString("US-ASCII")));
    }

    @Test
    public void productNotFound() throws Exception {
        displayProductNotFoundMessage("::barcode not found::");

        Assert.assertEquals(
                Text.lines("Product not found for ::barcode not found::"),
                Text.lines(canvas.toString("US-ASCII")));
    }

    @Test
    public void price() throws Exception {
        // Look at FormatPriceTest to find the detailed tests
        // for formatting a price the way we want to display it.
        displayPrice(Price.cents(23847600));

        Assert.assertEquals(
                Text.lines("EUR 238476.00"),
                Text.lines(canvas.toString("US-ASCII")));
    }

    private void displayPrice(Price price) {
        System.out.println(new EnglishLanguageMessageFormat().formatProductFoundMessage(price));
    }

    private void displayProductNotFoundMessage(String barcodeNotFound) {
        System.out.println(
                String.format("Product not found for %s", barcodeNotFound));
    }

    private void displayScannedEmptyBarcodeMessage() {
        System.out.println("Scanning error: empty barcode");
    }
}
