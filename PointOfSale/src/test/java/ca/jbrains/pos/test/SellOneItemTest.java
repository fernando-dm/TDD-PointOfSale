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
        final Display display = new Display();
        Catalog catalog = new Catalog(new HashMap<String, String>() {{
            put("12345", "$ 7,95");
        }});

        final Sale sale = new Sale(display, catalog);

        sale.onBarcode("12345");

        Assert.assertEquals("$ 7,95", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "$ 7,95");
            put("23456", "$ 12,50");
        }}));

        sale.onBarcode("23456");

        Assert.assertEquals("$ 12,50", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "$ 7,95");
            put("23456", "$ 12,50");
        }}));

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
        final Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "$ 7,50");
            put("23456", "$ 12,50");
        }}));

        sale.onBarcode("23456");
        sale.onTotal();

        Assert.assertEquals("Total: $ 12,50", display.getText());
    }

}
