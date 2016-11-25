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

        consumeBarcodeCommands(new StringReader(Text.unlines(Arrays.asList("::barcode::"))));
    }

    private void consumeBarcodeCommands(Reader commandSource) throws IOException {
        barcodeScannedListener.onBarcode(
                new BufferedReader(commandSource).readLine());
    }

    public interface BarcodeScannedListener {
        void onBarcode(String barcode);
    }
}
