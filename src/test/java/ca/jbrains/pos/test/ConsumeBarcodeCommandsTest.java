package ca.jbrains.pos.test;

import ca.jbrains.pos.BarcodeScannedListener;
import ca.jbrains.pos.ConsumeTextCommands;
import ca.jbrains.pos.ExecutePointOfSaleTextCommands;
import ca.jbrains.pos.Text;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class ConsumeBarcodeCommandsTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private ConsumeTextCommands consumeTextCommands;
    private BarcodeScannedListener barcodeScannedListener;

    @Before
    public void setUp() throws Exception {
        barcodeScannedListener = context.mock(BarcodeScannedListener.class);
        consumeTextCommands = new ConsumeTextCommands(new ExecutePointOfSaleTextCommands(barcodeScannedListener));
    }

    @Test
    public void one() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode(with("::barcode::"));
        }});

        consumeBarcodeCommandsFromLines(Arrays.asList("::barcode::"));
    }

    private void consumeBarcodeCommandsFromLines(List<String> lines) throws IOException {
        consumeTextCommands.fromReader(new StringReader(Text.unlines(lines)));
    }

    @Test
    public void none() throws Exception {
        context.checking(new Expectations() {{
            never(barcodeScannedListener).onBarcode(with(any(String.class)));
        }});

        consumeBarcodeCommandsFromLines(Arrays.asList());
    }

    @Test
    public void several() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 1::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 2::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 3::"));
        }});

        consumeBarcodeCommandsFromLines(Arrays.asList(
                "::barcode 1::",
                "::barcode 2::",
                "::barcode 3::"
        ));
    }

    @Test
    public void rejectEmptyBarcodes() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 1::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 2::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 3::"));
        }});

        consumeBarcodeCommandsFromLines(Arrays.asList(
                "",
                "",
                "::barcode 1::",
                "",
                "",
                "::barcode 2::",
                "",
                "",
                "",
                "::barcode 3::",
                "",
                "",
                ""
        ));
    }

    @Test
    public void removeWhitespaceFromBarcodes() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 1::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode\t\t2::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 3::"));
        }});

        consumeBarcodeCommandsFromLines(Arrays.asList(
                "",
                "    \n     \r      ",
                "     ::barcode 1::    ",
                "       \t",
                "",
                "\t\t::barcode\t\t2::  \t     \t",
                "   ",
                "      ",
                "",
                "\t\t\t\t::barcode 3::\t\t\t\t\t\t\t",
                "",
                "\r\r\r\r\r\r\r\r",
                ""
        ));
    }
}
