package HW1;

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
}
