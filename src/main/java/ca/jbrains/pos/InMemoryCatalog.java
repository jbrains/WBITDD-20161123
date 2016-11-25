package ca.jbrains.pos;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Price;

import java.util.Map;

public class InMemoryCatalog implements Catalog {
    private final Map<String, Price> pricesByBarcode;

    public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
        this.pricesByBarcode = pricesByBarcode;
    }

    public Price findPrice(String barcode) {
        return pricesByBarcode.get(barcode);
    }
}
