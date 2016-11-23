package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assert.assertEquals(0, sum.intValue());
    }

    public static class Fraction {
        public Fraction(int integerValue) {

        }

        public Fraction plus(Fraction other) {
            return new Fraction(-1);
        }

        public int intValue() {
            return 0;
        }
    }
}
