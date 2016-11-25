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

    private void consumeBarcodeCommands(Reader commandSource) throws IOException {
        final String line = new BufferedReader(commandSource).readLine();
        if (line != null)
            barcodeScannedListener.onBarcode(line);
    }

    public interface BarcodeScannedListener {
        void onBarcode(String barcode);
    }
}
