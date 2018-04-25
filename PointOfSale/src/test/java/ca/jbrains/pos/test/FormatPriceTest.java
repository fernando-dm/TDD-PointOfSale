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
                {789, "$7.89"},
                {0, "$0.00"},
                {2, "$0.02"},
                {10, "$0.10"},
                {100, "$1.00"},
                {1000, "$10.00"},
                {1215646845, "$12,156,468.45"}
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
    // debo agregarle al entero formato de divisa 789 ==> 7.89, agrego separador de miles y de cents y luego le concateno $
    // Esta forma se saca googleando! "$%,.2f"
    private String formatPrice(int priceInCents) {
        return String.format("$%,.2f",priceInCents/100.0d);
    }


}
