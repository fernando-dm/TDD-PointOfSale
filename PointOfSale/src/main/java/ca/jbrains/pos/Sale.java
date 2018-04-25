package ca.jbrains.pos;

public class Sale {
    private final Display display;
    private final Catalog catalog;

    public Sale(final Display display, final Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(final String barcode) {
        if ("".equals(barcode)) {
            display.displayEmptyBarcodeMessage();
            return;
        }

        final String price = catalog.findPrice(barcode);
        if (price == null)
            display.displayProductNotFoundMessage(barcode);
        else
            display.displayPrice(price);
    }
}
