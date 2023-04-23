import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


//    arr[y][x]
//    {
//        {},
//        {},
//        {},
//    }

public class Main {
    //public static Scanner scanner;
    public static Random rnd;
    public static Scanner scanner = new Scanner(System.in);

    public static final int EMPTY = 0;
    public static final int HIT = 1;
    public static final int SHIP = 2;
    public static final int MISS = 3;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;


    public static void battleshipGame() {

    }

    /**
     * the method sets up the board with all the ships
     */
    public static void setUpBoard() {
        System.out.println("Enter the board size");
        String size = scanner.nextLine();
        int index = size.indexOf('X');
        String row, col;
        row = col = "";
        for (int i = 0; i < index; ++i)
            row = row + size.charAt(i);
        for (int i = index + 1; i < size.length(); ++i)
            col = col + size.charAt(i);
        int numRows = Integer.parseInt(row);
        int numCols = Integer.parseInt(col);
        int max;
        if (numRows < numCols)
            max = numCols;
        else
            max = numRows;
        System.out.println("Enter the battleships sizes");
        int[] ships = Ships(numRows, numCols);
        int[][] board = new int[numRows][numCols];
        for (int i = 0; i < numRows; ++i)
            for (int j = 0; j < numCols; ++j)
                board[i][j] = EMPTY;
        int[][] board2 = new int[numRows][numCols];
        for (int i = 0; i < numRows; ++i)
            for (int j = 0; j < numCols; ++j)
                board2[i][j] = EMPTY;
        int[] ships2 = new int[max + 1];
        for (int i = 0; i < max + 1; ++i) {
            ships2[i] = ships[i];
        }
        shipPlacement(board, ships);
        computerBoard(ships2, board2);
    }

    /**
     * @param oreintation a number representing the oreintation
     * @param player      if player is true then were checking the players board otherwise were checking the computers board
     * @return returns true if the oreintation is a one or zero otherwise it returns false and prints a message
     */
    public static boolean checkOrientation(int oreintation, boolean player) {
        if (oreintation == VERTICAL || oreintation == HORIZONTAL)
            return true;
        if (player)
            System.out.println("Illegal orientation, try again!");
        return false;
    }

    /**
     * @param board  A 2d array representing the board
     * @param x      the row number of where the ship starts
     * @param y      the column number of where the ship starts
     * @param player if player is true then were checking the players board otherwise were checking the computers board
     * @return returns true if the tile is on the board otherwise it returns false and prints a message
     * if it's the players board
     */
    public static boolean checkTile(int[][] board, int x, int y, boolean player) {
        if (x < board.length && y < board[0].length)
            return true;
        if (player)
            System.out.println("Illegal tile, try again!");
        return false;
    }

    /**
     * @param board       A 2d array representing the board
     * @param x           the row number of where the ship starts
     * @param y           the column number of where the ship starts
     * @param size        the size of the ship
     * @param oreintation the oreintation of the ship
     * @param player      if player is true then were checking the players board otherwise were checking the computers board
     * @return returns true if the ship is within the boundaries of the board otherwise it returns false
     * and prints a message if it's the players board
     */
    public static boolean checkBoundaries(int[][] board, int x, int y, int size, int oreintation, boolean player) {
        if (oreintation == HORIZONTAL)
            if (y + size - 1 >= board[0].length) {
                if (player)
                    System.out.println("Battleship exceeds the boundaries of the board, try again!");
                return false;
            }
        if (oreintation == VERTICAL)
            if (x + size - 1 >= board.length) {
                if (player)
                    System.out.println("Battleship exceeds the boundaries of the board, try again!");
                return false;
            }
        return true;
    }

