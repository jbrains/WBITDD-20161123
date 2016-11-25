package ca.jbrains.pos.test

import ca.jbrains.pos.Price
import spock.lang.Specification
import spock.lang.Unroll

class FormatPriceTest extends Specification {
    @Unroll
    def "format price #price.euro()"() {
        expect:
        new EnglishLanguageMessageFormat().format(price) == text

        where:
        price                || text
        Price.cents(795)     || "EUR 7.95"
        Price.cents(1265)    || "EUR 12.65"
        Price.cents(850)     || "EUR 8.50"
        Price.cents(1700)    || "EUR 17.00"
        Price.cents(0)       || "EUR 0.00"
        Price.cents(1)       || "EUR 0.01"
        Price.cents(70)      || "EUR 0.70"
        Price.cents(2837124) || "EUR 28371.24"
    }
}
