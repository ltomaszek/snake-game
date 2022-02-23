package com.ltcode.settings;

import com.ltcode.util.Direction;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeySettings {

    /**
     * Maps playerIndex (from 0) to Direction to KeyEvent (Integer)
     */
    public static Map<Integer, Map<Direction, Integer>> KEY_MAP = new HashMap<>();

    static {
        initKeys();
    }

    private static void initKeys() {
        int playerIndex = 0;
        var playerKeyMap = new HashMap<Direction, Integer>();
        playerKeyMap.put(Direction.UP, KeyEvent.VK_UP);
        playerKeyMap.put(Direction.DOWN, KeyEvent.VK_DOWN);
        playerKeyMap.put(Direction.LEFT, KeyEvent.VK_LEFT);
        playerKeyMap.put(Direction.RIGHT, KeyEvent.VK_RIGHT);
        KEY_MAP.put(playerIndex, playerKeyMap);

        playerIndex = 1;
        playerKeyMap = new HashMap<Direction, Integer>();
        playerKeyMap.put(Direction.UP, KeyEvent.VK_W);
        playerKeyMap.put(Direction.DOWN, KeyEvent.VK_S);
        playerKeyMap.put(Direction.LEFT, KeyEvent.VK_A);
        playerKeyMap.put(Direction.RIGHT, KeyEvent.VK_D);
        KEY_MAP.put(playerIndex, playerKeyMap);

        playerIndex = 2;
        playerKeyMap = new HashMap<Direction, Integer>();
        playerKeyMap.put(Direction.UP, KeyEvent.VK_Y);
        playerKeyMap.put(Direction.DOWN, KeyEvent.VK_H);
        playerKeyMap.put(Direction.LEFT, KeyEvent.VK_G);
        playerKeyMap.put(Direction.RIGHT, KeyEvent.VK_J);
        KEY_MAP.put(playerIndex, playerKeyMap);
    }
}
