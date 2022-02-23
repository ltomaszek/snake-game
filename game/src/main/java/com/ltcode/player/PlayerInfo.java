package com.ltcode.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class PlayerInfo {

    @Getter
    private String name;
    @Getter
    private PlayerType type;

}
