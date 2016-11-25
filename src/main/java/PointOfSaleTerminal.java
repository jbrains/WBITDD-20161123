import ca.jbrains.pos.*;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        new ExecuteTextCommands(
                new SellOneItemController(
                        new InMemoryCatalog(
                                new HashMap<String, Price>() {{
                                    put("7070529026686", Price.cents(210));
                                }}
                        ),
                        new WriterDisplay(
                                new OutputStreamWriter(System.out),
                                new EnglishLanguageMessageFormat()
                        )
                )
        ).consumeBarcodeCommands(new InputStreamReader(System.in));
    }
}
