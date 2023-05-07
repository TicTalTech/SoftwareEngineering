package HW1;

public class State {
    private Board board;

    public State(Board board) {
        this.board = board;
    }

    public State result(Action action) {
        // TODO - implement
        return null;
    }

    public Board getBoard() {
        return board;
    }

    public Action[] actions() {
        Action[] actions = new Action[4];
        int numberOfActions = 0;
        // move up action
        if (board.getEmptyY() != board.getTiles().length - 1) {
            actions[0] = new Action(board.getTiles()[board.getEmptyY() + 1][board.getEmptyX()], Direction.UP);
            numberOfActions++;
        }
        // move down action
        if (board.getEmptyY() != 0) {
            actions[1] = new Action(board.getTiles()[board.getEmptyY() - 1][board.getEmptyX()], Direction.DOWN);
            numberOfActions++;
        }
        // move right action
        if (board.getEmptyX() != 0) {
            actions[2] = new Action(board.getTiles()[board.getEmptyY()][board.getEmptyX() - 1], Direction.RIGHT);
            numberOfActions++;
        }
        // move up action
        if (board.getEmptyX() != board.getTiles()[0].length - 1) {
            actions[3] = new Action(board.getTiles()[board.getEmptyY()][board.getEmptyX() + 1], Direction.LEFT);
            numberOfActions++;
        }
        Action[] condensedActions = new Action[numberOfActions];
        int actionsIndex = 0;
        for (Action temp : actions) {
            if (temp == null) {
                continue;
            }
            condensedActions[actionsIndex] = temp;
            actionsIndex++;
        }

        return condensedActions;
    }

    public boolean isGoal() {
        // TODO - implement
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
