package HW1.tests;

import HW1.Action;
import HW1.Board;
import HW1.State;

public class TestState {

    private static void printActionsOfState(String boardStr, String boardName) {
        System.out.println(boardName);
        Board board = new Board(boardStr);
        State state = new State(board);
        Action[] actions = state.actions();
        for (Action action : actions) {
            System.out.println(action);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            printActionsOfState(ExampleBoardStrings.BOARDS_3_BY_3[i], "BOARD " + i);

        }
    }
}
