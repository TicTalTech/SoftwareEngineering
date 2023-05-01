package HW0;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

//    arr[y][x]
//    {
//        {1, 2},
//        {3, 4},
//        {5, 6},
//    }

public class Main {
    public static Random rnd;
    public static Scanner scanner;

    public static final int EMPTY = 0;
    public static final int HIT = 1;
    public static final int SHIP = 2;
    public static final int MISS = 3;

    public static final int HIT_TEMP = 4;

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public static void print1dIntArray(int[] arr, String description) {
        System.out.print(description + ": ");
        for (int val : arr) {
            System.out.print(val + ", ");
        }
        System.out.println();
    }

    public static void battleshipGame() {
        int[][][] boards = setUpBoard();

        int[][] playerBoard = boards[0];
        int[][] agentBoard = boards[1];
        int[] playerShipsCount = boards[2][0];
        int[] agentShipsCount = boards[3][0];
//        print1dIntArray(playerShipsCount, "playerShipsCount");
//        print1dIntArray(agentShipsCount, "agentShipsCount");


        int turnNumber = 0;

        while (!isGameOver(playerShipsCount, agentShipsCount)) {
            if (turnNumber % 2 == 0) {
                playerTurn(agentBoard, agentShipsCount);
            } else {
                agentTurn(playerBoard, playerShipsCount);
            }
            turnNumber++;
        }
    }

