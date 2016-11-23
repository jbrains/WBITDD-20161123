package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assert.assertEquals(new Fraction(0), sum);
    }

    @Test
    public void notZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(5).plus(new Fraction(0));
        Assert.assertEquals(new Fraction(5), sum);
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(7));
        Assert.assertEquals(new Fraction(7), sum);
    }

    @Test
    public void bothNotZeroIntegers() throws Exception {
        Fraction sum = new Fraction(4).plus(new Fraction(8));
        Assert.assertEquals(new Fraction(12), sum);
    }

    @Test
    public void sameDenominatorAndNotOne() throws Exception {
        final Fraction sum = new Fraction(7, 5).plus(new Fraction(14, 5));
        Assert.assertEquals(new Fraction(21, 5), sum);
    }

    @Test
    public void sameDenominatorAndResultNotInLowestTerms() throws Exception {
        Assert.assertEquals(
                new Fraction(1),
                new Fraction(1, 2).plus(new Fraction(1, 2)));
    }

    @Test
    public void differentDenominators() throws Exception {
        Assert.assertEquals(
                new Fraction(3, 4),
                new Fraction(1, 4).plus(new Fraction(1, 2)));
    }

    @Test
    public void overflow() throws Exception {
        // MAX_VALUE looks like -1
        Assert.assertEquals(
                new Fraction(2),
                new Fraction(Integer.MAX_VALUE, 2).plus(new Fraction(5, 2)));
    }
}
