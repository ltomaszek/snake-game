package com.ltcode.player;

import com.ltcode.snake.Snake;
import com.ltcode.util.Position;

public interface Player {

    int getScore();

    void increaseScore(int byNumber);

    void update();

    String getName();

    Snake getSnake();

    void resetSnake();

    void resetScore();

    Position getStartPosition();
}
