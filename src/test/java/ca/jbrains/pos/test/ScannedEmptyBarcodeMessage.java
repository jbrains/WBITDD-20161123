package ca.jbrains.pos.test;

class ScannedEmptyBarcodeMessage implements UserDisplayMessage {
    public String formatWith(MessageFormat messageFormat) {
        return messageFormat.formatScannedEmptyBarcodeMessage();
    }
}
