package com.ltcode.game;

import com.ltcode.apple.Apple;
import com.ltcode.apple.AppleImpl;
import com.ltcode.player.Player;
import com.ltcode.player.PlayersCreator;
import com.ltcode.util.Position;
import lombok.Getter;

import java.util.stream.IntStream;

public class SinglePlayerGame extends GameImpl implements Game {

    @Getter
    private Player player;

    public SinglePlayerGame(String playerName, int snakeLength, int ROWS, int COLUMNS) {
        super(ROWS, COLUMNS);
        this.player = PlayersCreator.createPlayer(playerName, snakeLength);
        this.apple = this.createNewApple();
    }

    @Override
    public void restart() {
        this.player.resetSnake();
        this.player.resetScore();
        this.apple = this.createNewApple();
        this.isGameOver = false;
        this.isRunning = true;
    }

    @Override
    public int getNumberOfPlayers() {
        return 1;
    }

    @Override
    public int getNumberOfHumanPlayers() {
        return 1;
    }

    @Override
    public int getNumberOfComputerPlayers() {
        return 0;
    }

    @Override
    public Player getPlayer(int playerIndex) {
        return this.player;
    }

    @Override
    public int getNumberOfApples() {
        return 1;
    }

    @Override
    public Apple getApple(int appleIndex) {
        return apple;
    }

    @Override
    void updatePlayers() {
        player.update();
    }

    @Override
    void updateApple() {
        if (player.getSnake().getHeadPosition().getX() == apple.getPosition().getX()
                && player.getSnake().getHeadPosition().getY() == apple.getPosition().getY()) {
            this.apple = createNewApple();
            this.player.getSnake().increaseLength();
            this.player.increaseScore(1);
        }
    }

    @Override
    void checkCollision() {
        if (collisionManager.isCollidingWithWall(player.getSnake())
        || collisionManager.isCollidingWithItself(player.getSnake())) {
            isRunning = false;
            isGameOver = true;
        }
    }

    private Apple createNewApple() {
        int finalX;
        int finalY;
        boolean isCollidingWithSnake;

        do {
            final int x = finalX = random.nextInt(COLUMNS);
            final int y = finalY = random.nextInt(ROWS);

            isCollidingWithSnake = IntStream.range(0, player.getSnake().getLength())
                    .mapToObj(i -> player.getSnake().getBodyPartPosition(i))
                    .anyMatch(p -> p.getX() == x && p.getY() == y);
        } while (isCollidingWithSnake);

        return new AppleImpl(new Position(finalX, finalY));
    }
}