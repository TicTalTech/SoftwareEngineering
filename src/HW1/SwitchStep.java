package HW1;

public class SwitchStep {
    private int value;
    private int row;
    private int col;
    private boolean isFinal;

    public SwitchStep(int value, int row, int col, boolean isFinal) {
        this.value = value;
        this.row = row;
        this.col = col;
        this.isFinal = isFinal;
    }

    public String toString() {
        return "Move " + value + " to (" + col + ", " + row + ")";
    }

    public int getValue() {
        return value;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
