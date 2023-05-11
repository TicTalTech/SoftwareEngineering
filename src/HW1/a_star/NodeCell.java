package HW1.a_star;

import HW1.math.Int2;

public class NodeCell {

    private Tile tile;
    private boolean isActive;


    public void setActive(boolean active) {
        isActive = active;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public boolean isActive() {
        return isActive;
    }
}