package ca.jbrains.pos.test;

import ca.jbrains.pos.Price;

public class ProductFoundMessage implements UserDisplayMessage {
    private final Price price;

    public ProductFoundMessage(Price price) {
        this.price = price;
    }

    @Override
    public String formatWith(MessageFormat messageFormat) {
        return messageFormat.formatProductFoundMessage(price);
    }
}
