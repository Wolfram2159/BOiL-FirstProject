package sample.repository;

public class EntryData {
    private int[] dostawcy;
    private int[] odbiorcy;
    private int[][] kosztyTransportu;
    private int[] cenySprzedazy;
    private int[] cenyZakupu;

    public EntryData(int[] dostawcy, int[] odbiorcy, int[][] kosztyTransportu, int[] cenySprzedazy, int[] cenyZakupu) {
        this.dostawcy = dostawcy;
        this.odbiorcy = odbiorcy;
        this.kosztyTransportu = kosztyTransportu;
        this.cenySprzedazy = cenySprzedazy;
        this.cenyZakupu = cenyZakupu;
    }

    public int[] getDostawcy() {
        return dostawcy;
    }

    public void setDostawcy(int[] dostawcy) {
        this.dostawcy = dostawcy;
    }

    public int[] getOdbiorcy() {
        return odbiorcy;
    }

    public void setOdbiorcy(int[] odbiorcy) {
        this.odbiorcy = odbiorcy;
    }

    public int[][] getKosztyTransportu() {
        return kosztyTransportu;
    }

    public void setKosztyTransportu(int[][] kosztyTransportu) {
        this.kosztyTransportu = kosztyTransportu;
    }

    public int[] getCenySprzedazy() {
        return cenySprzedazy;
    }

    public void setCenySprzedazy(int[] cenySprzedazy) {
        this.cenySprzedazy = cenySprzedazy;
    }

    public int[] getCenyZakupu() {
        return cenyZakupu;
    }

    public void setCenyZakupu(int[] cenyZakupu) {
        this.cenyZakupu = cenyZakupu;
    }
}
