package HW0;

public class Test {
    public static final int E = 0; // EMPTY
    public static final int e = 0; // EMPTY

    public static final int H = 1; // HIT
    public static final int S = 2; // SHIP
    public static final int M = 3; // MISS
    public static int[] count1 = {0, 0};
    public static int[][] board1 = {
            {E, E, E, E, E},
            {E, E, E, E, E},
            {E, E, E, E, E},
            {E, E, E, E, E},
            {E, E, E, E, E}};
    public static int[] count2 = {0, 0, 0, 1, 1};

    public static int[][] board2 = {
            {S, S, S, e, e},
            {e, e, e, H, e},
            {e, H, e, S, e},
            {e, H, e, S, e},
            {e, H, e, S, e}};
    public static int[][] board3 = {
            {H, H, H, H, H},
            {E, E, E, E, E},
            {H, E, E, E, E},
            {H, E, H, H, H},
            {H, E, E, E, E}};

    public static void test_inputCoordinatedFromPlayer() {
        int[] arr = Main.inputCoordinatedFromPlayer();
        for (int val : arr) {
            System.out.println(val);
        }
    }

    public static void test_isShipDrowned() {
        System.out.println(Main.isShipDrowned(0, 0, board2) + " / False");
        System.out.println(Main.isShipDrowned(1, 0, board2) + " / False");
        System.out.println(Main.isShipDrowned(2, 0, board2) + " / False");
        System.out.println(Main.isShipDrowned(3, 1, board2) + " / False");
        System.out.println(Main.isShipDrowned(3, 2, board2) + " / False");
        System.out.println(Main.isShipDrowned(3, 3, board2) + " / False");
        System.out.println(Main.isShipDrowned(3, 4, board2) + " / False");
        System.out.println(Main.isShipDrowned(1, 2, board2) + " / True");
        System.out.println(Main.isShipDrowned(1, 3, board2) + " / True");
        System.out.println(Main.isShipDrowned(1, 4, board2) + " / True");
    }

    public static void test_calculateShipSize() {
        System.out.println(Main.calculateShipSize(0, 0, board3) + " / 5");
        System.out.println(Main.calculateShipSize(1, 0, board3) + " / 5");
        System.out.println(Main.calculateShipSize(2, 0, board3) + " / 5");
        System.out.println(Main.calculateShipSize(3, 0, board3) + " / 5");
        System.out.println(Main.calculateShipSize(4, 0, board3) + " / 5");

        System.out.println(Main.calculateShipSize(0, 2, board3) + " / 3");
        System.out.println(Main.calculateShipSize(0, 3, board3) + " / 3");
        System.out.println(Main.calculateShipSize(0, 4, board3) + " / 3");

        System.out.println(Main.calculateShipSize(2, 3, board3) + " / 3");
        System.out.println(Main.calculateShipSize(3, 3, board3) + " / 3");
        System.out.println(Main.calculateShipSize(4, 3, board3) + " / 3");
    }

    public static void test_printHitResults() {
//        Main.printHitResults(0, 0, board1, count1);
//        Main.printBoard(board1);
//        Main.printHitResults(0, 0, board2, count2);
//        Main.printBoard(board2);

    }

    public static void main(String[] args) {
//        test_inputCoordinatedFromPlayer();
//        test_isShipDrowned();
//        test_calculateShipSize();
        test_printHitResults();
    }
}
