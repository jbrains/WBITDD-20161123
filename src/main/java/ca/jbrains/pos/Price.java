package ca.jbrains.pos;

public class Price {
    private final int centsValue;

    public Price(int centsValue) {
        this.centsValue = centsValue;
    }

    public static Price cents(int centsValue) {
        return new Price(centsValue);
    }

    @Override
    public String toString() {
        return "a Price";
    }

    public double euro() {
        return centsValue / 100.0d;
    }
}
