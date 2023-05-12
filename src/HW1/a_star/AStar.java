package HW1.a_star;

import HW1.math.Int2;
import HW1.math.MathUtil;

public class AStar {

    private Tile[][] tiles;
    private int goalX, goalY;
    private int startX, startY;

//    private DynamicNodesArray toExplore;

    public AStar(int width, int height) {
        tiles = new Tile[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = new Tile(x, y);
            }
        }
//        toExplore = new DynamicNodesArray();
    }

    public void initEdges(int startX, int startY, int goalX, int goalY) {
        this.startX = startX;
        this.startY = startY;
        this.goalX = goalX;
        this.goalY = goalY;
    }

    public int pathLength() {
        int length = 0;
        Tile endTile = tiles[goalY][goalX];
        while (endTile.getParent() != null) {
            endTile = endTile.getParent();
            length++;
        }
        return length + 1;
    }

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

    public int countWalls() {
        int count = 0;
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getStatus() == TileStatus.WALL) {
                    count++;
                }
            }
        }
        return count;
    }

    public void unexplore() {
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
        System.out.println("from: " + (new Int2(startX, startY)) + " to: " + (new Int2(goalX, goalY)));
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                System.out.print(tile + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Tile findPath() {
        tiles[startY][startX].setStatus(TileStatus.TO_EXPLORE);
        boolean foundPath = false;
        while (!isDoneExploring() && !foundPath) {
//            printBoard();
            foundPath = explore();
        }
        return tiles[goalY][goalX];
//        changePathChars(tiles[goalY][goalX]);
//        printBoard();
    }

    public boolean isDoneExploring() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getStatus() == TileStatus.TO_EXPLORE) {
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

    public void changePathChars() {
        Tile tile = tiles[goalY][goalX];
        while (tile.getParent() != null) {
            tile.setStatus(TileStatus.PATH);
            tile = tile.getParent();
        }
        tile.setStatus(TileStatus.PATH);
    }

    public boolean isInsideBoard(int x, int y) {
        return x >= 0 && y >= 0 && x < tiles[0].length && y < tiles.length;
    }


    private boolean isTraversable(Tile tile) {
        return tile.getStatus() == TileStatus.EMPTY || tile.getStatus() == TileStatus.UNFAVORABLE;
    }

    private boolean isNotTraversable(Tile tile) {
        return tile.getStatus() == TileStatus.WALL || tile.getStatus() == TileStatus.EXPLORED;
    }
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
            if(isNotTraversable(neighbourTile)) {
                continue;
            }
            int startDist = tileToExplore.getDistToStart();
            if(neighbourTile.getStatus() == TileStatus.EMPTY) {
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
