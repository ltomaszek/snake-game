package com.ltcode.player.impl;

import com.ltcode.player.Player;
import com.ltcode.snake.Snake;
import com.ltcode.snake.SnakeImpl;
import com.ltcode.util.Position;
import lombok.Getter;

import java.util.ArrayList;

public class PlayerImpl implements Player {

    @Getter
    private String name;
    @Getter
    private int score;
    @Getter
    private Snake snake;
    private Snake startingSnake;

    public PlayerImpl(String name, Snake snake) {
        this.name = name;
        this.startingSnake = snake;
        this.resetSnake();
    }

    @Override
    public void increaseScore(int byNumber) {
        this.score += byNumber;
    }

    @Override
    public void update() {
        this.snake.move();
    }

    @Override
    public void resetSnake() {
        var bodyList = new ArrayList<Position>(startingSnake.getLength());
        for (int i = 0; i < startingSnake.getLength(); i++) {
            bodyList.add(startingSnake.getBodyPartPosition(i));
        }
        this.snake = new SnakeImpl(bodyList, startingSnake.getStartingDirection());
    }

    @Override
    public void resetScore() {
        score = 0;
    }

    @Override
    public Position getStartPosition() {
        return startingSnake.getHeadPosition();
    }


    @Override
    public String toString() {
        return "PlayerImpl{" +
                "name='" + name + '\'' +
                "type='" + (this instanceof PlayerHuman ? "HUMAN" : "COMPUTER") + '\'' +
                '}';
    }
}
