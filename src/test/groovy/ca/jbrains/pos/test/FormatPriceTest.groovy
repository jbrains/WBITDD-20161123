package ca.jbrains.pos.test

import ca.jbrains.pos.Price
import spock.lang.Specification

class FormatPriceTest extends Specification {
    def "format price #price.euro"() {
        expect:
        new EnglishLanguageMessageFormat().format(price) == text

        where:
        price            || text
        Price.cents(795) || "EUR 7.95"
    }
}
