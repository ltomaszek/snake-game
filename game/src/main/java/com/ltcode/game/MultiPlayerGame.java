package com.ltcode.game;

import com.ltcode.apple.Apple;
import com.ltcode.apple.AppleImpl;
import com.ltcode.player.Player;
import com.ltcode.player.PlayerInfo;
import com.ltcode.player.PlayersCreator;
import com.ltcode.player.impl.PlayerHuman;
import com.ltcode.util.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MultiPlayerGame extends GameImpl implements Game {

    private Player[] players;

    public MultiPlayerGame(PlayerInfo[] playersInfo, int snakeLength, int ROWS, int COLUMNS) {
        super(ROWS, COLUMNS);
        players = PlayersCreator.createPlayers(playersInfo, snakeLength, ROWS, COLUMNS, this);
        this.apple = this.createNewApple();
    }

    @Override
    public void restart() {
        Arrays.stream(players).forEach(Player::resetSnake);
        Arrays.stream(players).forEach(Player::resetScore);
        this.apple = this.createNewApple();
        this.isGameOver = false;
        this.isRunning = true;
    }

    @Override
    public int getNumberOfPlayers() {
        return players.length;
    }

    @Override
    public int getNumberOfHumanPlayers() {
        int numPlayers = 0;
        for (Player player : players) {
            if (player instanceof PlayerHuman) {
                numPlayers++;
            }
        }
        return numPlayers;
    }

    @Override
    public int getNumberOfComputerPlayers() {
        return players.length - getNumberOfHumanPlayers();
    }

    @Override
    public Player getPlayer() {
        return players[0];
    }

    @Override
    public Player getPlayer(int playerIndex) {
        return players[playerIndex];
    }

    @Override
    public int getNumberOfApples() {
        return 1;
    }

    @Override
    public Apple getApple() {
        return apple;
    }

    @Override
    public Apple getApple(int appleIndex) {
        return null;
    }

    // == PRIVATE METHODS ==

    @Override
    void updatePlayers() {
        for (Player player : players) {
            player.update();
        }
    }

    @Override
    void updateApple() {
        boolean appleEaten = false;
        for (Player player : players) {
            if (player.getSnake().getHeadPosition().getX() == apple.getPosition().getX()
                    && player.getSnake().getHeadPosition().getY() == apple.getPosition().getY()) {
                player.getSnake().increaseLength();
                player.increaseScore(1);
                appleEaten = true;
            }
        }

        if (appleEaten) {
            this.apple = createNewApple();
            for (Player player : players) {
                System.out.println(player.getName() + " : " + player.getScore());
            }
        }
    }

    private Apple createNewApple() {
        int finalX;
        int finalY;
        boolean isCollidingWithSnake = false;

        do {
            final int x = finalX = random.nextInt(COLUMNS);
            final int y = finalY = random.nextInt(ROWS);

            for (int i = 0; i < getNumberOfPlayers(); i++) {
                final int playerIdx = i;
                isCollidingWithSnake = IntStream.range(0, players[i].getSnake().getLength())
                        .mapToObj(bodyIdx -> players[playerIdx].getSnake().getBodyPartPosition(bodyIdx))
                        .anyMatch(position -> position.getX() == x && position.getY() == y);
                if (isCollidingWithSnake) {
                    break;
                }
            }

        } while (isCollidingWithSnake);


        return new AppleImpl(new Position(finalX, finalY));
    }

    @Override
    void checkCollision() {
        List<Player> collidingPlayers = new ArrayList<>();

        for (Player player : players) {
            if (collisionManager.isCollidingWithWall(player.getSnake())
            || collisionManager.isCollidingWithItself(player.getSnake())) {
                collidingPlayers.add(player);
                continue;
            }

            for (Player otherPlayer : players) {
                if (player != otherPlayer && collisionManager.isCollidingWithOtherSnake(player.getSnake(), otherPlayer.getSnake())) {
                    collidingPlayers.add(player);
                }
            }
        }

        for (Player player : collidingPlayers) {
            player.resetSnake();
        }
    }
}
