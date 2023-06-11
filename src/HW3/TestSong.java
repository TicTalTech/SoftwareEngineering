package HW3;

public class TestSong {
    public static void main(String args[]) {
        Song s = new Song("a", "b", Song.Genre.ROCK, 65);
        System.out.println(s);
    }
}
