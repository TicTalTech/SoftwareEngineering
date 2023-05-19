package HW1;

public class SwitchStep {
    /**
     * An object that holds information that is used when solving the board.
     */
    public static final int FINAL = 0;
    public static final int CORNER = 1;
    public static final int BELLOW_CORNER = 2;
    public static final int OUT_OF_THE_WAY = 3;


    private int value;
    private int row;
    private int col;
    private int moveStatus;

    // used to protect the tile next to the corner
    private int safeX, safeY;

    /**
     * @param value      The tiles value
     * @param row        The row to move the tile to
     * @param col        The column to move the tile to
     * @param moveStatus Where the move places the tile
     */
    public SwitchStep(int value, int row, int col, int moveStatus, int safeX, int safeY) {
        this.value = value;
        this.row = row;
        this.col = col;
        this.moveStatus = moveStatus;
        this.safeX = safeX;
        this.safeY = safeY;
    }

    public SwitchStep(int value, int row, int col, int moveStatus) {
        this(value, row, col, moveStatus, 0, 0);
    }

    public int getSafeX() {
        return safeX;
    }

    public int getSafeY() {
        return safeY;
    }

    public String toString() {
        return "Move " + value + " to (" + col + ", " + row + ")";
    }

    public int getValue() {
        return value;
    }

    public int getMoveStatus() {
        return moveStatus;
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
