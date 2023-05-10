package HW1.tests;

import HW1.Board;

import static HW1.Node.numberOfInversions;
import static HW1.tests.ExampleBoardStrings.SOLVED33;

public class TestInversions {
    public static void main(String[] args) {

        System.out.println(numberOfInversions(new Board(SOLVED33)));
        System.out.println(numberOfInversions(new Board("_ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40")));
        System.out.println(numberOfInversions(new Board("_ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 35 26 27 28 29 30 31 32 33 34 25 36 37 38 39 40")));
        System.out.println(numberOfInversions(new Board("1 2 3|4 5 6|8 7 _")));
        System.out.println(numberOfInversions(new Board("1 2 3|4 _ 6|7 5 8")));
        System.out.println(numberOfInversions(new Board("7 5 4|_ 3 2|8 6 1")));


    }
}
