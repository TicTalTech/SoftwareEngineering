package HW1;

/**
 * gives the "bigger picture" of how to solve a board
 */
public class MovePath {
    private static int movesCounter;

    /**
     * add the switch between (x1, y1) and (x2, y2) to moves and switch them - (the tiles are just next to each other)
     *
     * @param board - the board need changing
     * @param moves - the moves array that needed to add to
     * @param x1 - the x coordinate of the first point
     * @param y1 - the y coordinate of the first point
     * @param x2 - the x coordinate of the second point
     * @param y2 - the y coordinate of the second point
     */
    public static void addMove(Board board, Int4[] moves, int x1, int y1, int x2, int y2) {
        Int4 move = new Int4(x1, y1, x2, y2);
        board.switchTiles(move.x, move.y, move.z, move.w);
        moves[movesCounter] = move;
        movesCounter++;
    }

    /**
     * used in order to check if a value is on or next to a target location
     */
    private static boolean isValueCloseBy(Board board, int value, int closeToX, int closeToY) {
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
            if (board.getTiles()[y][x].getValue() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param aStar    - the already initialized A* that belongs to this board
     * @param moves    - the array that keep tracks of all the moves that the method would add its moved to
     * @param board    - the board that we are moving
     * @param step     - the action we are preforming
     * @param nextStep - the next action we would preform
     */
    public static void moveNumberToPlace(AStar aStar, Int4[] moves, Board board, SwitchStep step, SwitchStep nextStep) {
        // check if there is a "conflict" tile in the way and moves it to the right down most corner if needed
        // a "conflict" tile is a tile that needs to end up next to the tile we are trying to move at the time and that
        // is close to the target position of the tile we are moving
        if (nextStep != null && step.getMoveStatus() == SwitchStepStatus.CORNER && isValueCloseBy(board, nextStep.getValue(), step.getCol(), step.getRow())) {
            moveNumberToPlace(aStar, moves, board,
                    new SwitchStep(nextStep.getValue(), board.getTiles().length - 1,
                            board.getTiles()[0].length - 1, SwitchStepStatus.OUT_OF_THE_WAY), null);
            aStar.getTiles()[board.getTiles().length - 1][board.getTiles()[0].length - 1].setStatus(TileStatus.EMPTY);
        }

        Int2 numberPosition = Node.findNumberInBoard(board, step.getValue());
        aStar.resetBoardBeforeSearch();
        aStar.initEdges(numberPosition.x, numberPosition.y, step.getCol(), step.getRow());
        aStar.findPath();
        Int2[] path = aStar.exportPath();
        // move white spaces to be in front of the tile we want to move
        // (in order to switch them and advance the wanted tile)
        for (int i = 1; i < path.length; i++) {
            aStar.getTiles()[path[i - 1].y][path[i - 1].x].setStatus(TileStatus.WALL);
            Int2 emptySpaceGoal = path[i];
            aStar.resetBoardBeforeSearch();
            Int2 emptyStartPosition = Node.findNumberInBoard(board, Board.EMPTY);
            aStar.initEdges(emptyStartPosition.x, emptyStartPosition.y, emptySpaceGoal.x, emptySpaceGoal.y);
            aStar.findPath();
            Int2[] emptySpacePath = aStar.exportPath();
            for (int j = 0; j < emptySpacePath.length - 1; j++) {
                addMove(board, moves, emptySpacePath[j].x, emptySpacePath[j].y,
                        emptySpacePath[j + 1].x, emptySpacePath[j + 1].y);
            }
            addMove(board, moves, path[i].x, path[i].y, path[i - 1].x, path[i - 1].y);

            aStar.getTiles()[path[i - 1].y][path[i - 1].x].setStatus(TileStatus.EMPTY);
        }
        if (step.getMoveStatus() != SwitchStepStatus.OUT_OF_THE_WAY) {
            aStar.getTiles()[path[path.length - 1].y][path[path.length - 1].x].setStatus(TileStatus.WALL);
        }
    }

    /**
     * given a board, the method would solve it and give back an array od the moves it did in order to solve it
     *
     * @param board - the board needed solving
     * @return - the array of moves that solve the board
     */
    public static Int4[] solveBoard(Board board) {
        Int4[] moves = new Int4[MathUtil.pow(board.getTiles().length * board.getTiles()[0].length, 3)];
        movesCounter = 0;
        Solver solver = new Solver();
        SwitchStep[] steps = solver.solvingSteps(board);
        AStar aStar = new AStar(board.getTiles()[0].length, board.getTiles().length);
        // move each tile to its correct place
        for (int stepNumber = 0; stepNumber < steps.length; stepNumber++) {
            SwitchStep step = steps[stepNumber];
            if (step == null) {
                break;
            }
            SwitchStep nextStep = null;
            if (stepNumber + 1 < steps.length) {
                nextStep = steps[stepNumber + 1];
            }
            moveNumberToPlace(aStar, moves, board, step, nextStep);
        }
        return moves;
    }
}
