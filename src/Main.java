import java.io.File;
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
    public static Scanner scanner;
    public static Random rnd;

    public static final int EMPTY = 0;
    public static final int HIT = 1;
    public static final int SHIP = 2;
    public static final int MISS = 3;


    public static void battleshipGame() {

    }
public static void printBoard(int arr[][])
{
    System.out.print("  ");
    for(int i=0;i<arr[0].length;++i)
        System.out.print(i+" ");
    System.out.println();
        for(int i=0;i<arr.length;++i){
            System.out.print(i+" ");
            for(int j=0; j<arr[0].length;++j)
            {
                if(arr[i][j] == EMPTY || arr[i][j] == MISS)
                    System.out.print("- ");
                if(arr[i][j] == HIT)
                    System.out.print("x ");
                if(arr[i][j] == SHIP)
                    System.out.print("# ");
            }
            System.out.println();
        }

}    public static void main(String[] args) throws IOException {
        String path = args[0];
        Scanner scanner = new Scanner(System.in);
        //scanner = new Scanner(new File(path));
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



