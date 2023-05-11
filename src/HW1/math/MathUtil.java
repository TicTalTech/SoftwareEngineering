package HW1.math;

public class MathUtil {
    public static int abs(int value) {
        if (value >= 0) {
            return value;
        } else {
            return -value;
        }
    }

    public static int min(int[] arr) {
        int minVal = Integer.MAX_VALUE;
        for (int val : arr) {
            if (val < minVal) {
                minVal = val;
            }
        }
        return minVal;
    }

    public static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return abs(x1 - x2) + abs(y1 - y2);
    }

    public static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y1) * (y1 - y1));
    }
}
