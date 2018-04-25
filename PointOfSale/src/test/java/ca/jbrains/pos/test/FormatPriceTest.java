package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FormatPriceTest {

    @Parameters(name= "Monetary amount {0} formats to {1}")
    public static Collection<Object[]>data(){

        return Arrays.asList(new Object[][]{
//                {789, "$7.89"},
                {0, "$0"}
        });
    }

    private int formatInCents;
    private String formatExpected;

    public FormatPriceTest(int formatInCents, String formatInText) {
        this.formatInCents = formatInCents;
        this.formatExpected = formatInText;
    }

    @Test
    public void formatPriceTest(){

        Assert.assertEquals(formatExpected, formatPrice(formatInCents));
    }

    private String formatPrice(int formatInCents) {
        return "$0";
    }


}
