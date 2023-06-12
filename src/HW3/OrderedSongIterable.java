package HW3;

/**
 * An interface that allows you to set the Scanning order of a playlist
 */
public interface OrderedSongIterable extends Iterable<Song> {
    /**
     * A method that sets the order to scan a playlist
     *
     * @param order receives a scanning order
     */
    public void setScanningOrder(ScanningOrder order);
}
