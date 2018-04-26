package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.Sale;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ComputePurchaseTotalTest {

    @Test
    public void zeroItems0() {
        Assert.assertEquals(0, computePurchaseTotal(Collections.<Integer>emptyList()));
    }

    // tuve que abstraer el comportamiento de compute, aunque le di caracter de static, porque no me dejaba meter la lista directamente
    private int computePurchaseTotal(List<Integer>purchaseItemPrices) {
        return Sale.computePurchaseTotal(purchaseItemPrices).intValue();
    }

    @Test
    public void zeroItems() {
        final Display display = new Display();
        final Sale sale = new Sale(display,emptyCatalog());

        sale.onBarcode("");
        sale.onTotal();

        Assert.assertEquals("No sale in progress", display.getText());
    }
    private Catalog emptyCatalog() {
        return new Catalog(Collections.emptyMap());
    }

    @Test
    public void oneItemPurchase() {
        final Catalog catalog = new Catalog(new HashMap<String, Integer>(){{
            put("1", 18);
        }});
        final Display display = new Display();
        final Sale sale = new Sale(display,catalog);

        sale.onBarcode("1");
        sale.onTotal();

        Assert.assertEquals("$0.18", display.getText());
        Assert.assertEquals(795, computePurchaseTotal(Collections.singletonList(795)));
    }

    @Test
    public void fiveItemPurchase() {
        final Catalog catalog = new Catalog(new HashMap<String, Integer>(){{
            put("1", 10);
            put("2", 20);
            put("3", 30);
            put("4", 40);
            put("5", 50);
        }});
        final Display display = new Display();
        final Sale sale = new Sale(display,catalog);

        sale.onBarcode("1");
        sale.onBarcode("2");
        sale.onBarcode("3");
        sale.onBarcode("4");
        sale.onBarcode("5");

        sale.onTotal();

        Assert.assertEquals("$1.50", display.getText());
        Assert.assertEquals(795, computePurchaseTotal(Arrays.asList(794,1)));
    }
}
