package HW1;

public class Action {
    private Tile tile;
    private Direction direction;

    public Action(Tile tile, Direction direction) {
        this.tile = tile;
        this.direction = direction;
    }

    public String toString() {
        return "Move " + tile + " " + direction;
    }
}
