package HW1;

/**
 * This class represents a node. A node holds the current state its parent node and the action that
 * was done to get to the current state.
 */
public class Node {

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
     * figure out the heuristic value based on the board size
     *
     * @return heuristic value of the belonging board
     */
    public int heuristicValue() {
        Board board = this.state.getBoard();
        if (board.getTiles()[0].length == 1 || board.getTiles().length == 1) {
            return smartManhattanDistance(this.getState().getBoard());
        } else {
            return AStarHeuristic.aStarHeuristic(this.state.getBoard());
        }
    }

    /**
     * returns the manhattan  distance of the board that we already calculated before
     *
     * @param board
     * @return
     */
    public static int smartManhattanDistance(Board board) {
        return board.getManhattanScore();
    }

    /**
     * calculates the sum of the manhattan distances of all the tiles in the board
     *
     * @param board - the board we want to run the function on
     * @return the sum of the manhattan distances
     */
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
}
