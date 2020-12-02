package sample.repository;

public class FakeRepository implements Repository{
    private int[] suppliers = new int[] {20, 40};
    private int[] recipients = new int[] {10, 28, 27, 10};
    private int[][] transportCosts = new int[][] {
            new int[] {8, 14, 17, 12},
            new int[] {12, 9, 19, 10}
    };
    private int[] buyPrices = new int[] {10, 12};
    private int[] sellPrices = new int[] {30, 25, 30, 40};

    @Override
    public EntryData getEntryData() {
        return new EntryData(suppliers, recipients, transportCosts, sellPrices, buyPrices);
    }
}
