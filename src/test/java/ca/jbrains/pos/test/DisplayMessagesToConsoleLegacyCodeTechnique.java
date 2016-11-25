package ca.jbrains.pos.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class DisplayMessagesToConsoleLegacyCodeTechnique {
    private PrintStream productionStdout;
    private ByteArrayOutputStream canvas;

    // REFACTOR Move this to a reusable library
    public static List<String> lines(String text) {
        return Arrays.asList(text.split(System.lineSeparator()));
    }

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
                lines("Scanning error: empty barcode"),
                lines(canvas.toString("US-ASCII")));
    }

    @Test
    public void productNotFound() throws Exception {
        displayProductNotFoundMessage("::barcode not found::");

        Assert.assertEquals(
                lines("Product not found for ::barcode not found::"),
                lines(canvas.toString("US-ASCII")));
    }

    private void displayProductNotFoundMessage(String barcodeNotFound) {
        System.out.println(
                String.format("Product not found for %s", barcodeNotFound));
    }

    private void displayScannedEmptyBarcodeMessage() {
        System.out.println("Scanning error: empty barcode");
    }
}
