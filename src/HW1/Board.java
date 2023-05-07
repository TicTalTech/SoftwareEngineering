package HW1;

import java.util.Arrays;


public class Board {
    public static final int EMPTY = 0;
    public static final char EMPTY_CHAR = '_';

    private Tile[][] tiles;

    private int emptyX;
    private int emptyY;

    public Board(Board other) {
        tiles = new Tile[other.tiles.length][other.tiles[0].length];
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                tiles[y][x] = new Tile(other.tiles[y][x]);
            }
        }
        emptyX = other.emptyX;
        emptyY = other.emptyY;
    }


    public Board(String s) {
        int value;
        int numOfRows = 0;
        int numOfCols = 0;
        int index = 0;
        while (s.charAt(index) != '|') {
            if (s.charAt(index) == ' ')
                numOfCols++;
            index++;
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
        String valueS = "";
        char digit;
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
                if (valueS != "") {
                    value = Integer.parseInt(valueS);
                    valueS = "";
                    tiles[row][col] = new Tile(value);
                    col++;
                }
                continue;
            }
            if (s.charAt(i) == '|') {
                if (valueS != "") {
                    value = Integer.parseInt(valueS);
                    valueS = "";
                    tiles[row][col] = new Tile(value);
                }
                row++;
                col = 0;
                continue;
            }
            valueS += s.charAt(i);
        }
        if (s.charAt(s.length() - 1) != EMPTY_CHAR) {
            value = Integer.parseInt(valueS);
            tiles[row][col] = new Tile(value);
        }
    }

    public void switchTiles(int x1, int y1, int x2, int y2) {
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
