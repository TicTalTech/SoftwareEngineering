package HW1.a_star;

/**
 * A tile used by A* to track its tiles
 */
public class Tile
{
    private int distToTarget;
    private int distToStart;
    private int score;
    private TileStatus status;
    private int x, y;
    private Tile parent;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        status = TileStatus.EMPTY;
    }

    public String toString() {
        switch (status) {
            case WALL:
                return "#";
            case TO_EXPLORE:
//                return "" + score;
                return "?";
            case EMPTY:
                return ".";
            case EXPLORED:
//                return "" + distToTarget;
                return "-";
            case PATH:
                return "+";
        }
        return "!";
    }

    public Tile getParent() {
        return parent;
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }

    public TileStatus getStatus() {
        return status;
    }

    public void setStatus(TileStatus status) {
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getScore() {
        return score;
    }

    public int getDistToTarget() {
        return distToTarget;
    }

    public void setDistToTarget(int distToTarget) {
        this.distToTarget = distToTarget;
    }

    public int getDistToStart() {
        return distToStart;
    }

    public void setDistToStart(int distToStart) {
        this.distToStart = distToStart;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
