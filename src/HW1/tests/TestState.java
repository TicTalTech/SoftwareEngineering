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

    private static void testResults(String boardStr) {
        State state = new State(new Board(boardStr));
        TestBoard.printBoard(state.getBoard());
        Action[] actions = state.actions();
        for (Action action : actions) {
            System.out.println(action);
        }
        State newState1 = state.result(actions[0]);
        TestBoard.printBoard(newState1.getBoard());
        State newState2 = state.result(actions[1]);
        TestBoard.printBoard(newState2.getBoard());

    }

    public static void main(String[] args) {
//        for (int i = 0; i < 9; i++) {
//            printActionsOfState(ExampleBoardStrings.BOARDS_3_BY_3[i], "BOARD " + (i + 1));
//        }
        testResults(ExampleBoardStrings.BOARD1);
    }
}
