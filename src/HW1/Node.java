package HW1;


public class Node {
    private State state;
    private Node parent;
    private Action action;

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

    public Node[] expand() {
        Action[] actions = state.actions();
        Node[] expanded = new Node[actions.length];
        for (int i = 0; i < actions.length; i++) {
            expanded[i] = new Node(state.result(actions[i]), this, actions[i]);
        }
        return expanded;
    }

    public static int[] findNumberInBoard(Board board, int value) {
        int height = board.getBoard().length;
        int width = board.getBoard()[0].length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board.getBoard()[y][x].getValue() == value) {
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }

    public int heuristicValue() {
//        return heuristicValueManhattanDistance();
//        return heuristicValueManhattanDistanceConsiderEmpty();
        return heuristicValueRecur(this, 5);
//        return smartManhattanDistance(this.getState().getBoard());
    }

    public static int heuristicValueRecur(Node node, int depth) {
        if (node.getState().isGoal()) {
            return -depth;
        }
        if (depth == 1) {
            return Node.smartManhattanDistance(node.getState().getBoard());
//            return node.heuristicValueManhattanDistanceConsiderEmpty();
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
                int[] realPosition = Node.findNumberInBoard(board, targetNumber);
                int manhattanDistance = MathUtil.abs(wantedX - realPosition[0]) + MathUtil.abs(wantedY - realPosition[1]);
                sumDistance += manhattanDistance;
            }
        }
        return sumDistance;
    }

    public int linear_conflicts_rows() {
        int lengthOfRows = state.getBoard().getTiles()[0].length;
        int numOfRows = state.getBoard().getTiles().length;
        Tile[][] board = state.getBoard().getTiles();
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
        return 2 * counter;
    }

    public int linear_conflicts_cols() {
        int lengthOfCols = state.getBoard().getTiles().length;
        int numOfCols = state.getBoard().getTiles()[0].length;
        Tile[][] board = state.getBoard().getTiles();
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
        return 2 * counter;
    }

    public int heuristicValueManhattanDistanceConsiderEmpty() {
        int sumDistance = 0;
        int height = state.getBoard().getBoard().length;
        int width = state.getBoard().getBoard()[0].length;
        int[] emptyPosition = findNumberInBoard(state.getBoard(), Board.EMPTY);
        for (int wantedY = 0; wantedY < height; wantedY++) {
            for (int wantedX = 0; wantedX < width; wantedX++) {
                int targetNumber = wantedY * width + wantedX + 1;
                //if it is the empty tile (target)
                if (wantedX == width - 1 && wantedY == height - 1) {
                    targetNumber = Board.EMPTY;
                }
                int[] realPosition = findNumberInBoard(state.getBoard(), targetNumber);
                int manhattanDistance = MathUtil.abs(wantedX - realPosition[0]) +
                        MathUtil.abs(wantedY - realPosition[1]);
                int manhattanDistanceToZero = MathUtil.abs(emptyPosition[0] - realPosition[0]) + MathUtil.abs(emptyPosition[1] - realPosition[1]);
                sumDistance += (manhattanDistance + manhattanDistanceToZero);
            }
        }
        return sumDistance;
    }
}
