package ca.jbrains.pos;

import java.util.ArrayList;
import java.util.Collection;

public class Sale {
    private final Display display;
    private final Catalog catalog;
    private Collection<Integer> scannedPrices = new ArrayList<>();

    public Sale(final Display display, final Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(final String barcode) {
        if ("".equals(barcode)) {
            display.displayEmptyBarcodeMessage();
            return;
        }

        Integer priceInCents = catalog.findPrice(barcode);

        if (priceInCents == null)
            display.displayProductNotFoundMessage(barcode);
        else {
            scannedPrices.add(priceInCents);
            display.displayPrice(priceInCents);
        }
    }

    public void onTotal() {
        boolean saleInProgress = (!scannedPrices.isEmpty());

        if (saleInProgress){
            display.displayPurchasetotal(display.formatPrice(scannedPrices.iterator().next()));
        }
        else{
            display.displayNoSaleInProgressMessage();
        }


    }
}
