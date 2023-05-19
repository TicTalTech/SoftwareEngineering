package HW1.math_util;

/**
 * a 2D integer vector
 */
public class Int2
{
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

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void printArr(Int2[] arr) {
        for (Int2 vec : arr) {
            System.out.print(vec + ", ");
        }
        System.out.println();
    }
}
