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

    private int[] findNumberInBoard(Board board, int value) {
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
        return heuristicValueManhattanDistance();
    }

    public int heuristicValueManhattanDistance() {
        int sumDistance = 0;
        int height = state.getBoard().getBoard().length;
        int width = state.getBoard().getBoard()[0].length;
        for (int wantedY = 0; wantedY < height; wantedY++) {
            for (int wantedX = 0; wantedX < width; wantedX++) {
                int targetNumber = wantedY * width + wantedX + 1;
                //if it is the empty tile (target)
                if (wantedY == width - 1 && wantedY == height - 1) {
                    targetNumber = Board.EMPTY;
                }
                int[] realPosition = findNumberInBoard(state.getBoard(), targetNumber);
                int manhattanDistance = MathUtil.abs(wantedX - realPosition[0]) + MathUtil.abs(wantedY - realPosition[1]);
                sumDistance += manhattanDistance;
            }
        }
        return sumDistance;
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
                if (wantedY == width - 1 && wantedY == height - 1) {
                    targetNumber = Board.EMPTY;
                }
                int[] realPosition = findNumberInBoard(state.getBoard(), targetNumber);
                int manhattanDistance = MathUtil.abs(wantedX - realPosition[0]) + MathUtil.abs(wantedX - realPosition[0]);
                int manhattanDistanceToZero = MathUtil.abs(emptyPosition[0] - realPosition[0]) + MathUtil.abs(emptyPosition[1] - realPosition[1]);
                sumDistance += (manhattanDistance + manhattanDistanceToZero);
            }
        }
        return sumDistance;
    }
}