    /**
     * @param board       A 2d array representing the board
     * @param x           the row number of where the ship starts
     * @param y           the column number of where the ship starts
     * @param size        the size of the ship
     * @param oreintation the oreintation of the ship
     * @param player      if player is true then were checking the players board otherwise were checking the computers board
     * @return returns true if the ship is not overlapping another ship if it is then
     * it returns false and prints a message if it's the players board
     */
    public static boolean checkOverlap(int[][] board, int x, int y, int size, int oreintation, boolean player) {
        for (int i = 0; i < size; ++i) {
            if (oreintation == HORIZONTAL) {
                if (board[x][y + i] != EMPTY) {
                    if (player)
                        System.out.println("Battleship overlaps another battleship, try again!");
                    return false;
                }
            }
            if (oreintation == VERTICAL) {
                if (board[x + i][y] != EMPTY) {
                    if (player)
                        System.out.println("Battleship overlaps another battleship, try again!");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param board       A 2d array representing the board
     * @param x           the row number of where the ship starts
     * @param y           the column number of where the ship starts
     * @param size        the size of the ship
     * @param oreintation the oreintation of the ship
     * @param player      if player is true then were checking the players board otherwise were checking the computers board
     * @return returns true if there is no adjacent ship otherwise it returns false and prints a message
     * if it's the players board
     */
    public static boolean checkAdjacent(int[][] board, int x, int y, int size, int oreintation, boolean player) {
        boolean flag = true;
        for (int i = 0; i < size; ++i) {
            if (oreintation == HORIZONTAL) {
                if (x - 1 >= 0) {
                    if (board[x - 1][y + i] != EMPTY) {
                        flag = false;
                    }
                }
                if (x + 1 < board.length) {
                    if (board[x + 1][y + i] != EMPTY) {
                        flag = false;
                    }
                }
            }
            if (oreintation == VERTICAL) {
                if (y - 1 >= 0) {
                    if (board[x + i][y - 1] != EMPTY) {
                        flag = false;
                    }
                }
                if (y + 1 < board[0].length) {
                    if (board[x + i][y + 1] != EMPTY) {
                        flag = false;
                    }
                }
            }
        }
        //checks the 3 squares on both sides of the ship
        if (oreintation == HORIZONTAL) {
            if (x - 1 >= 0 && y - 1 >= 0) {
                if (board[x - 1][y - 1] != EMPTY)
                    flag = false;
            }
            if (y - 1 >= 0 && x + 1 < board.length) {
                if (board[x + 1][y - 1] != EMPTY)
                    flag = false;
            }
            if (y - 1 >= 0) {
                if (board[x][y - 1] != EMPTY)
                    flag = false;
            }
            if (y + size < board[0].length && x - 1 >= 0) {
                if (board[x - 1][y + size] != EMPTY)
                    flag = false;
            }
            if (y + size < board[0].length && x + 1 < board.length) {
                if (board[x + 1][y + size] != EMPTY)
                    flag = false;
            }
            if (y + size < board[0].length) {
                if (board[x][y + size] != EMPTY)
                    flag = false;
            }

        }
        if (oreintation == VERTICAL) {
            if (x - 1 >= 0 && y - 1 >= 0) {
                if (board[x - 1][y - 1] != EMPTY)
                    return false;
            }
            if (x - 1 >= 0 && y + 1 < board[0].length) {
                if (board[x - 1][y + 1] != EMPTY)
                    return false;
            }
            if (x - 1 >= 0) {
                if (board[x - 1][y] != EMPTY)
                    return false;
            }
            if (x + size < board.length && y - 1 >= 0) {
                if (board[x + size][y - 1] != EMPTY)
                    return false;
            }
            if (x + size < board.length && y + 1 < board[0].length) {
                if (board[x + size][y + 1] != EMPTY)
                    return false;
            }
            if (x + size < board.length) {
                if (board[x + size][y] != EMPTY)
                    return false;
            }
        }
        if (!flag) {
            if (player)
                System.out.println("Adjacent battleship detected, try again!");
            return false;
        }
        return true;
    }

    /**
     * @param board A 2d array representing the board
     * @param ships an array of ships where the value of ships[i] is the number of ships that are the size of i
     *              the method places the ships on the board
     */
    public static void shipPlacement(int[][] board, int[] ships) {
        int x, y, orientation;
        String x1, y1;
        x1 = y1 = "";
        x = y = orientation = -1;
        boolean bOrientation, bTile, bBoundary, bOverlap, bAdjacent;
        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
        System.out.println("Your current game board:");
        printBoard(board);
        for (int shipSize = 1; shipSize < ships.length; ++shipSize) {
            while (ships[shipSize] > 0) {
                System.out.println("Enter location and orientation for battleship of size " + shipSize);
                while (!bOrientation && !bTile && !bBoundary && !bOverlap && !bAdjacent) {
                    String s = scanner.nextLine();
                    int index = s.indexOf(',');
                    for (int i = 0; i < index; ++i)
                        x1 = x1 + s.charAt(i);
                    x = Integer.parseInt(x1);
                    s = s.replaceFirst(",", "f");
                    int index2 = s.indexOf(',');
                    for (int i = index + 2; i < index2; ++i)
                        y1 = y1 + s.charAt(i);
                    y = Integer.parseInt(y1);
                    orientation = s.charAt(index2 + 2) - '0';
                    x1 = y1 = "";
                    bOrientation = checkOrientation(orientation, true);
                    if (!bOrientation) {
                        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
                        continue;
                    }
                    bTile = checkTile(board, x, y, true);
                    if (!bTile) {
                        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
                        continue;
                    }
                    bBoundary = checkBoundaries(board, x, y, shipSize, orientation, true);
                    if (!bBoundary) {
                        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
                        continue;
                    }
                    bOverlap = checkOverlap(board, x, y, shipSize, orientation, true);
                    if (!bOverlap) {
                        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
                        continue;
                    }
                    bAdjacent = checkAdjacent(board, x, y, shipSize, orientation, true);
                }
                if (bAdjacent) {
                    for (int i = 0; i < shipSize; ++i) {
                        if (orientation == HORIZONTAL)
                            board[x][y + i] = SHIP;
                        if (orientation == VERTICAL)
                            board[x + i][y] = SHIP;
                    }
                    System.out.println("Your current game board:");
                    printBoard(board);
                    ships[shipSize]--;
                }
                bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;

            }

        }
    }

    /**
     * @param ships an array where the value of ships[i] is the number of ships with a length of i
     * @param board a 2d array representing the board
     *              the method creates a board for the computer
     */
    public static void computerBoard(int[] ships, int[][] board) {
        int x, y, orientation;
        boolean bOrientation, bTile, bBoundary, bOverlap, bAdjacent;
        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
        for (int shipSize = 1; shipSize < ships.length; ++shipSize) {
            while (ships[shipSize] > 0) {
                while (!bOrientation && !bTile && !bBoundary && !bOverlap && !bAdjacent) {
                    x = rnd.nextInt(board.length);
                    y = rnd.nextInt(board[0].length);
                    orientation = rnd.nextInt(2);
                    bOrientation = checkOrientation(orientation, false);
                    if (!bOrientation) {
                        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
                        continue;
                    }
                    bTile = checkTile(board, x, y, false);
                    if (!bTile) {
                        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
                        continue;
                    }
                    bBoundary = checkBoundaries(board, x, y, shipSize, orientation, false);
                    if (!bBoundary) {
                        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
                        continue;
                    }
                    bOverlap = checkOverlap(board, x, y, shipSize, orientation, false);
                    if (!bOverlap) {
                        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
                        continue;
                    }
                    bAdjacent = checkAdjacent(board, x, y, shipSize, orientation, false);
                    if (bAdjacent) {
                        for (int i = 0; i < shipSize; ++i) {
                            if (orientation == HORIZONTAL)
                                board[x][y + i] = SHIP;
                            if (orientation == VERTICAL)
                                board[x + i][y] = SHIP;
                        }
                        ships[shipSize]--;
                    }
                }
                bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
            }
        }
    }

    /**
     * @param row Number of rows of the board
     * @param col Number of columns of the board
     * @return returns an array where the value of arr[i] is the number of ships with a length of i
     */
    public static int[] Ships(int row, int col) {
        String ships = scanner.nextLine();
        int max;
        if (row < col)
            max = col;
        else
            max = row;
        int length = ships.length();
        int[] arr = new int[max + 1];
        int index = ships.indexOf('X');
        int size, num;
        int index2 = 0;
        String size1, num1;
        size1 = num1 = "";
        while (index != -1) {
            while (index2 != index) {
                num1 = num1 + ships.charAt(index2);
                index2++;
            }
            for (int i = index + 1; i < ships.length() && ships.charAt(i) != ' '; ++i)
                size1 = size1 + ships.charAt(i);
            num = Integer.parseInt(num1);
            size = Integer.parseInt(size1);
            arr[size] = num;
            ships = ships.replaceFirst("X", "f");
            index = ships.indexOf('X');
            index2 = index;
            if (index2 == -1)
                break;
            while (ships.charAt(index2) != ' ')
                index2--;
            index2++;
            size1 = "";
            num1 = "";
        }
        return arr;
    }

    /**
     * the method prints the board
     *
     * @param arr a 2d array representing the board
     */
    public static void printBoard(int arr[][]) {
        int rows = arr.length;
        rows = rows - 1;
        int digits = 0;
        int digits2 = 0;
        int index = 0;
        String s = "";
        while (rows > 0) {
            rows /= 10;
            digits++;
        }
        int digits3 = digits;
        while (digits3 > 0) {
            s = s + " ";
            digits3--;
        }
        s = s + " ";
        System.out.print(s);
        s = "";
        digits3 = digits;
        for (int i = 0; i < arr[0].length; ++i)
            System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < arr.length; ++i) {
            int index2 = i;
            if (index2 == 0)
                digits2++;
            while (index2 > 0) {
                index2 /= 10;
                digits2++;
            }

            while (digits3 > digits2) {
                s = s + " ";
                digits3--;
            }
            System.out.print(s + i + " ");
            s = "";
            digits3 = digits;
            digits2 = 0;
            for (int j = 0; j < arr[0].length; ++j) {
                if (arr[i][j] == EMPTY || arr[i][j] == MISS)
                    System.out.print("– ");
                if (arr[i][j] == HIT)
                    System.out.print("x ");
                if (arr[i][j] == SHIP)
                    System.out.print("# ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws IOException {
        String path = args[0];
        //scanner = new Scanner(new File(path));
        setUpBoard();
        int numberOfGames = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Total of " + numberOfGames + " games.");

        for (int i = 1; i <= numberOfGames; i++) {
            scanner.nextLine();
            int seed = scanner.nextInt();
            rnd = new Random(seed);
            scanner.nextLine();
            System.out.println("Game number " + i + " starts.");
            battleshipGame();
            System.out.println("Game number " + i + " is over.");
            System.out.println("------------------------------------------------------------");
        }
        System.out.println("All games are over.");
    }
}



