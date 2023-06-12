package HW3;

public class TestPlaylist {
    public static void main(String args[]) {
        Playlist p = new Playlist();
        Song s = new Song("b", "b", Song.Genre.ROCK, 50);
        p.addSong(s);
        s = new Song("b", "a", Song.Genre.HOP_HIP, 50);
        p.addSong(s);
        p.filterDuration(50);
        p.filterArtist(null);
        p.filterGenre(null);

        p.setScanningOrder(ScanningOrder.DURATION);
        for (Song song : p)
            System.out.println(song);
        System.out.println(p);
    }
}
