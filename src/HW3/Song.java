package HW3;

/**
 * A class that represents a song. Each song has a name,artist,genre,its duration in seconds and an index that
 * states when it was added to a playlist(0 if it's not part of a playlist)
 */
public class Song implements Cloneable {
    private final String name;
    private final String artist;
    public Genre genre;
    private int duration;
    private int whenWasAdded;

    /**
     * Constructor without the list index
     */
    public Song(String name, String artist, Genre genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.whenWasAdded = 0;
    }

    /**
     * Constructor with the list index
     */
    public Song(String name, String artist, Genre genre, int duration, int whenWasAdded) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
        this.whenWasAdded = whenWasAdded;
    }

    public void setWhenWasAdded(int whenWasAdded) {
        this.whenWasAdded = whenWasAdded;
    }

    @Override
    public String toString() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        if (seconds < 10)
            return "" + name + ", " + artist + ", " + genre + ", " + minutes + ":0" + seconds;
        return "" + name + ", " + artist + ", " + genre + ", " + minutes + ":" + seconds;
    }

    /**
     * Checks if the songs are equal. Songs are defined as equal if their name and artist name are the same
     *
     * @param obj The song that were checking if its equal to
     * @return returns true if the songs are equal else it returns false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Song) {
            Song s = (Song) obj;
            if (s.getName() == name && s.getArtist() == artist)
                return true;
        }
        return false;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getWhenWasAdded() {
        return whenWasAdded;
    }

    /**
     * Clones the song
     *
     * @return returns a deep copy of the current song. If an exception is thrown then it returns null
     */
    @Override
    public Song clone() {
        try {
            return new Song(this.name, this.artist, this.genre, this.duration, this.whenWasAdded);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * A hashcode method that assigns each song a hashcode based on its name and artist's hashcode
     *
     * @return returns the songs hashcode
     */
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

    /**
     * An enum that represents the possible genres of a song
     */
    public enum Genre {
        POP,
        ROCK,
        HIP_HOP,
        COUNTRY,
        JAZZ,
        DISCO
    }
}
