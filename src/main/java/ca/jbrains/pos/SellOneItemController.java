package ca.jbrains.pos;

public class SellOneItemController implements BarcodeScannedListener {
    private final Catalog catalog;
    private final Display display;

    public SellOneItemController(Catalog catalog, Display display) {
        this.catalog = catalog;
        this.display = display;
    }

    public void onBarcode(String barcode) {
        if("".equals(barcode)) {
            display.displayScannedEmptyBarcodeMessage();
            return;
        }

        final Price price = catalog.findPrice(barcode);
        if (price == null)
            display.displayProductNotFoundMessage(barcode);
        else
            display.displayPrice(price);
    }
}
