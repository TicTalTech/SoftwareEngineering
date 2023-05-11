package HW1.tests;

import HW1.Board;
import HW1.MovePath;
import HW1.math.Int4;

public class TestMovePath {

    public static void main(String[] args) {
        Board board = new Board("7 5 4|_ 3 2|8 1 6");
        Int4[] moves = MovePath.solveBoard(board);
        System.out.println("-=|finish|=-");
        Int4.printArr(moves);
        // 7 5 4
        // _ 3 2
        // 8 1 6
    }
}
