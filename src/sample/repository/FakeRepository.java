package sample.repository;

public class FakeRepository implements Repository{
    private int[] dostawcy = new int[] {20, 40};
    private int[] odbiorcy = new int[] {10, 28, 27, 10};
    private int[][] kosztyTransportu = new int[][] {
            new int[] {8, 14, 17, 12},
            new int[] {12, 9, 19, 10}
    };
    private int[] cenaZakupu = new int[] {10, 12};
    private int[] cenaSprzedazy = new int[] {30, 25, 30, 40};

    @Override
    public EntryData getEntryData() {
        return new EntryData(dostawcy, odbiorcy, kosztyTransportu, cenaSprzedazy, cenaZakupu);
    }
}
