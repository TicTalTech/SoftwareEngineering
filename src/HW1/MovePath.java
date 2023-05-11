package HW1;

import HW1.a_star.AStar;
import HW1.a_star.Tile;
import HW1.a_star.TileStatus;
import HW1.math.Int2;
import HW1.math.Int4;
import HW1.math.MathUtil;
import HW1.tests.TestBoard;

import static HW1.Solver.solvingSteps;

public class MovePath {

    private static int movesCounter;

    public static void movePath(Board board, Tile endTile) {
        while (endTile.getParent() != null) {
            Tile parent = endTile.getParent();
            board.switchTiles(endTile.getX(), endTile.getY(), parent.getX(), parent.getY());
            endTile = parent;
        }
    }

    public static void fixLast2By2() {
        // TODO
    }

    public static void addMove(Board board, Int4[] moves, int x1, int y1, int x2, int y2) {
        Int4 move = new Int4(x1, y1,
                x2, y2);
        board.switchTiles(move.x, move.y, move.z, move.w);
        moves[movesCounter] = move;
        movesCounter++;
    }

    public static Int4[] solveBoard(Board board) {
        Int4[] moves = new Int4[MathUtil.pow(board.getTiles().length * board.getTiles()[0].length, 3)];
//        Int4[] moves = new Int4[200];
        movesCounter = 0;
        TestBoard.printBoard(board);
        SwitchStep[] steps = solvingSteps(board);
        AStar aStar = new AStar(board.getTiles()[0].length, board.getTiles().length);
        // move numbers
        for (SwitchStep step : steps) {
            if (step == null) {
                break;
            }
            System.out.println("move a number step: " + step);
            TestBoard.printBoard(board);
            Int2 numberPosition = Node.findNumberInBoard(board, step.getValue());
            aStar.unexplore();
            aStar.initEdges(numberPosition.x, numberPosition.y, step.getCol(), step.getRow());
            Tile endOfPathTile = aStar.findPath();
            Int2[] path = aStar.exportPath();
            aStar.changePathChars();
            System.out.println("there are " + path.length + " path points");
            // move white spaces
            for (int i = 1; i < path.length; i++) {
                System.out.println("before moving white:");
                TestBoard.printBoard(board);
                aStar.getTiles()[path[i-1].y][path[i-1].x].setStatus(TileStatus.WALL);
                Int2 emptySpaceGoal = path[i];
                aStar.unexplore();
                Int2 emptyStartPosition = Node.findNumberInBoard(board, Board.EMPTY);
                aStar.initEdges(emptyStartPosition.x, emptyStartPosition.y, emptySpaceGoal.x, emptySpaceGoal.y);
                Tile endOfEmptyPathTile = aStar.findPath();
                Int2[] emptySpacePath = aStar.exportPath();
                for (int j = 0; j < emptySpacePath.length - 1; j++) {
                    addMove(board, moves, emptySpacePath[j].x, emptySpacePath[j].y,
                            emptySpacePath[j + 1].x, emptySpacePath[j + 1].y);
                }
                System.out.println("after moving white:");
                TestBoard.printBoard(board);
                addMove(board, moves, path[i].x, path[i].y,
                        path[i - 1].x, path[i - 1].y);
                aStar.getTiles()[path[i-1].y][path[i-1].x].setStatus(TileStatus.EMPTY);
                System.out.println("after switch:");
                TestBoard.printBoard(board);
            }
//            for (int i = 0; i < path.length - 1; i++) {
//                Int4 move = new Int4(path[i].x, path[i].y, path[i + 1].x, path[i + 1].y);
//                board.switchTiles(move.x, move.y, move.z, move.w);
//                moves[movesCounter] = move;
//                movesCounter++;
//            }
            aStar.getTiles()[path[path.length - 1].y][path[path.length - 1].x].setStatus(TileStatus.WALL);

        }
        System.out.println("before 2x2 switch:");
        TestBoard.printBoard(board);
        Int2 numberPosition = Node.findNumberInBoard(board, Board.EMPTY);
        int width = board.getTiles()[0].length;
        int height = board.getTiles().length;
        if (numberPosition.x == width - 1 && numberPosition.y == height - 2) {
            addMove(board, moves, width - 1, height - 2, width - 1, height - 1);
        }
        if (numberPosition.x == width - 2 && numberPosition.y == height - 1) {
            addMove(board, moves, width - 2, height - 1, width - 1, height - 1);
        }
        if (numberPosition.x == width - 2 && numberPosition.y == height - 2) {
            addMove(board, moves, width - 2, height - 2, width - 2, height - 1);
            addMove(board, moves, width - 2, height - 1, width - 1, height - 1);
        }


        System.out.println("final board:");
        TestBoard.printBoard(board);
        System.out.println("finish solving with " + movesCounter + " moves");

        return moves;
    }

}
