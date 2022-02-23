package com.ltcode.snake;


import com.ltcode.util.Direction;
import com.ltcode.util.Position;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeImpl implements Snake {

    private List<Position> body;
    @Getter
    private Direction startDirection;
    @Getter
    private Direction lastDirection;
    @Getter
    @Setter
    private Direction nextDirection;

    // queue of keys for next direction
    private Queue<Direction> nextDirections;

    public SnakeImpl(List<Position> body, Direction nextDirection) {
        this.body = new LinkedList<>(body);
        this.startDirection = nextDirection;
        this.lastDirection = nextDirection;
        this.nextDirection = nextDirection;
        this.nextDirections = new ArrayDeque<>();
    }

    @Override
    public void move() {
        body.remove(body.size() - 1);
        Position head = body.get(0);
        int x = head.getX();
        int y = head.getY();

        // calculate next Direction
        while (!nextDirections.isEmpty()) {
            var queueDirection = nextDirections.poll();
            if (queueDirection == lastDirection || queueDirection.isOpposing(lastDirection)) {
                continue;
            } else {
                nextDirection = queueDirection;
                break;
            }
        }

        switch (nextDirection) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        var newHeadPosition = new Position(x, y);
        body.add(0, newHeadPosition);

        this.lastDirection = this.nextDirection;
    }

    @Override
    public void increaseLength() {
        body.add(body.get(body.size() - 1));
    }

    @Override
    public Position getHeadPosition() {
        return this.body.get(0);
    }

    @Override
    public Position getBodyPartPosition(int bodyPart) {
        return this.body.get(bodyPart);
    }

    @Override
    public Direction getStartingDirection() {
        return startDirection;
    }

    @Override
    public int getLength() {
        return this.body.size();
    }

    @Override
    public void setNextDirection(Direction nextDirection) {
        this.nextDirections.offer(nextDirection);
    }


}
