package HW1.a_star;

import HW1.Board;
import HW1.math_util.Int4;

/**
 * uses the MovePath and The AStar to score the boards
 */
public class AStarHeuristic
{
    //    public static AStar aStar;
    public static Int4[] moves;
    public static Board board;
    public static int movesCounter;

    public static int lastBoardId;

    /**
     * initializing the A* and using it to solve the given board
     *
     * @param initialBoard - the start board that needs to be solved
     */
    public static void initOnBoard(Board initialBoard) {
        AStarHeuristic.board = new Board(initialBoard);
        Board copyBoard = new Board(initialBoard);
        moves = MovePath.solveBoard(copyBoard);
        movesCounter = 1;
        Int4 move = moves[0];
        // checks if the given board is not already solved
        if (move != null) {
            AStarHeuristic.board.switchTiles(move.x, move.y, move.z, move.w);
        }
        lastBoardId = 0;

    }

    /**
     * used to check if the board being explored is part of the solution to the board that was found
     *
     * @param compareBoard - the current state of the game board
     * @return true if the board is part of the solution
     */
    public static boolean isBoardEqualToCurrent(Board compareBoard) {
        if (compareBoard.equals(board)) {
            Int4 move = moves[movesCounter];
            if (move == null) {
                return true;
            }
            board.switchTiles(move.x, move.y, move.z, move.w);
            movesCounter++;
            return true;
        }
        return false;
    }

    /**
     * return the heuristic value of the board (that was already calculated earlier and was saved)
     *
     * @param board - the board we want the heuristic score of
     * @return the heuristic score of board
     */
    public static int aStarHeuristic(Board board) {
        return board.getAStarScore();
    }
}
