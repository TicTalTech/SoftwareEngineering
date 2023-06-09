import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * a class that represents a database you can read and write from, together with ways to make it thread-safe
 */
public class Database {
    private Map<String, String> data;
    private Lock counterLock;
    private Condition reachedCapacity;

    private int counter;
    private final int MAX_NUM_OF_READERS;
    private HashMap<Long, AccessStatus> threadsStatus;

    /**
     * Creates a database object
     *
     * @param maxNumOfReaders the max number of threads that could read from the database at the same time
     */
    public Database(int maxNumOfReaders) {
        counter = 0;
        data = new HashMap<>();  // Note: You may add fields to the class and initialize them in here. Do not add parameters!
        MAX_NUM_OF_READERS = maxNumOfReaders;
        counterLock = new ReentrantLock();
        reachedCapacity = counterLock.newCondition();
        threadsStatus = new HashMap<>();
    }

    /**
     * The method adds or subtract from the counter and waits for access if necessary
     *
     * @param change       The value that will be added or subtracted from the total counter
     * @param accessStatus An enum that indicates if the thread wants to read or write to the database
     * @param shouldWait   True if the thread should wait for it to be granted access (counter small enough)
     *                     false if the thread should return false and not wait
     * @throws IllegalMonitorStateException if you are trying to release a read or write that does not exist
     * @return return true if the thread added or subtracted from the counter otherwise if shouldWait is false and it
     * didn't get access(the counter was too big) then it returns false
     */
    public boolean addToCounter(int change, AccessStatus accessStatus, boolean shouldWait) {
        try {
            counterLock.lock();
            // if the change is negative the condition will always be false and it will never await
            while (counter + change > MAX_NUM_OF_READERS) {
                if (shouldWait) {
                    reachedCapacity.await();
                } else {
                    return false;
                }
            }
            if (change > 0) {
                threadsStatus.put(Thread.currentThread().getId(), accessStatus);
            } else if (change < 0) {
                // if the key is in the dictionary and trying to do the correct command to release it as indicated
                // by the dictionary
                long threadKey = Thread.currentThread().getId();
                if (threadsStatus.containsKey(threadKey) &&
                        threadsStatus.get(threadKey) == accessStatus) {
                    threadsStatus.remove(threadKey);
                } else {
                    throw new IllegalMonitorStateException("Illegal " + accessStatus + " release attempt");
                }
            }
            counter += change;
            if (change < 0) {
                reachedCapacity.signalAll();
            }
        } catch (InterruptedException e) {
        } finally {
            counterLock.unlock();
        }
        return true;
    }

    /**
     * simulates writing to a database (a dictionary)
     *
     * @param key   the key to write to in the dictionary
     * @param value the value to be written
     */
    public void put(String key, String value) {
        data.put(key, value);
    }

    /**
     * simulates reading from a database (a dictionary)
     *
     * @param key the key to read from the dictionary
     * @return the value in the key given
     */
    public String get(String key) {
        return data.get(key);
    }

    /**
     * used before reading from the database, it makes sure that there are not too many
     * threads reading / writing at the moment
     *
     * @return true if the thread calling is able to read. false if not
     */
    public boolean readTryAcquire() {
        return addToCounter(1, AccessStatus.READING, false);
    }

    /**
     * used before reading from the database, it makes sure that there are not too many
     * threads reading / writing at the moment. waits until there is space / permission
     * for it to access the database
     */
    public void readAcquire() {
        addToCounter(1, AccessStatus.READING, true);
    }

    /**
     * called after reading from the database. signal the threads that are waiting to access the
     * database to check if they can now access it
     * @throws  IllegalMonitorStateException if your trying to release a read that does not exist
     */
    public void readRelease() {
        addToCounter(-1, AccessStatus.READING, true);
    }

    /**
     * used before writing from the database, it makes sure that there are not too many
     * threads reading / writing at the moment. waits until there is space / permission
     * for it to access the database
     */
    public void writeAcquire() {
        addToCounter(MAX_NUM_OF_READERS, AccessStatus.WRITING, true);
    }

    /**
     * used before writing to the database, it makes sure that there are not too many
     * threads reading / writing at the moment
     *
     * @return true if the thread calling is able to write. false if not
     */
    public boolean writeTryAcquire() {
        return addToCounter(MAX_NUM_OF_READERS, AccessStatus.WRITING, false);
    }

    /**
     * called after writing to the database. signal the threads that are waiting to access the
     * database to check if they can now access it
     * @throws IllegalMonitorStateException if your trying to release a read that does not exist
     */
    public void writeRelease() {
        addToCounter(-MAX_NUM_OF_READERS, AccessStatus.WRITING, true);
    }
}