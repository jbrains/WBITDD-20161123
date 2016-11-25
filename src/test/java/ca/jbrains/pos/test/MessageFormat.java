package ca.jbrains.pos.test;

import ca.jbrains.pos.Price;

public interface MessageFormat {
    String format(Price price);

    String formatScannedEmptyBarcodeMessage();

    String formatProductNotFoundMessage(String barcodeNotFound);
}