    /**
     * the method sets up the board with all the ships
     */
    public static int[][][] setUpBoard() {
        System.out.println("Enter the board size");
        String size = scanner.nextLine();
        int index = size.indexOf('X');
        String row, col;
        row = col = "";
        for (int i = 0; i < index; i++) {
            row = row + size.charAt(i);
        }
        for (int i = index + 1; i < size.length(); i++) {
            col = col + size.charAt(i);
        }
        int numRows = Integer.parseInt(row);
        int numCols = Integer.parseInt(col);
        int max;
        if (numRows < numCols) {
            max = numCols;
        } else {
            max = numRows;
        }
        System.out.println("Enter the battleships sizes");
        int[] playerShips = shipsCount(numRows, numCols);
        int[][] playerBoard = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                playerBoard[i][j] = EMPTY;
            }
        }
        int[][] agentBoard = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                agentBoard[i][j] = EMPTY;
            }
        }
        int[] agentShips = new int[max + 1];
        for (int i = 0; i < max + 1; i++) {
            agentShips[i] = playerShips[i];
        }
        placePlayerShips(playerBoard, playerShips.clone());
        createComputerBoard(agentBoard, agentShips.clone());
        return new int[][][]{playerBoard, agentBoard, new int[][]{playerShips}, new int[][]{agentShips}};
    }

    /**
     * checks if the Oreintation that was inputted is valid
     *
     * @param orientation a number representing the orientation
     * @param player      if player is true then were checking the players board otherwise were checking the computers board
     * @return returns true if the orientation is a one or zero otherwise it returns false and prints a message if it's
     * checking the players board
     */
    public static boolean checkOrientation(int orientation, boolean player) {
        if (orientation == VERTICAL || orientation == HORIZONTAL) {
            return true;
        }
        if (player) {
            System.out.println("Illegal orientation, try again!");
        }
        return false;
    }

    /**
     * checks if the tile that was inputted is valid
     *
     * @param board  A 2d array representing the board
     * @param x      the row number of where the ship starts
     * @param y      the column number of where the ship starts
     * @param player if player is true then were checking the players board otherwise were checking the computers board
     * @return returns true if the tile is on the board otherwise it returns false and prints a message
     * if it's the players board
     */
    public static boolean checkTile(int[][] board, int x, int y, boolean player) {
        if (x < board.length && y < board[0].length && x >= 0 && y >= 0) {
            return true;
        }
        if (player) {
            System.out.println("Illegal tile, try again!");
        }
        return false;
    }

    /**
     * checks if the ship that was inputted goes outside the boards boundaries
     *
     * @param board       A 2d array representing the board
     * @param x           the row number of where the ship starts
     * @param y           the column number of where the ship starts
     * @param size        the size of the ship
     * @param orientation the orientation of the ship
     * @param player      if player is true then were checking the players board otherwise were checking the computers board
     * @return returns true if the ship is within the boundaries of the board otherwise it returns false
     * and prints a message if it's the players board
     */
    public static boolean checkBoundaries(int[][] board, int x, int y, int size, int orientation, boolean player) {
        if (orientation == HORIZONTAL) {
            if (y + size - 1 >= board[0].length) {
                if (player) {
                    System.out.println("Battleship exceeds the boundaries of the board, try again!");
                }
                return false;
            }
        }
        if (orientation == VERTICAL) {
            if (x + size - 1 >= board.length) {
                if (player)
                    System.out.println("Battleship exceeds the boundaries of the board, try again!");
                return false;
            }
        }
        return true;
    }

    /**
     * checks if the ship that was inputted overlaps with another ship
     *
     * @param board       A 2d array representing the board
     * @param x           the row number of where the ship starts
     * @param y           the column number of where the ship starts
     * @param size        the size of the ship
     * @param orientation the orientation of the ship
     * @param player      if player is true then were checking the players board otherwise were
     *                    checking the computers board
     * @return returns true if the ship is not overlapping another ship if it is then
     * it returns false and prints a message if it's the players board
     */
    public static boolean checkOverlap(int[][] board, int x, int y, int size, int orientation, boolean player) {
        for (int i = 0; i < size; i++) {
            if (orientation == HORIZONTAL) {
                if (board[x][y + i] != EMPTY) {
                    if (player)
                        System.out.println("Battleship overlaps another battleship, try again!");
                    return false;
                }
            }
            if (orientation == VERTICAL) {
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
     * checks if the ship that was inputted is adjacent to another ship
     *
     * @param board       A 2d array representing the board
     * @param x           the row number of where the ship starts
     * @param y           the column number of where the ship starts
     * @param size        the size of the ship
     * @param orientation the orientation of the ship
     * @param player      if player is true then were checking the players board otherwise were checking the computers board
     * @return returns true if there is no adjacent ship otherwise it returns false and prints a message
     * if it's the players board
     */
    public static boolean checkAdjacent(int[][] board, int x, int y, int size, int orientation, boolean player) {
        boolean flag = true;
        for (int i = 0; i < size; ++i) {
            if (orientation == HORIZONTAL) {
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
            if (orientation == VERTICAL) {
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
        if (orientation == HORIZONTAL) {
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
        if (orientation == VERTICAL) {
            if (x - 1 >= 0 && y - 1 >= 0) {
                if (board[x - 1][y - 1] != EMPTY)
                    flag = false;
            }
            if (x - 1 >= 0 && y + 1 < board[0].length) {
                if (board[x - 1][y + 1] != EMPTY)
                    flag = false;
            }
            if (x - 1 >= 0) {
                if (board[x - 1][y] != EMPTY)
                    flag = false;
            }
            if (x + size < board.length && y - 1 >= 0) {
                if (board[x + size][y - 1] != EMPTY)
                    flag = false;
            }
            if (x + size < board.length && y + 1 < board[0].length) {
                if (board[x + size][y + 1] != EMPTY)
                    flag = false;
            }
            if (x + size < board.length) {
                if (board[x + size][y] != EMPTY)
                    flag = false;
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
     * the method places the players ships on the board
     *
     * @param board A 2d array representing the board
     * @param ships an array of ships where the value of ships[i] is the number of ships that are the size of i
     *              <p>
     */
    public static void placePlayerShips(int[][] board, int[] ships) {
        int x, y, orientation;
        String x1, y1;
        x1 = y1 = "";
        x = y = orientation = -1;
        boolean bOrientation, bTile, bBoundary, bOverlap, bAdjacent;
        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
        System.out.println("Your current game board:");
        printBoard(board, 'X', '#', '_');
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
                    if (!bAdjacent) {
                        bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;
                    }
                }
                if (bAdjacent) {
                    for (int i = 0; i < shipSize; ++i) {
                        if (orientation == HORIZONTAL)
                            board[x][y + i] = SHIP;
                        if (orientation == VERTICAL)
                            board[x + i][y] = SHIP;
                    }

                    System.out.println("Your current game board:");
                    printBoard(board, 'X', '#', '_');
                    ships[shipSize]--;
                }
                bOrientation = bTile = bBoundary = bOverlap = bAdjacent = false;

            }

        }
    }

    /**
     * the method creates a board for the computer with the ships placed on it
     *
     * @param ships an array where the value of ships[i] is the number of ships with a length of i
     * @param board a 2d array representing the board
     *              <p>
     */
    public static void createComputerBoard(int[][] board, int[] ships) {
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
     * creates and array representing the number of ships of each length
     *
     * @param row Number of rows of the board
     * @param col Number of columns of the board
     * @return returns an array where the value of arr[i] is the number of ships with a length of i
     */
    public static int[] shipsCount(int row, int col) {
        String ships = scanner.nextLine();
        int max;
        if (row < col)
            max = col;
        else
            max = row;
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
    public static void printBoard(int[][] arr, char hit, char ship, char miss) {
        int rows = arr.length;
        rows = rows - 1;
        int digits = 0;
        int digits2 = 0;
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
        if (arr.length == 1)
            s = s + " ";
        s = s + " ";
        System.out.print(s);
        s = "";
        digits3 = digits;
        for (int i = 0; i < arr[0].length - 1; ++i)
            System.out.print(i + " ");
        System.out.print(arr[0].length - 1);
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
            for (int j = 0; j < arr[0].length - 1; ++j) {
                if (arr[i][j] == EMPTY)
                    System.out.print("– ");
                if (arr[i][j] == HIT)
                    System.out.print(hit + " ");
                if (arr[i][j] == SHIP)
                    System.out.print(ship + " ");
                if (arr[i][j] == MISS)
                    System.out.print(miss + " ");
            }
            if (arr[i][arr[0].length - 1] == EMPTY)
                System.out.print("–");
            if (arr[i][arr[0].length - 1] == HIT)
                System.out.print(hit);
            if (arr[i][arr[0].length - 1] == SHIP)
                System.out.print(ship);
            if (arr[i][arr[0].length - 1] == MISS)
                System.out.print(miss);


            System.out.println();
        }
        System.out.println();
    }

    /**
     * @param x     the x hit position
     * @param y     the y hit position
     * @param board the relevant board (player/agent)
     * @return true if the tile is inside the board
     */
    public static boolean isTileInsideBoard(int x, int y, int[][] board) {
        return x >= 0 && y >= 0 && y < board.length && x < board[0].length;
    }

    /**
     * @param x     the x hit position
     * @param y     the y hit position
     * @param board the relevant board (player/agent)
     * @return true if the tile is already hit
     */
    public static boolean isTileAlreadyHit(int x, int y, int[][] board) {
        return board[y][x] == Main.HIT || board[y][x] == Main.MISS;
    }

    /**
     * checks if the position is legal
     *
     * @param x     the x hit position
     * @param y     the y hit position
     * @param board the agent board
     * @return true if the position is legal
     */
    public static boolean isLegalHitCoordinates(int x, int y, int[][] board) {
        return isTileInsideBoard(x, y, board) && !isTileAlreadyHit(x, y, board);
    }

    /**
     * in charge of getting hit coordinates from user (player)
     *
     * @return the coordinates as a length 2 array
     */
    public static int[] inputCoordinatedFromPlayer() {
        String input = scanner.nextLine();
        String[] split = input.split(", ");
        return new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};
    }

    /**
     * (wrapper) recursively checks if there are un-drowned ship squares
     *
     * @param x     an x position that is part of the ship
     * @param y     a y position that is part of the ship
     * @param board the relevant board (player/agent)
     * @return if found ship
     */
    public static boolean isShipDrowned(int x, int y, int[][] board) {
        boolean value = isShipDrownedRecur(x, y, board);
        resetBoardHitConstant(board);
        return value;
    }

    /**
     * (inner) recursively checks if there are un-drowned ship squares
     *
     * @param x     an x position that is part of the ship
     * @param y     a y position that is part of the ship
     * @param board the relevant board (player/agent)
     * @return if found ship until now
     */
    public static boolean isShipDrownedRecur(int x, int y, int[][] board) {

        if (!isTileInsideBoard(x, y, board)) {
            return true;
        }
        if (board[y][x] == Main.SHIP) {
            return false;
        }

        if (board[y][x] == Main.EMPTY || board[y][x] == Main.MISS) {
            return true;
        }
        if (board[y][x] == Main.HIT_TEMP) {
            return true;
        }
        if (board[y][x] == Main.HIT) {
            board[y][x] = Main.HIT_TEMP;
        }

        return isShipDrownedRecur(x + 1, y, board) && isShipDrownedRecur(x - 1, y, board) &&
                isShipDrownedRecur(x, y + 1, board) && isShipDrownedRecur(x, y - 1, board);
    }

    /**
     * used by the recursive methods wrappers to reset the board to the start position
     *
     * @param board the relevant board (player/agent)
     */
    public static void resetBoardHitConstant(int[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x] == Main.HIT_TEMP) {
                    board[y][x] = Main.HIT;
                }
            }
        }
    }

    /**
     * (inner) recursively checks the size of the ship (the ship needs to be already sank)
     *
     * @param x     an x position that is part of the ship
     * @param y     a y position that is part of the ship
     * @param board the relevant board (player/agent)
     * @return the already found size
     */
    public static int calculateShipSizeRecur(int x, int y, int[][] board) {
        if (!isTileInsideBoard(x, y, board)) {
            return 0;
        }
        if (board[y][x] == Main.EMPTY || board[y][x] == Main.MISS) {
            return 0;
        }
        if (board[y][x] == Main.HIT_TEMP) {
            return 0;
        }

        if (board[y][x] == Main.HIT) {
            board[y][x] = Main.HIT_TEMP;
        }

        return 1 +
                calculateShipSizeRecur(x + 1, y, board) +
                calculateShipSizeRecur(x - 1, y, board) +
                calculateShipSizeRecur(x, y + 1, board) +
                calculateShipSizeRecur(x, y - 1, board);
    }

    /**
     * (wrapper) recursively checks the size of the ship (the ship needs to be already sank)
     *
     * @param x     an x position that is part of the ship
     * @param y     a y position that is part of the ship
     * @param board the relevant board (player/agent)
     * @return the size of the ship
     */
    public static int calculateShipSize(int x, int y, int[][] board) {
        int value = calculateShipSizeRecur(x, y, board);
        resetBoardHitConstant(board);
        return value;
    }

    /**
     * @param count arr of integers needed to be summed
     * @return the sum of the arr
     */
    public static int sum(int[] count) {
        int sum = 0;
        for (int val : count) {
            sum += val;
        }
        return sum;
    }

    /**
     * @param x          x position of the hit
     * @param y          y position of the hit
     * @param board      the relevant board (player/agent)
     * @param shipsCount the relevant ships count (player/agent)
     * @param isPlayer   true if refers to the player and false if refer to the agent
     */
    public static void printHitResults(int x, int y, int[][] board, int[] shipsCount, boolean isPlayer) {
        if (board[y][x] == Main.EMPTY) {
            System.out.println("That is a miss!");
            board[y][x] = Main.MISS;
        } else if (board[y][x] == Main.SHIP) {
            board[y][x] = Main.HIT;
            System.out.println("That is a hit!");

            if (isShipDrowned(x, y, board)) {
                int shipSize = calculateShipSize(x, y, board);
                shipsCount[shipSize]--;
                if (isPlayer) {
                    System.out.println("The computer's battleship has been drowned, "
                            + sum(shipsCount) + " more battleships to go!");
                } else {
                    System.out.println("Your battleship has been drowned, you have left "
                            + sum(shipsCount) + " more battleships!");
                }
            }
            if (isPlayer) {
                System.out.println("For making sure, computer game board");
                printBoard(board, 'X', '#', '–');
            }
        }
    }

    /**
     * in charge of executing the agent(computer) turn
     *
     * @param playerBoard      the board containing the player ships
     * @param playerShipsCount a count of how many ships the player has from each type
     */
    public static void agentTurn(int[][] playerBoard, int[] playerShipsCount) {
        int x, y;
        do {
            y = rnd.nextInt(playerBoard.length);
            x = rnd.nextInt(playerBoard[0].length);

        } while (!isLegalHitCoordinates(x, y, playerBoard));
        System.out.println("The computer attacked (" + y + ", " + x + ")");
        printHitResults(x, y, playerBoard, playerShipsCount, false);
        System.out.println("Your current game board:");
        printBoard(playerBoard, 'X', '#', '–');
    }

    /**
     * checks if the game has ended
     *
     * @param playerShipsCount a count of how many ships the player has from each type
     * @param agentShipsCount  a count of how many ships the computer has from each type
     * @return return true if the game is over
     */
    public static boolean isGameOver(int[] playerShipsCount, int[] agentShipsCount) {
        int numberOfPlayerShips = sum(playerShipsCount);
        int numberOfAgentShips = sum(agentShipsCount);
        if (numberOfPlayerShips == 0) {
            System.out.println("You lost ):");
            return true;
        } else if (numberOfAgentShips == 0) {

            System.out.println("You won the game!");
            return true;
        }
        return false;

    }

    /**
     * a method in charge of executing the player turn
     *
     * @param agentBoard      the board of the agent (computer)
     * @param agentShipsCount a count of how many ships the agents has from each type
     */
    public static void playerTurn(int[][] agentBoard, int[] agentShipsCount) {
        System.out.println("Your current guessing board:");
        Main.printBoard(agentBoard, 'V', '–', 'X');
        int x = -1, y = -1;
        boolean foundCoordsFlag = false;
        System.out.println("Enter a tile to attack");
        while (!foundCoordsFlag) {

            int[] coordinates = inputCoordinatedFromPlayer();
            y = coordinates[0];
            x = coordinates[1];
            if (!isTileInsideBoard(x, y, agentBoard)) {
                System.out.println("Illegal tile, try again!");
            } else if (isTileAlreadyHit(x, y, agentBoard)) {
                System.out.println("Tile already attacked, try again!");
            } else {
                foundCoordsFlag = true;
            }
        }
        printHitResults(x, y, agentBoard, agentShipsCount, true);
    }


    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));

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

