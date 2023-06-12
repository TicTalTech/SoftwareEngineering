package HW3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Playlist implements Iterable<Song>, FilteredSongIterable, OrderedSongIterable {
    private List<Song> songList;
    private String filterArtist;
    private Song.Genre filterGenre;
    private int filterDuration;

    public Playlist() {
        songList = new ArrayList<Song>();
    }

    public void addSong(Song s) {
        if (songList.contains(s))
            throw new SongAlreadyExistsException();
        songList.add(s);
    }

    public boolean removeSong(Song s) {
        return songList.remove(s);
    }

    public Iterator<Song> iterator() {
        return new PlaylistIterator();
    }


    @Override
    public void filterArtist(String name) {
        if (name == null)
            filterArtist = null;
        else
            filterArtist = name;
    }

    @Override
    public void filterGenre(Song.Genre genre) {
        if (filterGenre == null)
            filterGenre = null;
        else
            filterGenre = genre;
    }

    @Override
    public void filterDuration(int num) {
        filterDuration = num;
    }

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

    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < songList.size() - 1; ++i)
            s += songList.get(i).toString() + ",";
        s += songList.get(songList.size() - 1).toString() + "]";
        return s;
    }

    public List<Song> getSongList() {
        return songList;
    }

    @Override
    public Object clone() {
        try {
            Playlist newPlaylist = new Playlist();
            newPlaylist.filterGenre(this.filterGenre);
            newPlaylist.filterArtist(this.filterArtist);
            newPlaylist.filterDuration(this.filterDuration);
            List<Song> Songs = newPlaylist.getSongList();
            for (int i = 0; i < songList.size(); ++i)
                newPlaylist.addSong((Song) Songs.get(i).clone());
            return newPlaylist;
        } catch (Exception e) {
            return null;
        }
    }


    private class PlaylistIterator implements Iterator<Song> {
        private int index;
        private boolean artist;
        private boolean genre;

        public PlaylistIterator() {
            if (filterArtist == null)
                artist = false;
            else
                artist = true;
            if (filterGenre == null)
                genre = false;
            else
                genre = true;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            int i = index;
            while (i < songList.size()) {
                if (artist && genre)
                    if (songList.get(i).getArtist() == filterArtist && songList.get(i).getGenre() == filterGenre &&
                            songList.get(i).getDuration() <= filterDuration)
                        return true;
                if (artist && !genre)
                    if (songList.get(i).getArtist() == filterArtist &&
                            songList.get(i).getDuration() <= filterDuration)
                        return true;
                if (!artist && genre)
                    if (songList.get(i).getGenre() == filterGenre &&
                            songList.get(i).getDuration() <= filterDuration)
                        return true;
                if (!artist && !genre)
                    if (songList.get(i).getDuration() <= filterDuration)
                        return true;
                i++;
            }
            return false;
        }

        @Override
        public Song next() {
            if (artist && genre) {
                while (songList.get(index).getArtist() != filterArtist &&
                        (songList.get(index).getGenre() != (filterGenre)) &&
                        songList.get(index).getDuration() > filterDuration)
                    index++;
            }
            if (artist && !genre) {
                while (songList.get(index).getArtist() != filterArtist
                        && songList.get(index).getDuration() > filterDuration)
                    index++;
            }
            if (genre && !artist) {
                while (songList.get(index).getGenre() != filterGenre
                        && songList.get(index).getDuration() > filterDuration)
                    index++;
            }
            if (!artist && !genre) {
                while (songList.get(index).getDuration() > filterDuration)
                    index++;
            }
            index++;
            return songList.get(index - 1);
        }
    }
}
