package HW3;

/**
 * An exception that gets thrown if we try to add a song to a playlist in which that song is already on the playlist
 */
public class SongAlreadyExistsException extends RuntimeException {
    public SongAlreadyExistsException() {
    }

    public SongAlreadyExistsException(String message) {
        super(message);
    }

    public SongAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
