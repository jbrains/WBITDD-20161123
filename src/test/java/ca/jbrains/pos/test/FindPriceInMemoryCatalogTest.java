package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    public void productFound() throws Exception {
        final Price foundPrice = Price.cents(1250);
        final InMemoryCatalog catalog = new InMemoryCatalog(
                Collections.singletonMap("12345", foundPrice));
        Assert.assertEquals(foundPrice, catalog.findPrice("12345"));
    }

    @Test
    public void productNotFound() throws Exception {
        final InMemoryCatalog catalog = new InMemoryCatalog(
                Collections.emptyMap());
        Assert.assertEquals(null, catalog.findPrice("12345"));
    }

    public static class InMemoryCatalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
