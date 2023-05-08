package HW1.tests;

import HW1.Action;
import HW1.Board;
import HW1.Node;
import HW1.State;

public class TestNode {
    public static void main(String[] args) {
        Board b1 = new Board(ExampleBoardStrings.BOARD12);
        State s1 = new State(b1);
        Node n1 = new Node(s1, null, null);
//        int val = n1.linear_conflicts_cols();
       /* Node[] n1_result = n1.expand();
        for (int i = 0; i < n1_result.length; i++) {
            Board b = n1_result[i].getState().getBoard();
            // TestBoard(b)
            TestBoard.printBoard(b);
            Action[] actions = n1_result[i].getState().actions();
            for (Action action : actions)
                System.out.println(action);
        }
        for (int i = 0; i < n1_result.length; ++i) {
            Node[] n2_result = n1_result[i].expand();
            for (int j = 0; j < n2_result.length; ++j)
                TestBoard.printBoard(n2_result[j].getState().getBoard());
        }*/
    }
}
