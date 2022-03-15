package com.ltcode;

import com.ltcode.control.KeyManager;
import com.ltcode.control.MultiPlayerKeyManager;
import com.ltcode.control.SinglePlayerKeyManager;
import com.ltcode.game.Game;
import com.ltcode.settings.PlayerSettings;
import com.ltcode.util.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;
    private final int UNIT_SIZE = 30;
    private final int DELAY = 75;

    private final Game game;
    private final KeyManager keyManager;

    private final Position[] scorePosition;

    Timer timer;
    Random random;

    GamePanel(Game game) {
        SCREEN_WIDTH = game.getColumns() * UNIT_SIZE;
        SCREEN_HEIGHT = game.getRows() * UNIT_SIZE;
        if (SCREEN_WIDTH % UNIT_SIZE != 0 || SCREEN_HEIGHT % UNIT_SIZE != 0) {
            throw new IllegalArgumentException("Screen size must be a multiple of UNIT_SIZE");
        }
        this.random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);

        if (game.getNumberOfHumanPlayers() == 1) {
            this.addKeyListener(new SinglePlayerKeyAdapter());
            this.keyManager = new SinglePlayerKeyManager();
        } else {
            this.addKeyListener(new MultiPlayerKeyAdapter());
            this.keyManager = new MultiPlayerKeyManager(game.getNumberOfHumanPlayers());
        }

        this.game = game;

        this.scorePosition = new Position[game.getNumberOfPlayers()];
        this.initScorePosition();

        this.startGame();
    }

    private void initScorePosition() {
        for (int i = 0; i < game.getNumberOfPlayers(); i++) {
            Position gamePosition = game.getPlayer(i).getStartPosition();
            Position windowPosition = new Position(gamePosition.getX() * UNIT_SIZE, gamePosition.getY() * UNIT_SIZE + UNIT_SIZE);
            scorePosition[i] = windowPosition;
        }
    }

    public void startGame() {
        this.restartGame();
    }

    public void restartGame() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //this.drawLines(g);
        this.drawApple(g);
        this.drawSnake(g);
        this.drawScore(g);
    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();

        if (!game.isGameOver()) {
            repaint();
        }
    }

    private void drawSnake(Graphics g) {
        for (int playerIdx = 0; playerIdx < game.getNumberOfPlayers(); playerIdx++) {
            g.setColor(Color.GREEN);
            for (int i = 1; i < game.getPlayer(playerIdx).getSnake().getLength(); i++) {
                g.fillRect(game.getPlayer(playerIdx).getSnake().getBodyPartPosition(i).getX() * UNIT_SIZE,
                        game.getPlayer(playerIdx).getSnake().getBodyPartPosition(i).getY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            }
            g.setColor(PlayerSettings.SNAKE_HEAD_COLOR[playerIdx]);
            g.fillRect(game.getPlayer(playerIdx).getSnake().getHeadPosition().getX() * UNIT_SIZE,
                    game.getPlayer(playerIdx).getSnake().getHeadPosition().getY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }
    }

    private void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(game.getApple().getPosition().getX() * UNIT_SIZE, game.getApple().getPosition().getY() * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
    }

    private void drawLines(Graphics g) {
        // horizontal lines
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            final int Y = i * UNIT_SIZE;
            g.drawLine(0, Y, SCREEN_WIDTH, Y);
        }

        // vertical lines
        for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i++) {
            final int X = i * UNIT_SIZE;
            g.drawLine(X, 0, X, SCREEN_HEIGHT);
        }
    }

    private void drawScore(Graphics g) {
        for (int i = 0; i < game.getNumberOfPlayers(); i++) {
            g.setColor(PlayerSettings.SNAKE_HEAD_COLOR[i]);
            g.setFont(new Font("TimesRoman", Font.BOLD, UNIT_SIZE));
            g.drawString("" + game.getPlayer(i).getScore(), scorePosition[i].getX(), scorePosition[i].getY());
        }
    }

    // == INNER CLASSES ==

    private class AbstractKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_P:
                    game.setPause(!game.isPause());
                    break;
                case KeyEvent.VK_ENTER:
                    if (!game.isRunning()) {
                        game.restart();
                    }
                    break;
            }
        }
    }

    private class SinglePlayerKeyAdapter extends AbstractKeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            var direction = keyManager.keyToDirection(e.getKeyCode());
            if (direction != null) {
                game.getPlayer().getSnake().setNextDirection(direction);
            } else {
                super.keyPressed(e);
            }
        }
    }

    private class MultiPlayerKeyAdapter extends AbstractKeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            var optionalPlayerIdx = keyManager.keyToPlayerIdx(e.getKeyCode());
            if (optionalPlayerIdx.isPresent()) {
                var direction = keyManager.keyToDirection(e.getKeyCode());
                game.getPlayer(optionalPlayerIdx.get()).getSnake().setNextDirection(direction);
            } else {
                super.keyPressed(e);
            }
        }
    }

}