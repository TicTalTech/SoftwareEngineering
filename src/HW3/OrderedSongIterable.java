package HW3;

public interface OrderedSongIterable extends Iterable<Song> {
    public void setScanningOrder(ScanningOrder order);
}
