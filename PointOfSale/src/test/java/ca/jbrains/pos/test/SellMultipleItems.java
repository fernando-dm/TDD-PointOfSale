package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.Sale;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
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


}
