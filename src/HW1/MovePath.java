package HW1;

import HW1.a_star.AStar;
import HW1.a_star.Tile;
import HW1.a_star.TileStatus;
import HW1.math.Int2;
import HW1.math.Int4;
import HW1.math.MathUtil;

import static HW1.Solver.solvingSteps;

public class MovePath {

    private static int movesCounter;


    public static void fixLast2By2() {
        // TODO
    }

    public static void addMove(Board board, Int4[] moves, int x1, int y1, int x2, int y2) {
        Int4 move = new Int4(x1, y1,
                x2, y2);
        board.switchTiles(move.x, move.y, move.z, move.w);
        moves[movesCounter] = move;
        movesCounter++;
//        System.out.println("did move: " + move + " (" + movesCounter + ")");
        int valueFrom = board.getTiles()[move.y][move.x].getValue();
        int valueTo = board.getTiles()[move.w][move.z].getValue();

//        System.out.println("doing move: " + valueFrom + " -> " + valueTo);
//        if (valueTo != 0) {
//            System.out.println("=============================================================================================");
//        }
//        TestBoard.printBoard(board);

    }

    public static boolean isValueCloseBy(Board board, int value, int closeToX, int closeToY) {
         final Int2[] area = {
                 new Int2(-1, -1),
                 new Int2(0, -1),
                 new Int2(1, -1),
                 new Int2(-1, 0),
                 new Int2(0, 0),
                 new Int2(1, 0),
                 new Int2(-1, 1),
                 new Int2(0, 1),
                 new Int2(1, 1)};

         for (Int2 delta : area) {
             int x = closeToX + delta.x;
             int y = closeToY + delta.y;
             if (x < 0 || y < 0 || x >= board.getTiles()[0].length || y >= board.getTiles().length) {
                 continue;
             }
             if (board.getTiles()[y][x].getValue() == value){
                 return true;
             }
         }
         return false;
    }

    public static void moveNumberToPlace(AStar aStar, Int4[] moves, Board board, SwitchStep step, SwitchStep nextStep) {
        if(nextStep != null && step.getMoveStatus() == SwitchStep.CORNER && isValueCloseBy(board, nextStep.getValue(), step.getCol(), step.getRow())) {
//        if (nextStep != null && nextStep.getValue() == board.getTiles()[step.getRow()][step.getCol()].getValue()) {
//            System.out.println("-----moving " + nextStep.getValue() + " out of the way-----");
//            final int delta = 2;
//            int moveToX = MathUtil.min(board.getTiles()[0].length - 1, nextStep.getCol() + delta);
//            int moveToY = MathUtil.min(board.getTiles().length - 1, nextStep.getRow() + delta);
            moveNumberToPlace(aStar, moves, board,
                    new SwitchStep(nextStep.getValue(), board.getTiles().length - 1,
                            board.getTiles()[0].length - 1, SwitchStep.OUT_OF_THE_WAY),
                    null);
            aStar.getTiles()[board.getTiles().length - 1][board.getTiles()[0].length - 1].setStatus(TileStatus.EMPTY);
//            TestBoard.printBoard(board);
        }
//        if (step.getMoveStatus() == SwitchStep.CORNER) {
//            aStar.getTiles()[step.getSafeY()][step.getSafeX()].setStatus(TileStatus.WALL);
//        }
//        System.out.println("move a number step: " + step);
//            TestBoard.printBoard(board);
        Int2 numberPosition = Node.findNumberInBoard(board, step.getValue());
        aStar.unexplore();
        aStar.initEdges(numberPosition.x, numberPosition.y, step.getCol(), step.getRow());
        Tile endOfPathTile = aStar.findPath();
        Int2[] path = aStar.exportPath();
        aStar.changePathChars();
//            System.out.println("there are " + path.length + " path points");
        // move white spaces
        for (int i = 1; i < path.length; i++) {
//                System.out.println("before moving white:");
//                TestBoard.printBoard(board);
            aStar.getTiles()[path[i-1].y][path[i-1].x].setStatus(TileStatus.WALL);
            Int2 emptySpaceGoal = path[i];
            aStar.unexplore();
            Int2 emptyStartPosition = Node.findNumberInBoard(board, Board.EMPTY);
            aStar.initEdges(emptyStartPosition.x, emptyStartPosition.y, emptySpaceGoal.x, emptySpaceGoal.y);
            Tile endOfEmptyPathTile = aStar.findPath();
            Int2[] emptySpacePath = aStar.exportPath();
            for (int j = 0; j < emptySpacePath.length - 1; j++) {
                addMove(board, moves,emptySpacePath[j].x, emptySpacePath[j].y,
                        emptySpacePath[j + 1].x, emptySpacePath[j + 1].y);
//                aStar.printBoard();
            }
//            System.out.println("v - final move - v");
            addMove(board, moves, path[i].x, path[i].y, path[i - 1].x, path[i - 1].y);
//            aStar.printBoard();

            aStar.getTiles()[path[i-1].y][path[i-1].x].setStatus(TileStatus.EMPTY);
        }
//        if (step.getMoveStatus() == SwitchStep.FINAL) {
            aStar.getTiles()[path[path.length - 1].y][path[path.length - 1].x].setStatus(TileStatus.WALL);
//        }
//            else {
//                aStar.getTiles()[path[path.length - 1].y][path[path.length - 1].x].setStatus(TileStatus.UNFAVORABLE);
//            }
        if (step.getMoveStatus() == SwitchStep.CORNER) {
            aStar.getTiles()[step.getSafeY()][step.getSafeX()].setStatus(TileStatus.EMPTY);
        }
    }

    public static Int4[] solveBoard(Board board) {
        Int4[] moves = new Int4[MathUtil.pow(board.getTiles().length * board.getTiles()[0].length, 3)];
        movesCounter = 0;
//        TestBoard.printBoard(board);
        SwitchStep[] steps = solvingSteps(board);
        AStar aStar = new AStar(board.getTiles()[0].length, board.getTiles().length);
        // move numbers
        for (int stepNumber = 0; stepNumber < steps.length; stepNumber++) {
            SwitchStep step = steps[stepNumber];
            if (step == null) { break; }
            SwitchStep nextStep = null;
            if (stepNumber + 1 < steps.length) {
                nextStep = steps[stepNumber + 1];
            }
            moveNumberToPlace(aStar, moves, board, step, nextStep);
        }

        System.out.println("finish solving with " + movesCounter + " moves");
//        TestBoard.printBoard(board);

        return moves;
    }
}
