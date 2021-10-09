package com.gamepub.princesstale.data.dungeon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamepub.princesstale.data.dungeon.parsing.group.DungeonGroupLoader;
import com.gamepub.princesstale.data.dungeon.parsing.map.DungeonMapLoader;
import com.gamepub.princesstale.data.dungeon.parsing.map.model.DungeonMap;
import com.gamepub.princesstale.data.dungeon.parsing.tile.DungeonTileLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DungeonLoader {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public Map<Integer, List<DungeonMap>> dungeonMap;

    public DungeonLoader() {
        final DungeonTileLoader tileLoader = new DungeonTileLoader();
        final DungeonMapLoader mapLoader = new DungeonMapLoader();
        dungeonMap = mapLoader.getDungeonMap(tileLoader.getDungeonTile());
    }

    public void exportDungeonsAsJson() {
        dungeonMap.entrySet().forEach(entry -> {
            final int dungeon = entry.getKey();
            final List<DungeonMap> dungeonMaps = entry.getValue();
            final File output = new File("dungeon_map" + dungeon + ".json");

            try {
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(output, dungeonMaps);
                System.out.println("Written dungeon information to: " + output.getAbsolutePath());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
