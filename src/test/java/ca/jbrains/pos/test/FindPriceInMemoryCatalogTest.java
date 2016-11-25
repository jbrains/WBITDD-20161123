package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.InMemoryCatalog;
import ca.jbrains.pos.Price;

import java.util.HashMap;

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

}
