package com.ltcode.snake;

import com.ltcode.util.Direction;
import com.ltcode.util.Position;

public interface Snake {

    void move();

    void increaseLength();

    Position getHeadPosition();

    Position getBodyPartPosition(int bodyPart);

    Direction getStartingDirection();

    Direction getNextDirection();

    Direction getLastDirection();

    int getLength();

    void setNextDirection(Direction nextDirection);
}
