package ca.jbrains.pos.test;

import ca.jbrains.pos.Price;

public class EnglishLanguageMessageFormat {
    public String format(Price price) {
        return String.format("EUR %s", price.euro());
    }

}
