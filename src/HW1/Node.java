package HW1;


import HW1.math_util.Int2;
import HW1.math_util.MathUtil;

import static HW1.a_star.AStarHeuristic.aStarHeuristic;

public class Node
{
    /**
     * This class represents a node. A node holds the current state its parent node and the action that
     * was done to get to the current state.
     */
    private State state;
    private Node parent;
    private Action action;

    /**
     * Constructor
     *
     * @param state  The current state
     * @param parent The nodes parent node
     * @param action The action that was done to get to the current state
     */
    public Node(State state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    public Node getParent() {
        return this.parent;
    }

    public Action getAction() {
        return this.action;
    }

    public State getState() {
        return this.state;
    }

    /**
     * performs all the possible actions that can currently be performed on the board and puts the new nodes
     * that are the result of those actions in an array
     *
     * @return returns an array of nodes that hold all the possible nodes based on the actions that can be performed
     */
    public Node[] expand() {
        Action[] actions = state.actions();
        Node[] expanded = new Node[actions.length];
        for (int i = 0; i < actions.length; i++) {
            expanded[i] = new Node(state.result(actions[i]), this, actions[i]);
        }
        return expanded;
    }

    public static Int2 findNumberInBoard(Board board, int value) {
        int height = board.getBoard().length;
        int width = board.getBoard()[0].length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board.getBoard()[y][x].getValue() == value) {
                    return new Int2(x, y);
                }
            }
        }
        return null;
    }

    /**
     * @return
     */
    public int heuristicValue() {
//        System.out.println(numberOfCorrectTiles(this.state.getBoard()));
//        return heuristicValueManhattanDistance();
//        return heuristicValueManhattanDistanceConsiderEmpty();
//        return heuristicValueRecur(this, 2);

//        return smartManhattanDistance(this.getState().getBoard());
        // return numberOfInversions(this.state.getBoard());
//        return 0;
//        return veryGoodFunctionHopefully(this);
//        return aStarHeuristic(this.state.getBoard());

        Board board = this.state.getBoard();
        if (board.getTiles()[0].length == 1 || board.getTiles().length == 1) {
            return smartManhattanDistance(this.getState().getBoard());
        } else {
            return aStarHeuristic(this.state.getBoard());
        }
    }

    public static int numberOfInversions(Board board) {
        int numberOfInversions = 0;
        int height = board.getBoard().length;
        int width = board.getBoard()[0].length;
        int boardSize = width * height;
        for (int i = 0; i < boardSize; i++) {
            int x1 = i % width;
            int y1 = i / width;
            int value1 = board.getBoard()[y1][x1].getValue();
            if (value1 == Board.EMPTY) {
//                value1 = boardSize;
                continue;
            }

            for (int j = 0; j < i; j++) {
                int x2 = j % width;
                int y2 = j / width;
                int value2 = board.getBoard()[y2][x2].getValue();
                if (value2 == Board.EMPTY) {
//                    value2 = boardSize;
                    continue;
                }
                if (value2 > value1) {
                    numberOfInversions++;
                }
            }
        }
        return numberOfInversions;
    }

    public static int numberOfCorrectTiles(Board board) {
        int countDifference = 0;
        int height = board.getBoard().length;
        int width = board.getBoard()[0].length;
        for (int wantedY = 0; wantedY < height; wantedY++) {
            for (int wantedX = 0; wantedX < width; wantedX++) {
                int targetNumber = wantedY * width + wantedX + 1;
                //if it is the empty tile (target)
                if (wantedX == width - 1 && wantedY == height - 1) {
                    targetNumber = Board.EMPTY;
                }
                int realValue = board.getBoard()[wantedY][wantedX].getValue();
                if (targetNumber != realValue) {
                    countDifference++;
                }
            }
        }
        return countDifference;
    }

    public static int heuristicValueRecur(Node node, int depth) {
        if (node.getState().isGoal()) {
            return -depth;
        }
        if (depth == 1) {
            return Node.smartManhattanDistance(node.getState().getBoard());
//            return node.heuristicValueManhattanDistanceConsiderEmpty();
//            return Node.veryGoodFunctionHopefully(node);
        }

        Node[] children = node.expand();
        int[] scores = new int[children.length];
        for (int i = 0; i < children.length; i++) {
            Node child = children[i];
            int value = heuristicValueRecur(child, depth - 1);
            scores[i] = value;
        }
        return MathUtil.min(scores);
    }

    public static int smartManhattanDistance(Board board) {
        return board.getManhattanScore();
    }

    public static int heuristicValueManhattanDistance(Board board) {
        int sumDistance = 0;
        int height = board.getBoard().length;
        int width = board.getBoard()[0].length;
        for (int wantedY = 0; wantedY < height; wantedY++) {
            for (int wantedX = 0; wantedX < width; wantedX++) {
                int targetNumber = wantedY * width + wantedX + 1;
                //if it is the empty tile (target)
                if (wantedX == width - 1 && wantedY == height - 1) {
                    targetNumber = Board.EMPTY;
                }
                Int2 realPosition = Node.findNumberInBoard(board, targetNumber);
                int manhattanDistance = MathUtil.abs(wantedX - realPosition.x) + MathUtil.abs(wantedY - realPosition.y);
                sumDistance += manhattanDistance;
            }
        }
        return sumDistance;
    }

    public static int veryGoodFunctionHopefully(Node node) {
        int manScore = smartManhattanDistance(node.state.getBoard());

        if (node.getParent() == null || node.getParent().getAction() == null) {
            int rowScore = linear_conflicts_rows(node.state.getBoard());
            int colScore = linear_conflicts_cols(node.state.getBoard());

            return manScore + rowScore + colScore;
        }
        Direction dir = node.
                getAction().
                getDirection();
        int rowScore;
        if (dir == Direction.UP || dir == Direction.DOWN) {
            rowScore = linear_conflicts_rows(node.state.getBoard());
        } else {
            rowScore = node.getParent().getState().getBoard().getRowScore();
        }

        int colScore;
        if (dir == Direction.LEFT || dir == Direction.RIGHT) {
            colScore = linear_conflicts_cols(node.state.getBoard());
        } else {
            colScore = node.getParent().getState().getBoard().getColScore();
        }
        node.state.getBoard().setRowScore(rowScore);
        node.state.getBoard().setColScore(colScore);

        return manScore + rowScore + colScore;
    }

    public static int linear_conflicts_rows(Board stateBoard) {
        int lengthOfRows = stateBoard.getTiles()[0].length;
        int numOfRows = stateBoard.getTiles().length;
        Tile[][] board = stateBoard.getTiles();
        int max = 0;
        Boolean empty = false;
        int maxIndex = 0;
        int counter = 0;
        int numOfConflicts[] = new int[lengthOfRows];
        int conflicts[][] = new int[lengthOfRows][lengthOfRows];
        for (int row = 0; row < numOfRows; ++row) {
            for (int i = 0; i < lengthOfRows; ++i) {
                for (int j = i; j < lengthOfRows; ++j) {
                    if (board[row][j].getValue() <= (row + 1) * lengthOfRows
                            && board[row][j].getValue() >= (row) * lengthOfRows + 1
                            && board[row][i].getValue() <= (row + 1) * lengthOfRows
                            && board[row][i].getValue() >= (row) * lengthOfRows + 1
                            && board[row][j].getValue() < board[row][i].getValue()) {
                        conflicts[i][j]++;
                        conflicts[j][i]++;
                        numOfConflicts[i]++;
                        numOfConflicts[j]++;
                    }
                }
            }
            empty = false;
            while (!empty) {
                empty = true;
                for (int i = 0; i < lengthOfRows; ++i) {
                    if (numOfConflicts[i] != 0)
                        empty = false;
                }
                if (empty)
                    break;

                for (int i = 0; i < lengthOfRows; ++i) {
                    if (numOfConflicts[i] >= max) {
                        max = numOfConflicts[i];
                        maxIndex = i;
                    }
                }
                for (int i = 0; i < lengthOfRows; ++i) {
                    conflicts[maxIndex][i] = 0;
                    if (conflicts[i][maxIndex] > 0) {
                        conflicts[i][maxIndex]--;
                        numOfConflicts[i]--;
                    }
                }
                numOfConflicts[maxIndex] = 0;
                maxIndex = 0;
                max = 0;
                counter++;
            }
            for (int i = 0; i < lengthOfRows; ++i)
                for (int j = 0; j < lengthOfRows; ++j)
                    conflicts[i][j] = 0;
            for (int i = 0; i < numOfConflicts.length; ++i)
                numOfConflicts[i] = 0;
        }
        stateBoard.setRowScore(2 * counter);
        return 2 * counter;
    }

    public static int linear_conflicts_cols(Board stateBoard) {
        int lengthOfCols = stateBoard.getTiles().length;
        int numOfCols = stateBoard.getTiles()[0].length;
        Tile[][] board = stateBoard.getTiles();
        int max = 0;
        Boolean empty = false;
        int maxIndex = 0;
        int counter = 0;
        int numOfConflicts[] = new int[lengthOfCols];
        int conflicts[][] = new int[lengthOfCols][lengthOfCols];
        for (int col = 0; col < numOfCols; ++col) {
            for (int i = 0; i < lengthOfCols; ++i) {
                for (int j = i; j < lengthOfCols; ++j) {
                    if (board[i][col].getValue() % numOfCols == col + 1
                            && board[j][col].getValue() % numOfCols == col + 1
                            && board[j][col].getValue() < board[i][col].getValue()) {
                        conflicts[i][j]++;
                        conflicts[j][i]++;
                        numOfConflicts[i]++;
                        numOfConflicts[j]++;
                    }
                }
            }
            empty = false;
            while (!empty) {
                empty = true;
                for (int i = 0; i < lengthOfCols; ++i) {
                    if (numOfConflicts[i] != 0)
                        empty = false;
                }
                if (empty)
                    break;

                for (int i = 0; i < lengthOfCols; ++i) {
                    if (numOfConflicts[i] >= max) {
                        max = numOfConflicts[i];
                        maxIndex = i;
                    }
                }
                for (int i = 0; i < lengthOfCols; ++i) {
                    conflicts[maxIndex][i] = 0;
                    if (conflicts[i][maxIndex] > 0) {
                        conflicts[i][maxIndex]--;
                        numOfConflicts[i]--;
                    }
                }
                numOfConflicts[maxIndex] = 0;
                maxIndex = 0;
                max = 0;
                counter++;
            }
            for (int i = 0; i < lengthOfCols; ++i)
                for (int j = 0; j < lengthOfCols; ++j)
                    conflicts[i][j] = 0;
            for (int i = 0; i < numOfConflicts.length; ++i)
                numOfConflicts[i] = 0;
        }
        stateBoard.setColScore(2 * counter);
        return 2 * counter;
    }

    public int heuristicValueManhattanDistanceConsiderEmpty() {
        int sumDistance = 0;
        int height = state.getBoard().getBoard().length;
        int width = state.getBoard().getBoard()[0].length;
        Int2 emptyPosition = findNumberInBoard(state.getBoard(), Board.EMPTY);
        for (int wantedY = 0; wantedY < height; wantedY++) {
            for (int wantedX = 0; wantedX < width; wantedX++) {
                int targetNumber = wantedY * width + wantedX + 1;
                //if it is the empty tile (target)
                if (wantedX == width - 1 && wantedY == height - 1) {
                    targetNumber = Board.EMPTY;
                }
                Int2 realPosition = findNumberInBoard(state.getBoard(), targetNumber);
                int manhattanDistance = MathUtil.abs(wantedX - realPosition.x) +
                        MathUtil.abs(wantedY - realPosition.y);
                int manhattanDistanceToZero = MathUtil.abs(emptyPosition.x - realPosition.x) +
                        MathUtil.abs(emptyPosition.y - realPosition.y);
                sumDistance += (manhattanDistance + manhattanDistanceToZero);
            }
        }
        return sumDistance;
    }
}
