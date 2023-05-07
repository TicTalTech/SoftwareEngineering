package HW1;

public class Action {
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
