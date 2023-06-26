package HW4;

/**
 * Mark what kind of method is using the lock on the database
 */
public enum AccessStatus {
    READING {
        public String toString() {
            return "read";
        }
    },
    WRITING {
        public String toString() {
            return "write";
        }
    },
}
