package HW1;

import HW1.a_star.MovePath;
import HW1.math.Int4;

public class AStarHeuristic
{
    //    public static AStar aStar;
    public static Int4[] moves;
    public static Board board;
    public static int movesCounter;

    public static int lastBoardId;

    public static void initOnBoard(Board initialBoard) {
        AStarHeuristic.board = new Board(initialBoard);
        Board copyBoard = new Board(initialBoard);
//        aStar = new AStar(copyBoard.getTiles()[0].length, copyBoard.getTiles().length);
        moves = MovePath.solveBoard(copyBoard);
        movesCounter = 1;
        Int4 move = moves[0];
        if (move != null) {
            AStarHeuristic.board.switchTiles(move.x, move.y, move.z, move.w);
        }
        lastBoardId = 0;

//        Int4.printArr(moves);
    }

    public static boolean isBoardEqualToCurrent(Board compareBoard) {
        if (compareBoard.equals(board)) {
            Int4 move = moves[movesCounter];
            if (move == null) {
//                System.out.println("stack ----------------------");
//                TestBoard.printBoard(compareBoard);
//                TestBoard.printBoard(board);
//                System.out.println("^----------------------^");
                return true;
            }
            board.switchTiles(move.x, move.y, move.z, move.w);
            movesCounter++;
            return true;
        }
        return false;
    }

    public static int aStarHeuristic(Board compareBoard) {
        return compareBoard.getAStarScore();
    }

    public static int aStarHeuristic2(Board compareBoard) {
        if (compareBoard.getAStarScore() != -1) {
            return compareBoard.getAStarScore();
        }

        if (compareBoard.equals(board)) {
            Int4 move = moves[movesCounter];
            board.switchTiles(move.x, move.y, move.z, move.w);
            movesCounter++;
            compareBoard.setAStarScore(0);
            return 0;
        } else {
            compareBoard.setAStarScore(1);
            return 1;
        }
    }
}
