package com.gamepub.princesstale.data.dungeon.parsing.tile;

import com.gamepub.princesstale.data.CSVLoader;
import com.gamepub.princesstale.data.dungeon.parsing.tile.data.DungeonTile202109;
import com.gamepub.princesstale.data.dungeon.parsing.tile.model.DungeonTile;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DungeonTileLoader {
    public List<DungeonTile202109> dungeonTile;

    public DungeonTileLoader() {
        final String resource = "DungeonTile_202109.csv";
        this.dungeonTile = CSVLoader.loadCSV(DungeonTile202109.class, resource);
    }

    public Map<Integer, DungeonTile> getDungeonTile() {
        return dungeonTile.stream()
                .map(this::from202109DungeonTile)
                .collect(Collectors.toMap(DungeonTile::getId, Function.identity()));
    }

    private DungeonTile from202109DungeonTile(DungeonTile202109 tile) {
        try {
            return DungeonTile.builder()
                    .id(Integer.parseInt(tile.getIndex()))
                    .tileType(tile.getTileType())
                    .tileValue(tile.getTileValue())
                    .build();
        } catch (Exception e) {
            System.out.println("Failed to convert tile: " + tile);
            throw e;
        }
    }
}
