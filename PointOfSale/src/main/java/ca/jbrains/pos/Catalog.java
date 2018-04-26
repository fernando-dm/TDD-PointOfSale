package ca.jbrains.pos;

import java.util.Map;

public class Catalog {
    private final Map<String, Integer> pricesInCentsByBarcode;

    public Catalog(final Map<String, Integer> pricesInCentsByBarcode) {
        this.pricesInCentsByBarcode = pricesInCentsByBarcode;
    }

    public Integer findPrice(String barcode) {
        return pricesInCentsByBarcode.get(barcode);
    }


}
