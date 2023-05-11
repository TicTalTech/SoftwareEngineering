package HW1.math;

public class Int4 {
    public int x, y, z, w;

    public void set(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Int4() {
        this(0, 0, 0, 0);
    }

    public Int4(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public String toString() {
        return "(" + x + ", " + y + ")->( " + z + ", " + w + ")";
    }

    public static void printArr(Int4[] arr) {
        int counter = -1;
        for (Int4 vec : arr) {
            counter++;
            if (counter % 5 == 0) {
                System.out.println();
                System.out.print(counter / 5 + ":\t");
            }
            System.out.print(vec + ",\t");
            if (vec == null) {
                break;
            }
        }
        System.out.println();
    }
}
