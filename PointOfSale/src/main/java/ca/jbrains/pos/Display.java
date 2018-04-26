package ca.jbrains.pos;

public class Display {
    private String text;

    public String getText() {
        return text;
    }

    public void displayEmptyBarcodeMessage() {
        this.text = "Scanning error: empty barcode";
    }

    public void displayPrice(final String price) {
        this.text = price;
    }

    public void displayProductNotFoundMessage(final String barcode) {
        this.text = String.format("Product not found for %s", barcode);
    }

    public void displayPurchasetotal() {
        this.text = this.getText();
    }

    public void displayNoSaleInProgressMessage() {
        this.text = String.format("No sale in progress");
    }

    public String formatPrice(int priceInCents) {
        return String.format("$%,.2f",priceInCents/100.0d);
    }
}

