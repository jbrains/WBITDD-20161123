package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Price;

import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog catalogWith(String barcode, Price matchingPrice) {
        return new InMemoryCatalog(
                new HashMap<String, Price>() {{
                    put(String.format("Not %s", barcode), Price.cents(1));
                    put(String.format("Definitely not %s", barcode), Price.cents(2));
                    put(barcode, matchingPrice);
                    put(String.format("Still not %s", barcode), Price.cents(3));
                }});
    }

    @Override
    protected Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put(String.format("Not %s", barcodeToAvoid), Price.cents(1));
            put(String.format("Definitely not %s", barcodeToAvoid), Price.cents(2));
            put(String.format("Still not %s", barcodeToAvoid), Price.cents(3));
        }});
    }

    public static class InMemoryCatalog implements Catalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
