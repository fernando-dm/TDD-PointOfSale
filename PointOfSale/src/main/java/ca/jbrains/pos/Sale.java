package ca.jbrains.pos;

public class Sale {
    private final Display display;
    private final Catalog catalog;
    private String scannedPrice;

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
            scannedPrice =catalog.formatPrice(priceInCents);
            display.displayPrice(scannedPrice);
        }

    }

    public void onTotal() {
        boolean saleInProgress = (scannedPrice != null);
        if (saleInProgress){
            display.displayPurchasetotal();
        }
        else{
            display.displayNoSaleInProgressMessage();
        }


    }
}
