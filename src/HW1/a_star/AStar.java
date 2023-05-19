package HW1.a_star;

import HW1.math_util.Int2;
import HW1.math_util.MathUtil;

/**
 * an implementation of the A* algorithm
 */
public class AStar
{
    private Tile[][] tiles;
    private int goalX, goalY;
    private int startX, startY;

    /**
     * create and AStar object
     *
     * @param width  - the width of the board that needs solving
     * @param height - the height of the board that needs solving
     */
    public AStar(int width, int height) {
        tiles = new Tile[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = new Tile(x, y);
            }
        }
    }


    /**
     * used to initialize the aStar beginning and end positions (from where and to where we want to go)
     */
    public void initEdges(int startX, int startY, int goalX, int goalY) {
        this.startX = startX;
        this.startY = startY;
        this.goalX = goalX;
        this.goalY = goalY;
    }

    /**
     * after the algorithm found a valid path from start to goal, it would count how many steps the path is
     *
     * @return path length
     */
    public int pathLength() {
        int length = 0;
        Tile endTile = tiles[goalY][goalX];
        while (endTile.getParent() != null) {
            endTile = endTile.getParent();
            length++;
        }
        return length + 1;
    }

    /**
     * after finding a path, the method is used to create an array of the steps that were taken on the path
     *
     * @return - an array that contains all the steps taken
     */
    public Int2[] exportPath() {
        int pathLength = pathLength();
        Tile endTile = tiles[goalY][goalX];
        Int2[] pathArr = new Int2[pathLength];
        for (int i = pathLength - 1; i >= 0; i--) {
            pathArr[i] = new Int2(endTile.getX(), endTile.getY());
            endTile = endTile.getParent();
        }
        return pathArr;
    }

    public void resetBoardBeforeSearch() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getStatus() == TileStatus.EXPLORED || tile.getStatus() == TileStatus.TO_EXPLORE || tile.getStatus() == TileStatus.PATH) {
                    tile.setStatus(TileStatus.EMPTY);
                }
                tile.setParent(null);
            }
        }
    }

    public void printBoard() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                System.out.print(tile + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * runs the A* algorithm
     */
    public void findPath() {
        tiles[startY][startX].setStatus(TileStatus.TO_EXPLORE);
        boolean foundPath = false;
        while (!isDoneExploring() && !foundPath) {
            foundPath = explore();
        }
    }

    /**
     * checks if found a valid path from begging to end
     *
     * @return true if the path is valid
     */
    private boolean isDoneExploring() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getStatus() == TileStatus.TO_EXPLORE) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * looks at the searching board for the best tile to explore (based on normal A* implementation)
     */
    private Tile getBestTile() {
        Tile bestTile = null;
        int bestScore = Integer.MAX_VALUE;
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getStatus() != TileStatus.TO_EXPLORE) {
                    continue;
                }
                if (tile.getScore() < bestScore) {
                    bestTile = tile;
                    bestScore = tile.getScore();
                } else if (tile.getScore() == bestScore) {
                    if (tile.getDistToTarget() < bestTile.getDistToTarget()) {
                        bestTile = tile;
                        bestScore = tile.getScore();
                    }
                }
            }
        }
        return bestTile;
    }

    /**
     * used before printing the board (after running the A*) to display the path it found
     */
    public void changePathChars() {
        Tile tile = tiles[goalY][goalX];
        while (tile.getParent() != null) {
            tile.setStatus(TileStatus.PATH);
            tile = tile.getParent();
        }
        tile.setStatus(TileStatus.PATH);
    }

    private boolean isInsideBoard(int x, int y) {
        return x >= 0 && y >= 0 && x < tiles[0].length && y < tiles.length;
    }


    private boolean isTraversable(Tile tile) {
        return tile.getStatus() == TileStatus.EMPTY || tile.getStatus() == TileStatus.UNFAVORABLE;
    }

    private boolean isNotTraversable(Tile tile) {
        return tile.getStatus() == TileStatus.WALL || tile.getStatus() == TileStatus.EXPLORED;
    }

    /**
     * finding the best tile to check and adding its neighbors to be explored
     *
     * @return true if found the target
     */
    public boolean explore() {
        Tile tileToExplore = getBestTile();
        tileToExplore.setStatus(TileStatus.EXPLORED);
        if (tileToExplore.getX() == goalX && tileToExplore.getY() == goalY) {
            return true; // found target
        }
        final int[][] deltaList = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] delta : deltaList) {
            int neighbourX = tileToExplore.getX() + delta[0];
            int neighbourY = tileToExplore.getY() + delta[1];
            if (!isInsideBoard(neighbourX, neighbourY)) {
                continue;
            }
            Tile neighbourTile = tiles[neighbourY][neighbourX];
//            if (neighbourTile.getStatus() == TileStatus.EXPLORED || neighbourTile.getStatus() == TileStatus.WALL) {
            if (isNotTraversable(neighbourTile)) {
                continue;
            }
            int startDist = tileToExplore.getDistToStart();
            if (neighbourTile.getStatus() == TileStatus.EMPTY) {
                startDist++;
            } else if (neighbourTile.getStatus() == TileStatus.UNFAVORABLE) {
                startDist += 5;
            }
            int goalDist = MathUtil.manhattanDistance(neighbourX, neighbourY, goalX, goalY);
            int neighbourNewScore = startDist + goalDist;
            if (isTraversable(neighbourTile) || neighbourNewScore <= tileToExplore.getScore()) {
                if (!isTraversable(neighbourTile) && neighbourNewScore == tileToExplore.getScore()) {
                    if (goalDist > tileToExplore.getDistToTarget()) {
                        continue;
                    }
                }
                neighbourTile.setScore(neighbourNewScore);
                neighbourTile.setDistToTarget(goalDist);
                neighbourTile.setDistToStart(startDist);

                neighbourTile.setParent(tileToExplore);
                if (neighbourTile.getStatus() != TileStatus.TO_EXPLORE) {
                    neighbourTile.setStatus(TileStatus.TO_EXPLORE);
                }
            }
        }
        return false;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
