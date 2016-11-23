package ca.jbrains.math.test;

public final class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int integerValue) {
        this(integerValue, 1);
    }

    public Fraction(int numerator, int denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException("Fraction can't have a 0 denominator.");

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction plus(Fraction that) {
        return new Fraction(
                this.numerator * that.denominator
                        + this.denominator * that.numerator,
                this.denominator * that.denominator);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Fraction) {
            Fraction that = (Fraction) other;
            return this.numerator * that.denominator
                    == this.denominator * that.numerator;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }
}
