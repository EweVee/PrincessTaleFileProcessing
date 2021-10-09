package com.gamepub.princesstale.data.dungeon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamepub.princesstale.data.dungeon.parsing.map.model.DungeonMap;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

class DungeonLoaderTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void test() {
        final DungeonLoader loader = new DungeonLoader();

        loader.dungeonMap.entrySet().forEach(dun -> {
            int dungeonId = dun.getKey();
            List<DungeonMap> map = dun.getValue();
            int minX = map.stream().map(DungeonMap::getPosX).min(Integer::compare).get();
            int maxX = map.stream().map(DungeonMap::getPosX).max(Integer::compare).get();
            int minY = map.stream().map(DungeonMap::getPosY).min(Integer::compare).get();
            int maxY = map.stream().map(DungeonMap::getPosY).max(Integer::compare).get();
            String out = "Dungeon #%s has X: %s to %s and Y: %s to %s";
            System.out.println(String.format(out, dungeonId, minX, maxX, minY, maxY));
        });
    }
}