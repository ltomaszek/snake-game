package com.ltcode.game;

import com.ltcode.apple.Apple;
import com.ltcode.player.Player;

public interface Game {

    void start();

    void restart();

    void update();

    void setPause(boolean isPause);

    boolean isPause();

    boolean isRunning();

    boolean isGameOver();

    int getNumberOfPlayers();

    int getNumberOfHumanPlayers();

    int getNumberOfComputerPlayers();

    Player getPlayer();

    Player getPlayer(int playerIndex);

    int getNumberOfApples();

    Apple getApple();

    Apple getApple(int appleIndex);

    int getRows();

    int getColumns();
}
