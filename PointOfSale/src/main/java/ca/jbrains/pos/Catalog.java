package ca.jbrains.pos;

import java.util.Map;

public class Catalog {
    private final Map<String, Integer> pricesInCentsByBarcode;

    public Catalog(final Map<String, Integer> pricesInCentsByBarcode) {
        this.pricesInCentsByBarcode = pricesInCentsByBarcode;
    }

    public String findPricethenFormatPrice(final String barcode) {
        Integer priceInCents = findPrice(barcode);

        if (priceInCents == null) {
            return null;
        } else {
            return formatPrice(priceInCents);
        }
    }

    public Integer findPrice(String barcode) {
        return pricesInCentsByBarcode.get(barcode);
    }

    public String formatPrice(int priceInCents) {
        return String.format("$%,.2f",priceInCents/100.0d);
    }
}
