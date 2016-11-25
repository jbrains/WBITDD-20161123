package ca.jbrains.pos.test;

import ca.jbrains.pos.Price;

public class EnglishLanguageMessageFormat implements MessageFormat {
    @Override
    public String formatProductFoundMessage(Price price) {
        return String.format("EUR %.2f", price.euro());
    }

    @Override
    public String formatScannedEmptyBarcodeMessage() {
        return "Scanning error: empty barcode";
    }

    @Override
    public String formatProductNotFoundMessage(String barcodeNotFound) {
        return String.format(
                "Product not found for %s", barcodeNotFound);
    }
}
