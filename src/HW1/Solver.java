package HW1;

// https://www.cs.princeton.edu/courses/archive/spring21/cos226/assignments/8puzzle/specification.php#:~:text=Thus%2C%20if%20a%20board%20has,inversions%2C%20then%20it%20is%20solvable.
// https://www.kopf.com.br/kaplof/how-to-solve-any-slide-puzzle-regardless-of-its-size/
public class Solver {
    public static Vector[] Paths(Board b) {
        Tile[][] board = b.getTiles();
        int numOfRows = board.length;
        int numOfCols = board[0].length;
        int col = 0;
        int index = 0;
        Vector[] paths = new Vector[numOfRows * numOfCols * 3];
        for (int row = 0; row < numOfRows - 2; ++row) {
            while (col < numOfCols - 2) {
                paths[index] = new Vector((row * numOfCols) + 1 + col, row, col);
                col++;
                index++;
            }
            paths[index] = new Vector(row * numOfCols + col + 1, row, numOfCols - 1);
            index++;
            paths[index] = new Vector(row * numOfCols + col + 2, row + 1, numOfCols - 1);
            index++;
            paths[index] = new Vector(row * numOfCols + col + 1, row, numOfCols - 2);
            index++;
            paths[index] = new Vector(row * numOfCols + col + 2, row, numOfCols - 1);
            index++;
            col = 0;
        }
        while (col < numOfCols - 2) {
            paths[index] = new Vector((numOfRows - 2) * numOfCols + 1 + col, numOfRows - 1, col);
            index++;
            paths[index] = new Vector((numOfRows - 2) * numOfCols + 1 + col + numOfCols, numOfRows - 1, col + 1);
            index++;
            paths[index] = new Vector((numOfRows - 2) * numOfCols + 1 + col, numOfRows - 2, col);
            index++;
            paths[index] = new Vector((numOfRows - 2) * numOfCols + 1 + col + +numOfCols, numOfRows - 1, col);
            index++;
            col++;
        }
        return paths;
    }
}