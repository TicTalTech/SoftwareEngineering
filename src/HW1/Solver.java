package HW1;

/**
 * deciding witch tiles needs to end up where and in what order
 */
public class Solver {
    /**
     * Creates an array that holds where each tile needs to be placed in order of its placement
     *
     * @param b The board that will be solved
     * @return returns an array that holds where each tile needs to be placed in order to solve the board
     */
    public static SwitchStep[] solvingSteps(Board b) {
        Tile[][] board = b.getTiles();
        int numOfRows = board.length;
        int numOfCols = board[0].length;
        int col = 0;
        int index = 0;
        SwitchStep[] paths = new SwitchStep[numOfRows * numOfCols * 3];
        for (int row = 0; row < numOfRows - 2; ++row) {
            while (col < numOfCols - 2) {
                paths[index] = new SwitchStep((row * numOfCols) + 1 + col, row, col, SwitchStepStatus.FINAL);
                col++;
                index++;
            }
            paths[index] = new SwitchStep(row * numOfCols + col + 1, row, numOfCols - 1, SwitchStepStatus.CORNER);
            index++;
            paths[index] = new SwitchStep(row * numOfCols + col + 2, row + 1, numOfCols - 1, SwitchStepStatus.NEXT_TO_CORNER);
            index++;
            paths[index] = new SwitchStep(row * numOfCols + col + 1, row, numOfCols - 2, SwitchStepStatus.FINAL);
            index++;
            paths[index] = new SwitchStep(row * numOfCols + col + 2, row, numOfCols - 1, SwitchStepStatus.FINAL);
            index++;
            col = 0;
        }
        while (col < numOfCols - 2) {
            paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + 1 + col, numOfRows - 1, col, SwitchStepStatus.CORNER);
            index++;
            paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + 1 + col + numOfCols, numOfRows - 1, col + 1, SwitchStepStatus.NEXT_TO_CORNER);
            index++;
            paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + 1 + col, numOfRows - 2, col, SwitchStepStatus.FINAL);
            index++;
            paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + 1 + col + numOfCols, numOfRows - 1, col, SwitchStepStatus.FINAL);
            index++;
            col++;
        }
        paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + (numOfCols - 2) + 1, numOfRows - 2, numOfCols - 2, SwitchStepStatus.FINAL);
        index++;
        paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + (numOfCols - 1) + 1, numOfRows - 2, numOfCols - 1, SwitchStepStatus.FINAL);
        index++;
        paths[index] = new SwitchStep((numOfRows - 1) * numOfCols + (numOfCols - 2) + 1, numOfRows - 1, numOfCols - 2, SwitchStepStatus.FINAL);

        return paths;
    }
}