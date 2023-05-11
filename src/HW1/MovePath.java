package HW1;

import HW1.a_star.AStar;
import HW1.a_star.Tile;
import HW1.a_star.TileStatus;
import HW1.math.Int2;
import HW1.math.Int4;
import HW1.tests.TestBoard;

import static HW1.Solver.solvingSteps;

public class MovePath {


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

    public static Int4[] solveBoard(Board board) {
        Int4[] moves = new Int4[board.getTiles().length * board.getTiles()[0].length * board.getTiles().length
                * board.getTiles()[0].length];
        int movesCounter = 0;
        TestBoard.printBoard(board);
        SwitchStep[] steps = solvingSteps(board);
        AStar aStar = new AStar(board.getTiles().length, board.getTiles()[0].length);
        for (SwitchStep step : steps) {
            if (step == null) {
                break;
            }
            System.out.println("Number step: " + step);
            Int2 numberPosition = Node.findNumberInBoard(board, step.getValue());
            aStar.unexplore();
            aStar.initEdges(numberPosition.x, numberPosition.y, step.getCol(), step.getRow());
            Tile endOfPathTile = aStar.findPath();
            Int2[] path = aStar.exportPath();
            aStar.changePathChars();
            aStar.printBoard();
            aStar.getTiles()[numberPosition.y][numberPosition.x].setStatus(TileStatus.WALL);
            for (int i = 1; i < path.length; i++) {
                System.out.println("moving empty space");
                TestBoard.printBoard(board);
                Int2 emptySpaceGoal = path[i];
                System.out.println("moving empty space to " + emptySpaceGoal);
                aStar.unexplore();
                Int2 emptyStartPosition = Node.findNumberInBoard(board, Board.EMPTY);
                System.out.println("moving empty space from " + emptyStartPosition);
                aStar.initEdges(emptyStartPosition.x, emptyStartPosition.y, emptySpaceGoal.x, emptySpaceGoal.y);
                aStar.printBoard();
                Tile endOfEmptyPathTile = aStar.findPath();
                aStar.changePathChars();
                aStar.printBoard();
                Int2[] emptySpacePath = aStar.exportPath();
                Int2.printArr(emptySpacePath);
                for (int j = 0; j < emptySpacePath.length - 1; j++) {
                    Int4 move = new Int4(emptySpacePath[j].x, emptySpacePath[j].y,
                            emptySpacePath[j + 1].x, emptySpacePath[j + 1].y);
                    board.switchTiles(move.x, move.y, move.z, move.w);
                    moves[movesCounter] = move;
                    movesCounter++;
                }
                Int4 move = new Int4(emptySpacePath[emptySpacePath.length - 1].x, emptySpacePath[emptySpacePath.length - 1].y,
                        numberPosition.x, numberPosition.y);
                board.switchTiles(move.x, move.y, move.z, move.w);
                moves[movesCounter] = move;
                movesCounter++;
            }
            aStar.getTiles()[numberPosition.y][numberPosition.x].setStatus(TileStatus.EMPTY);
            for (int i = 0; i < path.length - 1; i++) {
                Int4 move = new Int4(path[i].x, path[i].y, path[i + 1].x, path[i + 1].y);
                board.switchTiles(move.x, move.y, move.z, move.w);
                moves[movesCounter] = move;
                movesCounter++;
            }
            aStar.getTiles()[path[path.length - 1].y][path[path.length - 1].x].setStatus(TileStatus.WALL);

        }
        TestBoard.printBoard(board);
        return moves;
    }

}
