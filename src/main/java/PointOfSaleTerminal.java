import ca.jbrains.pos.Display;
import ca.jbrains.pos.InMemoryCatalog;
import ca.jbrains.pos.Price;
import ca.jbrains.pos.SellOneItemController;

import java.util.HashMap;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        final SellOneItemController controller = new SellOneItemController(
                new InMemoryCatalog(
                        new HashMap<String, Price>() {{
                            put("7070529026686", Price.cents(150));
                        }}
                ),
                new Display() {
                    @Override
                    public void displayPrice(Price price) {
                        System.out.println(String.format("EUR %.2f", price.euro()));
                    }

                    @Override
                    public void displayProductNotFoundMessage(String barcodeNotFound) {
                        System.out.println(String.format("Product not found for %s", barcodeNotFound));
                    }

                    @Override
                    public void displayScannedEmptyBarcodeMessage() {
                        System.out.println("Scanning error: empty barcode");
                    }
                }
        );

        controller.onBarcode("7070529026686");
        controller.onBarcode("I don't know you");
        controller.onBarcode("");
    }
}
