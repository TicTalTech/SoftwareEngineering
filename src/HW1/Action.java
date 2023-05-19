package HW1;

public class Action {
    /**
     * This class represents an action that can be performed on the board. Each action is defined by a tile
     * and the direction to move it
     */
    private Tile tile;
    private Direction direction;

    public Direction getDirection() {
        return direction;
    }

    private int tileX, tileY;

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    /**
     * Constructor
     *
     * @param tile      The tile that the action will be preformed on
     * @param direction The direction that the tile moves
     * @param tileX     The tiles column number
     * @param tileY     The tiles row number
     */
    public Action(Tile tile, Direction direction, int tileX, int tileY) {
        this.tile = tile;
        this.direction = direction;
        this.tileX = tileX;
        this.tileY = tileY;
    }

    public String toString() {
        return "Move " + tile + " " + direction;
    }
}
