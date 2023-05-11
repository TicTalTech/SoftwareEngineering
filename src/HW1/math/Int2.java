package HW1.math;

public class Int2 {
    public int x, y;

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Int2() {
        this(0, 0);
    }

    public Int2(int x, int y) {
        set(x, y);
    }
}
