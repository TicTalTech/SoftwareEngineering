package HW3;

public class Point implements Cloneable {
    private int x;
    private int y;

    public Point(int x) {
        this.x = x;
        this.y = x;
    }
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) {
        Point original = new Point(3, 5);

        try {
            Point clone = (Point) original.clone();
            clone.setX(10);

            System.out.println("Original: " + original);  // (3, 5)
            System.out.println("Clone: " + clone);        // (10, 5)
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}


