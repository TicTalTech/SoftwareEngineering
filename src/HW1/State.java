package HW1;

public class State {
    private Board board;


    public State result(Action action) {
        // TODO - implement
        return null;
    }

    public Action[] actions() {
        Action[] actions = new Action[4];
        
        // TODO - implement
        return null;
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
