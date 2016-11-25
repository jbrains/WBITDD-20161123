package ca.jbrains.pos.test;

import ca.jbrains.pos.Price;

public class EnglishLanguageMessageFormat implements MessageFormat {
    @Override
    public String format(Price price) {
        return String.format("EUR %.2f", price.euro());
    }
}
