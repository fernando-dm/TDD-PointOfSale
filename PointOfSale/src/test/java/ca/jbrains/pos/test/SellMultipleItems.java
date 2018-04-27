package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.Sale;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import java.util.Collections;
import java.util.HashMap;

public class SellMultipleItems {

    @Test
    public void severallItemsNotFound() {
        final Display display = new Display();
        final Sale sale = new Sale(display,emptyCatalog());

        sale.onBarcode("2345");
        sale.onBarcode("a");
        sale.onBarcode("d");
        sale.onBarcode("3");

        sale.onTotal();

        Assert.assertEquals("No sale in progress", display.getText());
    }

    private Catalog emptyCatalog() {
        return new Catalog(Collections.emptyMap());
    }
    // Este test y el metodo de catalogo es mas revelador
    @Test
    public void severalItemsWithBarcodeNotFound() {
        final Display display = new Display();
        final Sale sale = new Sale(display,catalogWithOutBarcodes("barcode1", "otrobar", "kajw", "sfff"));

        sale.onBarcode("2345");
        sale.onBarcode("a");
        sale.onBarcode("d");
        sale.onBarcode("3");

        sale.onTotal();

        Assert.assertEquals("No sale in progress", display.getText());
    }

    private Catalog catalogWithOutBarcodes(String... emptyBarcodes) {
        return new Catalog(Collections.emptyMap());
    }

    @Test
//    @Ignore("Por ahora")
    public void severalItemsAllFound() {
        final Catalog catalog = new Catalog(new HashMap<String, Integer>(){{
                                                                            put("1", 789);
                                                                            put("2", 125);
                                                                        }});
        final Display display = new Display();
        final Sale sale = new Sale(display,catalog);

        sale.onBarcode("1");
        sale.onBarcode("2");

        sale.onTotal();

        Assert.assertEquals("$9.14", display.getText());
    }

    @Test
    public void severalItemsSomeNotFound() {
        final Catalog catalog = new Catalog(new HashMap<String, Integer>(){{
            put("1", 100);
            put("2", 200);
            put("3", 300);
            put("4", 400);
            put("5", 500);
            put("6", 600);

        }});
        final Display display = new Display();
        final Sale sale = new Sale(display,catalog);

        sale.onBarcode("1");
        sale.onBarcode("2");
        sale.onBarcode("3");
        sale.onBarcode("4");
        sale.onBarcode("50");
        sale.onBarcode("6");
        sale.onBarcode("60");


        sale.onTotal();

        Assert.assertEquals("$16.00", display.getText());
    }

}
