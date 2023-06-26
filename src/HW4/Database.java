package HW4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Database {
    private Map<String, String> data;
    private Lock counterLock;
    private Condition reachedCapacity;

    private int counter = 0;
    private final int MAX_NUM_OF_READERS;
    private HashMap<Long, AccessStatus> threadsStatus;


    public Database(int maxNumOfReaders) {
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
     * @return return true if the thread added or subtracted from the counter otherwise if shouldWait is false and it
     * didn't get access(the counter was too big) then it returns false
     */
    public boolean addToCounter(int change, AccessStatus accessStatus, boolean shouldWait) {
        counterLock.lock();
        try {
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

    public void put(String key, String value) {
        data.put(key, value);
    }

    public String get(String key) {
        return data.get(key);
    }

    public boolean readTryAcquire() {
        return addToCounter(1, AccessStatus.READING, false);
    }

    public void readAcquire() {
        addToCounter(1, AccessStatus.READING, true);
    }

    public void readRelease() {
        addToCounter(-1, AccessStatus.READING, true);
    }

    public void writeAcquire() {
        // TODO: Add your code here...
    }

    public boolean writeTryAcquire() {
        // TODO: Add your code here...
    }

    public void writeRelease() {
        // TODO: Add your code here...
    }
}