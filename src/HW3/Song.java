package HW3;

public class Song implements Cloneable {
    private final String name;
    private final String artist;
    public Genre genre;
    private int duration;

    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    @Override
    public String toString() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        if (seconds < 10)
            return "(" + name + " ," + artist + " ," + genre + " ," + minutes + ":0" + seconds + ")";
        return "(" + name + " ," + artist + " ," + genre + " ," + minutes + ":" + seconds + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Song) {
            Song s = (Song) obj;
            if (s.getName() == name && s.getArtist() == artist)
                return true;
        }
        return false;
    }

    @Override
    public Object clone() {
        try {
            return new Song(this.name, this.artist, this.genre, this.duration);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode() + artist.hashCode();
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public enum Genre {
        POP,
        ROCK,
        HOP_HIP,
        COUNTRY,
        JAZZ,
        DISCO
    }
}
