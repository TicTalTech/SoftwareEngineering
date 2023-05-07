package HW1.tests;

import HW1.Action;
import HW1.Board;
import HW1.State;

public class TestState {

    private static void printActionsOfState(String boardStr) {
        Board board = new Board(boardStr);
        State state = new State(board);
        Action[] actions = state.actions();
        for (Action action : actions) {
            System.out.println(action);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printActionsOfState(ExampleBoardStrings.BOARD1);
//        printActionsOfState(ExampleBoardStrings.BOARD2);
        printActionsOfState(ExampleBoardStrings.BOARD3);
        printActionsOfState(ExampleBoardStrings.BOARD4);
        printActionsOfState(ExampleBoardStrings.BOARD5);


    }
}
