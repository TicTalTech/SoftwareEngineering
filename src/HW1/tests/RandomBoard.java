package HW1.tests;


import HW1.Action;
import HW1.Board;
import HW1.State;

import java.util.Random;

public class RandomBoard
{
    /**
     * create an already solved String that represents a board
     * example: "1 2 3|4 5 6|7 8 _"
     *
     * @param width  - the width (number of columns) of the boards will have
     * @param height - the height (number of rows) of the boards will have
     * @return - a string that represents a solved board
     */
    public static String createSolvedStringBoard(int width, int height) {
        String s = "";
        for (int i = 0; i < width * height - 1; i++) {
            s += (i + 1);
            if (i % width == width - 1) {
                s += "|";
            } else {
                s += " ";
            }
        }
        s += "_";
        return s;
    }

    public static State mixBoard(Board board, int numberOfMixes, Random random) {
        numberOfMixes += random.nextInt(10);
        State state = new State(board);
        for (int i = 0; i < numberOfMixes; i++) {
            Action[] actions = state.actions();
            if (actions.length == 0) {
                continue;
            }
            int rand = random.nextInt(actions.length);
            state = state.result(actions[rand]);
        }
        return state;
    }

    public static Board mixBoard(Board board, Random random) {
        int width = board.getTiles()[0].length;
        int height = board.getTiles().length;
        return mixBoard(board, width * height * width * height * 5, random).getBoard();
    }

    public static String boardToString(Board board) {
        String s = "";
        for (int i = 0; i < board.getTiles().length; i++) {
            for (int j = 0; j < board.getTiles()[0].length; j++) {
                if (board.getTiles()[i][j].getValue() == Board.EMPTY) {
                    s += "_";
                } else {
                    s += board.getTiles()[i][j].getValue();
                }
                if (j == board.getTiles()[0].length - 1 && i != board.getTiles().length - 1) {
                    s += "|";
                } else if (!(j == board.getTiles()[0].length - 1 && i == board.getTiles().length - 1)) {
                    s += " ";
                }
            }
        }
        return s;
    }

    public static String createRandomBoardString(int width, int height, Random random, boolean isSolvable) {
        String boardString = createSolvedStringBoard(width, height);
        Board board = new Board(boardString);
        if (!isSolvable) {
            board.switchTiles(0, 0, 1, 0);
        }
        Board mixedBoard = mixBoard(board, random);
        String mixedBoardString = boardToString(mixedBoard);
//        System.out.print(mixedBoardString + "\t");
//        System.out.print(mixedBoard.getTiles()[0].length + "X" + mixedBoard.getTiles().length + "\t");
//        System.out.print(mixedBoard.getTiles()[0].length * mixedBoard.getTiles().length + "\t");
//        System.out.println(Node.numberOfInversions(mixedBoard));
        return mixedBoardString;
    }

    public static String[] createBoardsSample(int minWidth, int minHeight, int maxWidth, int maxHeight, int repeats, int seed, boolean isSolvable) {
        Random random = new Random(seed);
        String[] sample = new String[(maxWidth - minWidth + 1) * (maxHeight - minHeight + 1) * repeats];
        int index = 0;
        for (int height = minHeight; height <= maxHeight; height++) {
            for (int width = minWidth; width <= maxWidth; width++) {
                for (int i = 0; i < repeats; i++) {
                    String mixedBoardString = createRandomBoardString(width, height, random, isSolvable);
//                    System.out.println("\"\"\"" + mixedBoardString + "\"\"\", ");
                    sample[index] = mixedBoardString;

                    index++;
                }
            }
        }
        return sample;
    }

    public static void main(String[] args) {
//        String[] small = createBoardsSample(2, 2, 4, 4, 10, 0);
//        String[] mid = createBoardsSample(3, 3, 6, 6, 3);
//        String[] big = createBoardsSample(5, 5, 10, 10, 3);
//        String[] huge = createBoardsSample(20, 20, 22, 2, 1);

        String[] boards = createBoardsSample(1, 1, 10, 10, 2, 0, true);
//        String[] boards = createBoardsSample(5, 6, 5, 6, 20, 0);


    }
}
