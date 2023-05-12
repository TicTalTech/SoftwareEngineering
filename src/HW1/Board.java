package HW1;

import HW1.math.MathUtil;

import java.util.Arrays;


public class Board {
    public int getRowScore() {
        return rowScore;
    }

    public void setRowScore(int rowScore) {
        this.rowScore = rowScore;
    }

    public int getColScore() {
        return colScore;
    }

    public void setColScore(int colScore) {
        this.colScore = colScore;
    }

    public static final int EMPTY = 0;
    public static final char EMPTY_CHAR = '_';

    private Tile[][] tiles;

    private int emptyX;
    private int emptyY;

    private int manhattanScore;
    private int rowScore;
    private int colScore;

    private int aStarScore = -1;

    private int boardId;

    public int getBoardId()
    {
        return boardId;
    }

    public void setBoardId(int boardId)
    {
        this.boardId = boardId;
    }

    private static boolean shouldRunSmartManhattanDistance = false;

    public int getAStarScore()
    {
        return aStarScore;
    }

    public void setAStarScore(int aStarScore)
    {
        this.aStarScore = aStarScore;
    }

    public Board(Board other) {
//        tiles = other.tiles.clone();
        tiles = new Tile[other.tiles.length][other.tiles[0].length];
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
//                tiles[y][x] = new Tile(other.tiles[y][x]);
                tiles[y][x] = other.tiles[y][x];

            }
        }
        emptyX = other.emptyX;
        emptyY = other.emptyY;
        this.manhattanScore = other.manhattanScore;
    }


    public Board(String s) {
        int value;
        int numOfRows = 0;
        int numOfCols = 0;
        int index = 0;
        if (s.contains("|")) {
            while (s.charAt(index) != '|') {
                if (s.charAt(index) == ' ')
                    numOfCols++;
                index++;
            }
        } else {
            for (char ch : s.toCharArray()) {
                if (ch == ' ')
                    numOfCols++;
            }
        }
        index = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '|')
                numOfRows++;
            index++;
        }
        numOfRows++;
        numOfCols++;
        tiles = new Tile[numOfRows][numOfCols];
        StringBuilder valueS = new StringBuilder();
        int row = 0;
        int col = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == EMPTY_CHAR) {
                tiles[row][col] = new Tile(EMPTY);
                emptyY = row;
                emptyX = col;
                col++;
                continue;
            }
            if (s.charAt(i) == ' ') {
                if (!valueS.toString().equals("")) {
                    value = Integer.parseInt(valueS.toString());
                    valueS = new StringBuilder();
                    tiles[row][col] = new Tile(value);
                    col++;
                }
                continue;
            }
            if (s.charAt(i) == '|') {
                if (!valueS.toString().equals("")) {
                    value = Integer.parseInt(valueS.toString());
                    valueS = new StringBuilder();
                    tiles[row][col] = new Tile(value);
                }
                row++;
                col = 0;
                continue;
            }
            valueS.append(s.charAt(i));
        }
        if (s.charAt(s.length() - 1) != EMPTY_CHAR) {
            value = Integer.parseInt(valueS.toString());
            tiles[row][col] = new Tile(value);
        }
        manhattanScore = Node.heuristicValueManhattanDistance(this);
    }

    public int getManhattanScore() {
        return manhattanScore;
    }

    public void switchTiles(int x1, int y1, int x2, int y2) {
        if(shouldRunSmartManhattanDistance)
        {
            int value1 = tiles[y1][x1].getValue() - 1;

            int value1TargetX = value1 % tiles[0].length;
            int value1TargetY = value1 / tiles[0].length;
            int value2TargetX = tiles[0].length - 1;
            int value2TargetY = tiles.length - 1;

            int value1OldDist = MathUtil.abs(value1TargetX - x1) + MathUtil.abs(value1TargetY - y1);
            int value2OldDist = MathUtil.abs(value2TargetX - x2) + MathUtil.abs(value2TargetY - y2);
            int value1NewDist = MathUtil.abs(value1TargetX - x2) + MathUtil.abs(value1TargetY - y2);
            int value2NewDist = MathUtil.abs(value2TargetX - x1) + MathUtil.abs(value2TargetY - y1);

            manhattanScore -= value1OldDist;
            manhattanScore -= value2OldDist;
            manhattanScore += value1NewDist;
            manhattanScore += value2NewDist;

        }

        Tile temp = tiles[y1][x1];
        tiles[y1][x1] = tiles[y2][x2];
        tiles[y2][x2] = temp;
    }

    public Tile[][] getBoard() {
        return tiles;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    public int getEmptyX() {
        return emptyX;
    }

    public void setEmptyX(int emptyX) {
        this.emptyX = emptyX;
    }

    public int getEmptyY() {
        return emptyY;
    }

    public void setEmptyY(int emptyY) {
        this.emptyY = emptyY;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

}
