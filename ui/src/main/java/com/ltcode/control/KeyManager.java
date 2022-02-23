package com.ltcode.control;

import com.ltcode.util.Direction;

import java.util.Optional;

public interface KeyManager {

    Optional<Integer> keyToPlayerIdx(int keyEvent);

    Direction keyToDirection(int keyEvent);
}
