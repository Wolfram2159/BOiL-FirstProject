package sample.object;

public class TableCell {
    private int z;
    private int val;

    public TableCell() {
        this.z = 0;
        this.val = 0;
    }

    public TableCell(int z) {
        this.z = z;
        this.val = 0;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void addVal(int val) { this.val += val; }

    public void substractVal(int val) { this.val -= val; }
}
