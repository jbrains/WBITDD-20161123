package ca.jbrains.pos;

import ca.jbrains.pos.Price;

public interface MessageFormat {
    String formatProductFoundMessage(Price price);

    String formatScannedEmptyBarcodeMessage();

    String formatProductNotFoundMessage(String barcodeNotFound);
}
