package HW3;

/**
 * An interface that allows you to decide how to iterate over a playlist
 */
public interface FilteredSongIterable extends Iterable<Song> {
    /**
     * A method that allows you to iterate in a playlist only over songs with a certain artist name
     *
     * @param name The artist name that we want to filter by
     */
    public void filterArtist(String name);

    /**
     * A method that allows you to iterate in a playlist only over songs with a certain genre
     *
     * @param genre The genere that we want to filter by
     */
    public void filterGenre(Song.Genre genre);

    /**
     * A method that allows you to iterate in a playlist only over songs with a duration less than or equal to a number
     *
     * @param num The maximum duration of the songs we want to iterate over
     */
    public void filterDuration(int num);
}
