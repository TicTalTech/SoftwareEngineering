package HW1.tests;

import HW1.Board;

public class TestBoard {
    public static void printBoard(Board b) {
        for (int i = 0; i < b.getTiles().length; ++i) {
            for (int j = 0; j < b.getTiles()[0].length; ++j) {
                int value = b.getBoard()[i][j].getValue();
                System.out.print(" " + (value == 0 ? "_" : value));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String s = "7 5 4|6 3 2|8 1 _";
        Board b = new Board(ExampleBoardStrings.BOARD5);
        printBoard(b);
    }
}