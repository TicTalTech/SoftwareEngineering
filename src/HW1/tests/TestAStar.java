package HW1.tests;

import HW1.a_star.AStar;
import HW1.a_star.TileStatus;
import HW1.math_util.Int2;

public class TestAStar
{
    public static void main(String[] args) {
        AStar aStar = new AStar(10, 10);
        // , int startX, int startY, int goalX, int goalY
        aStar.initEdges(0, 0, 6, 6);
//        aStar.setStartX(0);
//        aStar.setStartY(0);
//        aStar.setGoalX(6);
//        aStar.setGoalY(6);

        for (int i = 2; i <= 9; i++) {
            aStar.getTiles()[3][i].setStatus(TileStatus.WALL);
        }
        for (int i = 3; i <= 7; i++) {
            aStar.getTiles()[i][5].setStatus(TileStatus.WALL);
        }
        aStar.getTiles()[1][0].setStatus(TileStatus.WALL);
        aStar.getTiles()[1][1].setStatus(TileStatus.WALL);
        aStar.findPath();
        aStar.changePathChars();
        aStar.printBoard();
        System.out.println("The path length is: " + aStar.pathLength());

        Int2[] path = aStar.exportPath();
        for (Int2 node : path) {
            System.out.println(node);
        }
    }
}
