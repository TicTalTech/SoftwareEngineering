package HW1.tests;

import HW1.Action;
import HW1.Direction;
import HW1.Tile;

public class TestAction {
    public static void main(String[] args) {
        Action action1 = new Action(new Tile(7), Direction.UP, 0, 0);
        System.out.println(action1);
    }
}
