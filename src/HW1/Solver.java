package HW1;

// https://www.cs.princeton.edu/courses/archive/spring21/cos226/assignments/8puzzle/specification.php#:~:text=Thus%2C%20if%20a%20board%20has,inversions%2C%20then%20it%20is%20solvable.
// https://www.kopf.com.br/kaplof/how-to-solve-any-slide-puzzle-regardless-of-its-size/
public class Solver {
    public static SwitchStep[] solvingSteps(Board b) {
        Tile[][] board = b.getTiles();
        int numOfRows = board.length;
        int numOfCols = board[0].length;
        int col = 0;
        int index = 0;
        SwitchStep[] paths = new SwitchStep[numOfRows * numOfCols * 3];
        for (int row = 0; row < numOfRows - 2; ++row) {
            while (col < numOfCols - 2) {
                paths[index] = new SwitchStep((row * numOfCols) + 1 + col, row, col, true);
                col++;
                index++;
            }
            paths[index] = new SwitchStep(row * numOfCols + col + 1, row, numOfCols - 1, false);
            index++;
            paths[index] = new SwitchStep(row * numOfCols + col + 2, row + 1, numOfCols - 1, false);
            index++;
            paths[index] = new SwitchStep(row * numOfCols + col + 1, row, numOfCols - 2, true);
            index++;
            paths[index] = new SwitchStep(row * numOfCols + col + 2, row, numOfCols - 1, true);
            index++;
            col = 0;
        }
        while (col < numOfCols - 2) {
            paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + 1 + col, numOfRows - 1, col, false);
            index++;
            paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + 1 + col + numOfCols, numOfRows - 1, col + 1, false);
            index++;
            paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + 1 + col, numOfRows - 2, col, true);
            index++;
            paths[index] = new SwitchStep((numOfRows - 2) * numOfCols + 1 + col + +numOfCols, numOfRows - 1, col, true);
            index++;
            col++;
        }

        return paths;
    }
}