package sample.repository;

public class EntryData {
    private int[] suppliers;
    private int[] recipients;
    private int[][] transportCosts;
    private int[] sellPrices;
    private int[] buyPrices;

    public EntryData(int[] suppliers, int[] recipients, int[][] transportCosts, int[] sellPrices, int[] buyPrices) {
        this.suppliers = suppliers;
        this.recipients = recipients;
        this.transportCosts = transportCosts;
        this.sellPrices = sellPrices;
        this.buyPrices = buyPrices;
    }

    public int[] getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(int[] suppliers) {
        this.suppliers = suppliers;
    }

    public int[] getRecipients() {
        return recipients;
    }

    public void setRecipients(int[] recipients) {
        this.recipients = recipients;
    }

    public int[][] getTransportCosts() {
        return transportCosts;
    }

    public void setTransportCosts(int[][] transportCosts) {
        this.transportCosts = transportCosts;
    }

    public int[] getSellPrices() {
        return sellPrices;
    }

    public void setSellPrices(int[] sellPrices) {
        this.sellPrices = sellPrices;
    }

    public int[] getBuyPrices() {
        return buyPrices;
    }

    public void setBuyPrices(int[] buyPrices) {
        this.buyPrices = buyPrices;
    }
}
