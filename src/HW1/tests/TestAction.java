package HW1.tests;

import HW1.Action;
import HW1.Direction;
import HW1.Tile;

public class TestAction {
    public static void main(String[] args) {
        Action action1 = new Action(new Tile(), Direction.UP);
        System.out.println(action1);
    }
}