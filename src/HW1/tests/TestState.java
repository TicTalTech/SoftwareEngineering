package HW1.tests;

import HW1.Board;

public class TestState {
    public static void main(String[] args) {
        String s = "7 5 4|6 3 2|8 1 _";
        Board b = new Board(s);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.println(b.getBoard()[i][j].getValue());
            }
        }
    }
}
