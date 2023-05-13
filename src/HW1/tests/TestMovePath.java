package HW1.tests;

import HW1.Board;
import HW1.a_star.MovePath;
import HW1.math.Int4;

public class TestMovePath
{

    public static void main(String[] args) {
        //Board board = new Board("7 5 4|_ 3 2|8 1 6");
        //Board board = new Board("6 4 7|8 5 _|3 2 1");
//        Board board = new Board("6 7 5 1|2 10 4 11|9 3 8 _");
//        Board board = new Board("1 6 4 15|5 10 _ 13|9 11 3 7|12 8 2 14");
//        Board board = new Board("1 9 _ 12 10 5 6|8 11 2 3 4 13 7");
//        Board board = new Board("1 2 3 12 5 6|7 8 9 4 11 17|13 14 15 _ 10 16");
//        Board board = new Board("29 7 14 9 11 16|23 17 24 22 18 20|5 3 21 13 27 15|6 4 12 1 19 28|26 10 8 25 2 _");
        Board board = new Board("2 3 8 4 14 13 6|16 9 17 _ 10 5 7|1 11 15 18 12 19 20");
//        Board board = new Board("1 2 23 5 21 14 7 6 16|10 11 3 22 15 35 9 34 _|28 20 13 31 4 33 26 17 8|29 19 12 32 30 24 18 27 25");
//        Board board = new Board("");


        Int4[] moves = MovePath.solveBoard(board);
        System.out.println("-=|finish|=-");
//        Int4.printArr(moves);
        // 7 5 4
        // _ 3 2
        // 8 1 6


        // 7 5 4
        // 3 1 2
        // 8 _ 6
    }
}
