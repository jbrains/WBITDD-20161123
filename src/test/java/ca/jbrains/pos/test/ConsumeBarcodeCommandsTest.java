package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ConsumeBarcodeCommandsTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private BarcodeScannedListener barcodeScannedListener;

    @Before
    public void setUp() throws Exception {
        barcodeScannedListener = context.mock(BarcodeScannedListener.class);
    }

    @Test
    public void one() throws Exception {
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode(with("::barcode::"));
        }});

        consumeBarcodeCommandsFromLines(Arrays.asList("::barcode::"));
    }

    private void consumeBarcodeCommandsFromLines(List<String> lines) throws IOException {
        consumeBarcodeCommands(new StringReader(Text.unlines(lines)));
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

    private void consumeBarcodeCommands(Reader commandSource) {
        sanitizeCommands(commandStream(commandSource))
                .forEach(this::interpretCommand);
    }

    private void interpretCommand(String command) {
        barcodeScannedListener.onBarcode(command);
    }

    private Stream<String> sanitizeCommands(Stream<String> commandStream) {
        return commandStream
                .map(String::trim)
                .filter((line) -> !line.isEmpty());
    }

    private Stream<String> commandStream(Reader commandSource) {
        return new BufferedReader(commandSource).lines();
    }

    public interface BarcodeScannedListener {
        void onBarcode(String barcode);
    }
}
