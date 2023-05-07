package HW1.tests;

import HW1.Board;
import HW1.Node;
import HW1.State;

public class TestNode {
    public static void main(String[] args) {
        Board b1 = new Board(ExampleBoardStrings.BOARD1);
        State s1 = new State(b1);
        Node n1 = new Node(s1, null, null);
        Node[] n1_result = n1.expand();
        for (int i = 0; i < n1_result.length; ++i)
            TestBoard.printBoard(n1_result[i].getState().getBoard());
    }
}
