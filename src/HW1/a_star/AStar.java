package HW1.a_star;

import HW1.math.MathUtil;

public class AStar {

    private Tile[][] tiles;
    private int goalX, goalY;
    private int startX, startY;

//    private DynamicNodesArray toExplore;

    public AStar(int width, int height, int startX, int startY, int goalX, int goalY) {
        this.startX = startX;
        this.startY = startY;
        this.goalX = goalX;
        this.goalY = goalY;
        tiles = new Tile[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = new Tile(x, y);
            }
        }
        tiles[startY][startX].setStatus(Tile.TO_EXPLORE);

//        toExplore = new DynamicNodesArray();
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

    public void findPath() {
        boolean foundPath = false;
        while (!isDoneExploring() && !foundPath) {
//            printBoard();
            foundPath = explore();
        }
        changePathChars(tiles[goalY][goalX]);
        printBoard();
    }

    public boolean isDoneExploring() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getStatus() == Tile.TO_EXPLORE) {
                    return false;
                }
            }
        }
        return true;
    }

    public Tile getBestTile() {
        Tile bestTile = null;
        int bestScore = Integer.MAX_VALUE;
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getStatus() != Tile.TO_EXPLORE) {
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

    public void changePathChars(Tile tile) {
        while (tile.getParent() != null) {
            tile.setStatus(Tile.PATH);
            tile = tile.getParent();
        }
        tile.setStatus(Tile.PATH);
    }

    public boolean isInsideBoard(int x, int y) {
        return x >= 0 && y >= 0 && x < tiles[0].length && y < tiles.length;
    }

    public boolean explore() {
        Tile tileToExplore = getBestTile();
        tileToExplore.setStatus(Tile.EXPLORED);
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
            if (neighbourTile.getStatus() == Tile.EXPLORED || neighbourTile.getStatus() == Tile.WALL) {
                continue;
            }
            int startDist = tileToExplore.getDistToStart() + 1;
            int goalDist = MathUtil.manhattanDistance(neighbourX, neighbourY, goalX, goalY);
            int neighbourNewScore = startDist + goalDist;
            if (neighbourTile.getStatus() == Tile.EMPTY || neighbourNewScore <= tileToExplore.getScore()) {
                if (neighbourTile.getStatus() != Tile.EMPTY && neighbourNewScore == tileToExplore.getScore()) {
                    if (goalDist > tileToExplore.getDistToTarget()) {
                        continue;
                    }
                }
                neighbourTile.setScore(neighbourNewScore);
                neighbourTile.setDistToTarget(goalDist);
                neighbourTile.setDistToStart(startDist);

                neighbourTile.setParent(tileToExplore);
                if (neighbourTile.getStatus() != Tile.TO_EXPLORE) {
                    neighbourTile.setStatus(Tile.TO_EXPLORE);
                }
            }


        }
        return false;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getGoalX() {
        return goalX;
    }

    public void setGoalX(int goalX) {
        this.goalX = goalX;
    }

    public int getGoalY() {
        return goalY;
    }

    public void setGoalY(int goalY) {
        this.goalY = goalY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }
}
