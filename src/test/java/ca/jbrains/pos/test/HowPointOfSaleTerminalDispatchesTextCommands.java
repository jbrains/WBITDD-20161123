package ca.jbrains.pos.test;

import ca.jbrains.pos.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;

public class HowPointOfSaleTerminalDispatchesTextCommands {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void removeWhitespaceAndConsiderEverythingABarcode() throws Exception {
        final BarcodeScannedListener barcodeScannedListener = context.mock(BarcodeScannedListener.class);
        final ConsumeTextCommands consumeTextCommands =
                new ConsumeTextCommands(
                        new ExecuteSanitizedCommands(
                                new RemoveWhitespaceFromCommands(),
                                new InterpretPointOfSaleTextCommand(barcodeScannedListener)));

        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 1::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode\t\t2::"));
            oneOf(barcodeScannedListener).onBarcode(with("::barcode 3::"));
        }});

        consumeTextCommands.fromReader(new StringReader(Text.unlines(Arrays.asList(
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
        ))));
    }
}
