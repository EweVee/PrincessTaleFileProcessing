package com.gamepub.princesstale.data.dungeon.parsing.map;

import com.gamepub.princesstale.data.CSVLoader;
import com.gamepub.princesstale.data.dungeon.parsing.map.data.DungeonMap202109;
import com.gamepub.princesstale.data.dungeon.parsing.map.model.DungeonMap;
import com.gamepub.princesstale.data.dungeon.parsing.tile.model.DungeonTile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DungeonMapLoader {
    public List<DungeonMap202109> dungeonMap;

    public DungeonMapLoader() {
        final String resource = "DungeonMap_202109.csv";
        this.dungeonMap = CSVLoader.loadCSV(DungeonMap202109.class, resource);
    }

    public Map<Integer, List<DungeonMap>> getDungeonMap(Map<Integer, DungeonTile> tileMap) {
        return dungeonMap.stream()
                .map(m -> fromDungeonMap202109(m, tileMap))
                .collect(Collectors.groupingBy(e -> e.getId(),Collectors.mapping(e -> e, Collectors.toList())));
    }

    private DungeonMap fromDungeonMap202109(DungeonMap202109 map, Map<Integer, DungeonTile> tileMap) {
        return DungeonMap.builder()
                .id(Integer.parseInt(map.getDungeonID()))
                .posX(Integer.parseInt(map.getPositionX()))
                .posY(Integer.parseInt(map.getPositionY()))
                .tile(tileMap.get(Integer.parseInt(map.getTileID())))
                .group(map.getGroup())
                .parameters(map.getParameters())
                .build();

    }
}
