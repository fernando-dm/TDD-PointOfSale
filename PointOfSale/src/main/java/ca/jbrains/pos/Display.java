package ca.jbrains.pos;

public class Display {
    private String text;

    public String getText() {
        return text;
    }

    public void displayEmptyBarcodeMessage() {
        this.text = "Scanning error: empty barcode";
    }

    private void displayText(String text) {
        this.text = text;
    }

    public void displayProductNotFoundMessage(final String barcode) {
        this.text = String.format("Product not found for %s", barcode);
    }

    public void displayPurchaseTotal(Integer price) {
        this.text = formatPrice(price);
    }

    public void displayNoSaleInProgressMessage() {
        this.text = String.format("No sale in progress");
    }

    public String formatPrice(int priceInCents) {
        return String.format("$%,.2f",priceInCents/100.0d);
    }

    public void displayPrice(Integer priceInCents) {
        displayText(formatPrice(priceInCents));
    }

}

