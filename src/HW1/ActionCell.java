package HW1;

import HW1.Action;
import HW1.Board;

public class ActionCell {
    private Action action;
    private boolean isActive;

    public void setAction(Action action) {
        this.action = action;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Action getAction() {
        return action;
    }

    public boolean isActive() {
        return isActive;
    }
}
