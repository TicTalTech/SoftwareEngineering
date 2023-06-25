package HW4;

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
