package HW1.a_star;

public class TestAStar {
    public static void main(String[] args) {
        AStar aStar = new AStar(10, 10, 0, 0, 6, 6);
        for (int i = 2; i <= 9; i++) {
            aStar.getTiles()[3][i].setStatus(Tile.WALL);
        }
        for (int i = 3; i <= 7; i++) {
            aStar.getTiles()[i][5].setStatus(Tile.WALL);
        }
        aStar.getTiles()[1][0].setStatus(Tile.WALL);
        aStar.getTiles()[1][1].setStatus(Tile.WALL);


        aStar.findPath();
    }
}
