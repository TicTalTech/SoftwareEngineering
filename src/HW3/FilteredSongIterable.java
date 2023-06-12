package HW3;

public interface FilteredSongIterable extends Iterable<Song> {
    public void filterArtist(String name);

    public void filterGenre(Song.Genre genre);

    public void filterDuration(int num);
}
