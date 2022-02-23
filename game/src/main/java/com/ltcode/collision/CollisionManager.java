package com.ltcode.collision;

import com.ltcode.snake.Snake;

public class CollisionManager {

    private final int ROWS;
    private final int COLUMNS;

    public CollisionManager(int ROWS, int COLUMNS) {
        this.ROWS = ROWS;
        this.COLUMNS = COLUMNS;
    }

    public boolean isCollidingWithWall(Snake snake) {
        int headX = snake.getHeadPosition().getX();
        int headY = snake.getHeadPosition().getY();

        if (headX < 0
                || headX >= COLUMNS
                || headY < 0
                || headY >= ROWS) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the head of the snake is colliding with other part of the body
     * @param snake
     * @return
     */
    public boolean isCollidingWithItself(Snake snake) {
        var headX = snake.getHeadPosition().getX();
        var headY = snake.getHeadPosition().getY();

        for (int i = 1; i < snake.getLength(); i++) {
            if ((headX == snake.getBodyPartPosition(i).getX() && headY == snake.getBodyPartPosition(i).getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the head of the first snake is colliding with any part of the other snake
     * @param headSnake
     * @param otherSnake
     * @return
     */
    public boolean isCollidingWithOtherSnake(Snake headSnake, Snake otherSnake) {
        for (int i = 0; i < otherSnake.getLength(); i++) {
            if (headSnake.getHeadPosition().equals(otherSnake.getBodyPartPosition(i))) {
                return true;
            }
        }
        return false;
    }
}
