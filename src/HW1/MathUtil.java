package HW1;

/**
 * usefully math functions
 */
public class MathUtil {

    public static int pow(int value, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= value;
        }
        return result;
    }

    public static int abs(int value) {
        if (value >= 0) {
            return value;
        } else {
            return -value;
        }
    }

    public static int min(int x, int y) {
        if (x < y) {
            return x;
        }
        return y;
    }

    public static int max(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
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
}
