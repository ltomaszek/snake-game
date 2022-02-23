package com.ltcode.apple;

import com.ltcode.util.Position;
import lombok.Getter;

public class AppleImpl implements Apple {

    @Getter
    private final Position position;

    public AppleImpl(Position position) {
        this.position = position;
    }
}
