package com.ltcode.control;

import com.ltcode.settings.KeySettings;
import com.ltcode.util.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SinglePlayerKeyManager implements KeyManager {

    private final Map<Integer, Direction> keyToDirectionMap;

    public SinglePlayerKeyManager() {
        keyToDirectionMap = new HashMap<>(4);
        for (var entry : KeySettings.KEY_MAP.get(0).entrySet()) {
            var direction = entry.getKey();
            var keyEvent = entry.getValue();
            keyToDirectionMap.put(keyEvent, direction);
        }
    }

    @Override
    public Optional<Integer> keyToPlayerIdx(int keyEvent) {
        throw new UnsupportedOperationException("Method unsupported for Single Player mode");
    }

    @Override
    public Direction keyToDirection(int keyEvent) {
        return keyToDirectionMap.get(keyEvent);
    }
}
