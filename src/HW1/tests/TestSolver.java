package HW1.tests;

import HW1.Board;

import HW1.Solver;
import HW1.Vector;

import static HW1.Solver.*;
import static HW1.tests.TestBoard.printBoard;

public class TestSolver {
    public static void main(String args[]) {
        Board b = new Board(ExampleBoardStrings.BOARD13);
        printBoard(b);
        Vector[] p = Solver.Paths(b);
        for (int i = 0; i < p.length; ++i) {
            if (p[i] != null) {
                System.out.print(p[i].getValue() + ",");
                System.out.print(p[i].getRow() + ",");
                System.out.print(p[i].getCol() + ",");
                System.out.println();
            }

        }
    }
}
