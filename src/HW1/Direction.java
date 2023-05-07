package HW1;

public enum Direction {
    UP {
        public String toString() {
            return "up";
        }
    },
    DOWN {
        public String toString() {
            return "down";
        }
    },
    LEFT {
        public String toString() {
            return "left";
        }
    },
    RIGHT {
        public String toString() {
            return "right";
        }
    };

    public abstract String toString();
}
