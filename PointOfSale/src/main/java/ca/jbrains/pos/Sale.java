package ca.jbrains.pos;

import java.util.ArrayList;
import java.util.Collection;

public class Sale {
    private final Display display;
    private final Catalog catalog;
    private Collection<Integer> pendingPurchaseItemPrices = new ArrayList<>();

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
            // REFACTOR eventually a shop cart?
            pendingPurchaseItemPrices.add(priceInCents);
            display.displayPrice(priceInCents);
        }
    }

    public void onTotal() {
        boolean saleInProgress = (!pendingPurchaseItemPrices.isEmpty());

        if (saleInProgress){
            display.displayPurchaseTotal(pendingPurchaseTotal());
        }
        else{
            display.displayNoSaleInProgressMessage();
        }
    }

    private Integer pendingPurchaseTotal() {
        return computePurchaseTotal(pendingPurchaseItemPrices);
    }

    public static Integer computePurchaseTotal(Collection<Integer> pendingPurchaseItemPrices) {
                return pendingPurchaseItemPrices.stream().reduce(0, (sum, each)-> sum+each);
    }
}
