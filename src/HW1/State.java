package HW1;

public class State {
    private Board board;

    public State(Board board) {
        this.board = board;
    }

    public State(State other) {
        this.board = new Board(other.board);
    }

    public State result(Action action) {
        State newState = new State(this);
        switch (action.getDirection()) {
            case UP:
                newState.board.switchTiles(
                        action.getTileX(), action.getTileY(), action.getTileX(), action.getTileY() - 1);
                newState.board.setEmptyY(action.getTileY());
                break;
            case DOWN:
                newState.board.switchTiles(
                        action.getTileX(), action.getTileY(), action.getTileX(), action.getTileY() + 1);
                newState.board.setEmptyY(action.getTileY());

                break;
            case RIGHT:
                newState.board.switchTiles(
                        action.getTileX(), action.getTileY(), action.getTileX() + 1, action.getTileY());
                newState.board.setEmptyX(action.getTileX());
                break;
            case LEFT:
                newState.board.switchTiles(
                        action.getTileX(), action.getTileY(), action.getTileX() - 1, action.getTileY());
                newState.board.setEmptyX(action.getTileX());

                break;

        }
        return newState;
    }

    public Board getBoard() {
        return board;
    }

    public ActionCell[] actions() {
        ActionCell[] actions = new ActionCell[4];
        int numberOfActions = 0;
        int x, y;
        // move up action
        if (board.getEmptyY() != board.getTiles().length - 1) {
            x = board.getEmptyX();
            y = board.getEmptyY() + 1;
            actions[0] = Board.actionsBank.newAction(board.getTiles()[y][x], Direction.UP, x, y);
//            actions[0] = new Action(board.getTiles()[y][x], Direction.UP, x, y);
            numberOfActions++;
        }
        // move down action
        if (board.getEmptyY() != 0) {
            x = board.getEmptyX();
            y = board.getEmptyY() - 1;
//            actions[1] = new Action(board.getTiles()[y][x], Direction.DOWN, x, y);
            actions[1] = Board.actionsBank.newAction(board.getTiles()[y][x], Direction.DOWN, x, y);

            numberOfActions++;
        }
        // move right action
        if (board.getEmptyX() != 0) {
            x = board.getEmptyX() - 1;
            y = board.getEmptyY();
//            actions[2] = new Action(board.getTiles()[y][x], Direction.RIGHT, x, y);
            actions[2] = Board.actionsBank.newAction(board.getTiles()[y][x], Direction.RIGHT, x, y);

            numberOfActions++;
        }
        // move up action
        if (board.getEmptyX() != board.getTiles()[0].length - 1) {
            x = board.getEmptyX() + 1;
            y = board.getEmptyY();
//            actions[3] = new Action(board.getTiles()[y][x], Direction.LEFT, x, y);
            actions[3] = Board.actionsBank.newAction(board.getTiles()[y][x], Direction.LEFT, x, y);

            numberOfActions++;
        }
        ActionCell[] condensedActions = new ActionCell[numberOfActions];
        int actionsIndex = 0;
        for (ActionCell temp : actions) {
            if (temp == null) {
                continue;
            }
            condensedActions[actionsIndex] = temp;
            actionsIndex++;
        }

        return condensedActions;
    }

    public boolean isGoal() {
        int num = 1;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[0].length; j++) {
                if (i != board.getBoard().length - 1 || j != board.getBoard()[0].length - 1) {
                    if (board.getBoard()[i][j].getValue() != num)
                        return false;
                    num++;
                } else {
                    if (board.getBoard()[i][j].getValue() != Board.EMPTY)
                        return false;
                }
            }
        }
        return true;
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
