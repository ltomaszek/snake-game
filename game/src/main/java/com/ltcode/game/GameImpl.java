package com.ltcode.game;

import com.ltcode.apple.Apple;
import com.ltcode.collision.CollisionManager;
import lombok.Getter;

import java.util.Random;

public abstract class GameImpl implements Game {

    protected final int ROWS;
    protected final int COLUMNS;
    protected Random random;
    @Getter
    protected boolean isRunning;
    @Getter
    protected boolean isGameOver;
    @Getter
    protected boolean isPause;
    protected CollisionManager collisionManager;
    @Getter
    protected Apple apple;

    public GameImpl(int ROWS, int COLUMNS) {
        this.ROWS = ROWS;
        this.COLUMNS = COLUMNS;
        this.random = new Random();
        this.collisionManager = new CollisionManager(ROWS, COLUMNS);
    }

    @Override
    public void start() {
        this.restart();
    }

    @Override
    public int getRows() {
        return ROWS;
    }

    @Override
    public int getColumns() {
        return COLUMNS;
    }

    @Override
    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }

    @Override
    public void update() {
        if (isRunning && !isPause && !isGameOver) {
            updatePlayers();
            checkCollision();
            updateApple();
        }
    }

    abstract void updatePlayers();

    abstract void checkCollision();

    abstract void updateApple();
}
