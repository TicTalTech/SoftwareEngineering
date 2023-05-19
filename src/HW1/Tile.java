package HW1;

public class Tile {
    private final int value;

    /**
     * Constructor
     *
     * @param value The value of the tile
     */
    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}