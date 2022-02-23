package com.ltcode.player.impl;

import com.ltcode.apple.Apple;
import com.ltcode.game.Game;
import com.ltcode.snake.Snake;
import com.ltcode.util.Direction;

import java.util.Random;

public class PlayerComputer extends PlayerImpl {

    private static Random random = new Random();
    private Game game;

    public PlayerComputer(String name, Snake snake) {
        this(name, snake, null);
    }

    public PlayerComputer(String name, Snake snake, Game game) {
        super(name, snake);
        this.game = game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void update() {
        Apple apple = game.getApple();
        Snake snake = getSnake();
        Direction nextDirection;

        int xComp = Integer.compare(snake.getHeadPosition().getX(), apple.getPosition().getX());
        if (xComp == 0) {
            int yComp = Integer.compare(snake.getHeadPosition().getY(), apple.getPosition().getY());
            nextDirection = yComp < 0
                    ? Direction.DOWN
                    : Direction.UP;
            if (nextDirection.isOpposing(snake.getLastDirection())) {
                nextDirection = random.nextBoolean()
                    ? Direction.LEFT
                    : Direction.RIGHT;
            }
        } else {
            nextDirection = xComp < 0
                    ? Direction.RIGHT
                    : Direction.LEFT;
            if (nextDirection.isOpposing(snake.getLastDirection())) {
                nextDirection = random.nextBoolean()
                        ? Direction.UP
                        : Direction.DOWN;
            }
        }
        snake.setNextDirection(nextDirection);
        super.update();
    }
}
