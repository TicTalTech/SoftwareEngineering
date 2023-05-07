package HW1;

import java.util.Arrays;


public class Board {
    final int EMPTY = 0;
    final char EMPTY_CHAR = '_';

    private Tile[][] tiles;

    private int emptyX;
    private int emptyY;


    public Board(String s) {
        int value;
        String valueS = "";
        char digit;
        int row = 0;
        int col = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == EMPTY_CHAR) {
                tiles[row][col] = new Tile(EMPTY);
                col++;
                continue;
            }
            if (s.charAt(i) == ' ') {
                value = Integer.parseInt(valueS);
                valueS = "";
                tiles[row][col] = new Tile(value);
                col++;
                continue;
            }
            if (s.charAt(i) == '|') {
                value = Integer.parseInt(valueS);
                valueS = "";
                tiles[row][col] = new Tile(value);
                row++;
                col = 0;
                continue;
            }
            valueS += s.charAt(i);
        }
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

}
