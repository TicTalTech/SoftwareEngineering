package HW1.tests;

import HW1.Board;

public class TestBoard {
    public static void main(String[] args) {
        String s = "7 5 4|6 3 2|8 1 _";
        Board b = new Board(ExampleBoardStrings.BOARD2);
        for (int i = 0; i < b.getTiles().length; ++i) {
            for (int j = 0; j < b.getTiles()[0].length; ++j) {
                System.out.println(b.getBoard()[i][j].getValue());
            }
        }
    }
}
