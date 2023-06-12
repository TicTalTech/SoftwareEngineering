package HW3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * A class that represents a playlist. A playlist is represented by an arraylist of songs.
 * filterArtist, filterGenre, filterDuration represent what we want to filter by when we iterate over the list
 */
public class Playlist implements Iterable<Song>, FilteredSongIterable, OrderedSongIterable {
    private List<Song> songList;
    private String filterArtist;
    private Song.Genre filterGenre;
    private int filterDuration;
    private int index = 0;

    public Playlist() {
        songList = new ArrayList<Song>();
        filterArtist = null;
        filterGenre = null;
        filterDuration = -1;
    }

    /**
     * A method that adds a song to the playlist
     *
     * @param s The song we want to add
     * @throws if the song already exists in the playlist the methods throws a SongAlreadyExistsException
     */
    public void addSong(Song s) {
        if (songList.contains(s))
            throw new SongAlreadyExistsException();
        s.setWhenWasAdded(index);
        index++;
        songList.add(s);
    }

    /**
     * A method that removes a song from a playlist
     *
     * @param s The song we want to remove
     * @return returns true if the song was removed else it returns false
     */
    public boolean removeSong(Song s) {
        return songList.remove(s);
    }

    /**
     * A method that returns an iterator for the list
     *
     * @return returns an iterator that iterates over the playlist
     */
    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }

    /**
     * A method that allows you to iterate in a playlist only over songs with a certain artist name. If the name
     * is null then there is no name restrictions to the iterations.
     *
     * @param name The artist name that we want to filter by
     */
    @Override
    public void filterArtist(String name) {
        if (name == null)
            filterArtist = null;
        else
            filterArtist = name;
    }

    /***
     * A method that allows you to iterate in a playlist only over songs with a certain genre. If the genre
     * is null then there is no genre restrictions to the iterations.
     * @param genre The genre that we want to filter by
     */
    @Override
    public void filterGenre(Song.Genre genre) {
        if (genre == null)
            filterGenre = null;
        else
            filterGenre = genre;
    }

    /**
     * A method that allows you to iterate in a playlist only over songs with a duration less than or equal to a number
     *
     * @param num The maximum duration of the songs we want to iterate over
     */
    @Override
    public void filterDuration(int num) {
        filterDuration = num;
    }

    /**
     * A method that receives a scanning order and sorts the list by that order, in order for the iterations to be in
     * the order that we want
     *
     * @param order receives a scanning order
     */
    @Override
    public void setScanningOrder(ScanningOrder order) {
        Comparator<Song> c;
        if (order == ScanningOrder.NAME) {
            c = Comparator.comparing(Song::getName).thenComparing(Song::getArtist);
            songList.sort(c);
        }
        if (order == ScanningOrder.DURATION) {
            c = Comparator.comparing(Song::getDuration).thenComparing(Song::getName).thenComparing(Song::getArtist);
            songList.sort(c);
        }
        if (order == ScanningOrder.ADDING) {
            c = Comparator.comparing(Song::getWhenWasAdded);
            songList.sort(c);
        }
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < songList.size() - 1; ++i)
            s += "(" + songList.get(i).toString() + "), ";
        s += "(" + songList.get(songList.size() - 1).toString() + ")]";
        return s;
    }

    public List<Song> getSongList() {
        return songList;
    }

    /**
     * A method that checks if two playlists are equal. Two playlists are equal if they contain the same songs
     *
     * @param obj The playlist that we want to check if its equal to the current playlist
     * @return returns true if the playlists are equal otherwise it returns false
     */
    @Override
    public boolean equals(Object obj) {
        Playlist list2;
        if (obj instanceof Playlist)
            list2 = (Playlist) obj;
        else
            return false;
        for (int i = 0; i < songList.size(); ++i) {
            if (!(list2.getSongList().contains(songList.get(i))))
                return false;
        }
        return true;
    }

    /**
     * A method that returns the playlists hashcode. A playlists hashcode is the sum of the hashcode of the songs
     * that are in the playlist
     *
     * @return returns the playlists hashcode
     */
    @Override
    public int hashCode() {
        int num = 0;
        for (int i = 0; i < songList.size(); ++i)
            num += songList.get(i).hashCode();
        return num;
    }

    /**
     * Clones the current playlist
     *
     * @return returns a deep copy of the playlist, if an exception is thrown then it returns null
     */
    @Override
    public Playlist clone() {
        try {
            Playlist newPlaylist = new Playlist();
            newPlaylist.filterGenre(this.filterGenre);
            newPlaylist.filterArtist(this.filterArtist);
            newPlaylist.filterDuration(this.filterDuration);
            List<Song> Songs = songList;
            for (int i = 0; i < songList.size(); ++i)
                newPlaylist.getSongList().add(songList.get(i).clone());
            return newPlaylist;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * An inner class that represents the iterator of the playlist. Artist,genre and duration are booleans
     * that are true if we have restrictions over what we can iterate over.
     */
    private class PlaylistIterator implements Iterator<Song> {
        private int index;
        private boolean artist;
        private boolean genre;
        private boolean duration;

        public PlaylistIterator() {
            if (filterArtist == null)
                artist = false;
            else
                artist = true;
            if (filterGenre == null)
                genre = false;
            else
                genre = true;
            if (filterDuration == -1)
                duration = false;
            else
                duration = true;
            index = 0;
        }

        /**
         * A method that checks if there is another song for the iterator to get to
         *
         * @return returns true if there is another song in the list that the iterator hasn't reached
         */
        @Override
        public boolean hasNext() {
            int i = index;
            while (i < songList.size()) {
                if (artist && genre && duration)
                    if (songList.get(i).getArtist() == filterArtist && songList.get(i).getGenre() == filterGenre &&
                            songList.get(i).getDuration() <= filterDuration)
                        return true;
                if (artist && !genre && duration)
                    if (songList.get(i).getArtist() == filterArtist &&
                            songList.get(i).getDuration() <= filterDuration)
                        return true;
                if (!artist && genre && duration)
                    if (songList.get(i).getGenre() == filterGenre &&
                            songList.get(i).getDuration() <= filterDuration)
                        return true;
                if (!artist && !genre && duration)
                    if (songList.get(i).getDuration() <= filterDuration)
                        return true;
                if (artist && genre && !duration)
                    if (songList.get(i).getArtist() == filterArtist && songList.get(i).getGenre() == filterGenre)
                        return true;
                if (artist && !genre && !duration)
                    if (songList.get(i).getArtist() == filterArtist)
                        return true;
                if (!artist && genre && !duration)
                    if (songList.get(i).getGenre() == filterGenre)
                        return true;
                if (!artist && !genre && !duration)
                    return true;
                i++;
            }
            return false;
        }

        /**
         * A method the returns the next song in the list, taking into account the restrictions we have
         *
         * @return returns the next song
         */
        @Override
        public Song next() {
            if (artist && genre && duration) {
                while (songList.get(index).getArtist() != filterArtist ||
                        (songList.get(index).getGenre() != (filterGenre)) ||
                        songList.get(index).getDuration() > filterDuration)
                    index++;
            }
            if (artist && !genre && duration) {
                while (songList.get(index).getArtist() != filterArtist
                        || songList.get(index).getDuration() > filterDuration)
                    index++;
            }
            if (genre && !artist && duration) {
                while (songList.get(index).getGenre() != filterGenre
                        || songList.get(index).getDuration() > filterDuration)
                    index++;
            }
            if (!artist && !genre && duration) {
                while (songList.get(index).getDuration() > filterDuration)
                    index++;
            }
            if (artist && genre && !duration) {
                while (songList.get(index).getArtist() != filterArtist ||
                        (songList.get(index).getGenre() != (filterGenre)))
                    index++;
            }
            if (artist && !genre && !duration) {
                while (songList.get(index).getArtist() != filterArtist)
                    index++;
            }
            if (genre && !artist && !duration) {
                while (songList.get(index).getGenre() != filterGenre)
                    index++;
            }
            index++;
            return songList.get(index - 1);
        }
    }
}
