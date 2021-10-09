package com.gamepub.princesstale.data.dungeon.parsing.tile.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DungeonTile {
    private int id;
    private String tileType;
    private String tileValue;
}
