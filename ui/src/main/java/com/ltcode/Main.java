package com.ltcode;


import com.ltcode.game.Game;
import com.ltcode.game.MultiPlayerGame;
import com.ltcode.player.PlayerInfo;
import com.ltcode.player.PlayerType;

public class Main {

    public static void main(String[] args) {
        int SNAKE_LENGTH = 3;
        PlayerInfo[] playersInfo = {
                new PlayerInfo("Julia", PlayerType.HUMAN),
                new PlayerInfo("Lukas", PlayerType.HUMAN)
                //new PlayerInfo("Comp 1", PlayerType.COMPUTER)
        };

        //Game game = new SinglePlayerGame("Muster Player", SNAKE_LENGTH, 20, 20);
        Game game = new MultiPlayerGame(
                playersInfo,
                SNAKE_LENGTH,
                25,
                30);
        new GameFrame(game);
        //new Frame();


    }


}
