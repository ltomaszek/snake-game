package com.ltcode.player;

import com.ltcode.game.Game;
import com.ltcode.player.impl.PlayerComputer;
import com.ltcode.player.impl.PlayerHuman;
import com.ltcode.snake.Snake;
import com.ltcode.snake.SnakeImpl;
import com.ltcode.util.Direction;
import com.ltcode.util.Position;

import java.util.ArrayList;
import java.util.List;

public class PlayersCreator {

    public static Player createPlayer(final String playerName,
                                      final int SNAKE_LENGTH) {
        var snake = createSnakeForPlayer(0, SNAKE_LENGTH, -1, -1);
        return new PlayerHuman(playerName, snake);
    }

    /**
     * Creates players
     * 1st (player index = 0) player - top left corner
     * 2nd (player index = 1) player - bottom right corner
     * @param playersInfo
     * @param SNAKE_LENGTH
     * @param ROWS
     * @param COLUMNS
     * @return
     */
    public static Player[] createPlayers(final PlayerInfo[] playersInfo,
                                         final int SNAKE_LENGTH,
                                         final int ROWS,
                                         final int COLUMNS,
                                         final Game game) {
        Player[] players = new Player[playersInfo.length];
        for (int i = 0; i < players.length; i++) {
            var name = playersInfo[i].getName();
            var snake = createSnakeForPlayer(i, SNAKE_LENGTH, ROWS, COLUMNS);
            switch (playersInfo[i].getType()) {
                case HUMAN:
                    players[i] = new PlayerHuman(name, snake);
                    break;
                case COMPUTER:
                    players[i] = new PlayerComputer(name, snake, game);
                    break;
            }
        }
        return players;
    }

    private static Snake createSnakeForPlayer(final int playerIndex,
                                              final int SNAKE_LENGTH,
                                              final int ROWS,
                                              final int COLUMNS) {
        List<Position> body = new ArrayList<>(SNAKE_LENGTH);
        Direction startDirection;
        int xHead;
        int yHead;
        // difference between snake body parts starting from head
        int xDelta;

        switch (playerIndex) {
            case 0:
                xHead = 2;
                yHead = 0;
                xDelta = -1;
                startDirection = Direction.RIGHT;
                break;
            case 1:
                xHead = COLUMNS - 3;
                yHead = ROWS - 1;
                xDelta = 1;
                startDirection = Direction.LEFT;
                break;
            case 2:
                xHead = COLUMNS - 3;
                yHead = 0;
                xDelta = 1;
                startDirection = Direction.LEFT;
                break;
            case 3:
                xHead = 2;
                yHead = ROWS - 1;
                xDelta = -1;
                startDirection = Direction.RIGHT;
                break;
            default:
                throw new UnsupportedOperationException("PlayersCreator can create only up to 4 players");
        }

        int x = xHead;
        int y = yHead;
        for (int i = 0; i < SNAKE_LENGTH; i++) {
            body.add(new Position(x, y));
            x += xDelta;
        }

        return new SnakeImpl(body, startDirection);
    }
}
