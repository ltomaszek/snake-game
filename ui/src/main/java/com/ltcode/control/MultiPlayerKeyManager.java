package com.ltcode.control;

import com.ltcode.settings.KeySettings;
import com.ltcode.util.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MultiPlayerKeyManager implements KeyManager {

    private final Map<Integer, Integer> keyToPlayerIdx;
    private final Map<Integer, Direction> keyToDirectionMap;

    public MultiPlayerKeyManager(int numOfPlayers) {
        keyToPlayerIdx = new HashMap<>(numOfPlayers * 4);
        for (int playerIdx = 0; playerIdx < numOfPlayers; playerIdx++) {
            var playerKeys = KeySettings.KEY_MAP.get(playerIdx);
            for (int playerKeyEvent : playerKeys.values()) {
                keyToPlayerIdx.put(playerKeyEvent, playerIdx);
            }
        }

        keyToDirectionMap = new HashMap<>(4);
        for (int playerIdx = 0; playerIdx < numOfPlayers; playerIdx++) {
            for (var entry : KeySettings.KEY_MAP.get(playerIdx).entrySet()) {
                var direction = entry.getKey();
                var keyEvent = entry.getValue();
                keyToDirectionMap.put(keyEvent, direction);
            }
        }
    }

    @Override
    public Optional<Integer> keyToPlayerIdx(int keyEvent) {
        return Optional.ofNullable(keyToPlayerIdx.get(keyEvent));
    }

    @Override
    public Direction keyToDirection(int keyEvent) {
        return keyToDirectionMap.get(keyEvent);
    }
}
