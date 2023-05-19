package HW1;

public class SwitchStep {
    /**
     * An object that holds information that is used when solving the board.
     */
public class SwitchStep
{

    private int value;
    private int row;
    private int col;
    private SwitchStepStatus moveStatus;

    public SwitchStep(int value, int row, int col, SwitchStepStatus moveStatus) {
        this.value = value;
        this.row = row;
        this.col = col;
        this.moveStatus = moveStatus;
        this.safeX = safeX;
        this.safeY = safeY;
    }

    public SwitchStep(int value, int row, int col, int moveStatus) {
        this(value, row, col, moveStatus, 0,0);
    }

    public int getSafeX()
    {
        return safeX;
    }

    public int getSafeY()
    {
        return safeY;
    }

    public String toString() {
        return "Move " + value + " to (" + col + ", " + row + ") - " + getMoveStatus();
    }

    public int getValue() {
        return value;
    }

    public SwitchStepStatus getMoveStatus() {
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
