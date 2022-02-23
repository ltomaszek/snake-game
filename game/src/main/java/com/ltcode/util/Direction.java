package com.ltcode.util;

public enum Direction {
    UP {
        public boolean isOpposing(Direction direction) {
            return direction == DOWN;
        }
    }, DOWN {
        public boolean isOpposing(Direction direction) {
            return direction == UP;
        }
    }, LEFT {
        public boolean isOpposing(Direction direction) {
            return direction == RIGHT;
        }
    }, RIGHT {
        public boolean isOpposing(Direction direction) {
            return direction == LEFT;
        }
    };

    public abstract boolean isOpposing(Direction direction);
}
