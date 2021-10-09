package com.gamepub.princesstale.data.dungeon.parsing.map.model;

import com.gamepub.princesstale.data.dungeon.parsing.tile.model.DungeonTile;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DungeonMap {
    private int id;
    private int posX;
    private int posY;
    private DungeonTile tile;
    private String group;
    private String parameters;
}
