package HW1;

public class ActionsBank {
    private ActionCell[] actionsBank;

    public ActionsBank() {
        actionsBank = new ActionCell[20];
    }

    public boolean isEmpty(int i) {
        return actionsBank[i] == null || !actionsBank[i].isActive();
    }

    public int findEmpty() {
        for (int i = 0; i < actionsBank.length; i++) {
            if (isEmpty(i)) {
                return i;
            }
        }
        return -1;
    }

    public void doubleArr() {
        ActionCell[] newArr = new ActionCell[actionsBank.length * 2];
        for (int i = 0; i < actionsBank.length; i++) {
            newArr[i] = actionsBank[i];
        }
        actionsBank = newArr;
        System.out.println("The new length is " + actionsBank.length);
    }

    public ActionCell newAction(Tile tile, Direction direction, int tileX, int tileY) {
        int index = findEmpty();
        if (findEmpty() == -1) {
            doubleArr();
            index = findEmpty();
        }
        if (actionsBank[index] == null) {
            actionsBank[index] = new ActionCell();
            actionsBank[index].setAction(new Action());
        }
        actionsBank[index].getAction().createAction(tile, direction, tileX, tileY);
        actionsBank[index].setActive(true);
        return actionsBank[index];
    }
}
