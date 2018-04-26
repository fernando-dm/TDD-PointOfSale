package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.Sale;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        Catalog catalog = new Catalog(
                new HashMap<String, Integer>() {{
                                            put("12345", 795);}}
                            );
        final Display display = new Display();
        final Sale sale = new Sale(display, catalog);

        sale.onBarcode("12345");

        Assert.assertEquals("$7.95", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(

                new HashMap<String, Integer>() {{
                    put("12345", 795);
                    put("2345", 125);
                }}
        ));

        sale.onBarcode("2345");

        Assert.assertEquals("$1.25", display.getText());
    }

    @Test
    public void productNotFound() {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(
                new HashMap<String, Integer>() {{
                    put("12345", 795);
                    put("2345", 125);
                }}
        ));

        sale.onBarcode("99999");

        Assert.assertEquals("Product not found for 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(Collections.emptyMap()));

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", display.getText());
    }

    @Test
    public void getTotalOfAProduct() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display,new Catalog(
                new HashMap<String, Integer>() {{
                                                            put("12345", 795);
                                                            put("2345", 125);}}
        ));

        sale.onBarcode("2345");
        sale.onTotal();

        Assert.assertEquals("$1.25", display.getText());
    }


}
