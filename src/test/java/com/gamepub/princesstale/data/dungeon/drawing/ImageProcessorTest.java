package com.gamepub.princesstale.data.dungeon.drawing;

import com.gamepub.princesstale.data.dungeon.DungeonLoader;
import com.gamepub.princesstale.data.dungeon.parsing.map.model.DungeonMap;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ImageProcessorTest {

    @Test
    public void test2() throws IOException {
        final DungeonLoader loader = new DungeonLoader();
        for(Map.Entry<Integer, List<DungeonMap>> dun : loader.dungeonMap.entrySet()) {
            int dungeonId = dun.getKey();
                List<DungeonMap> map = dun.getValue();
                int minX = map.stream().map(DungeonMap::getPosX).min(Integer::compare).get();
                int maxX = map.stream().map(DungeonMap::getPosX).max(Integer::compare).get();
                int minY = map.stream().map(DungeonMap::getPosY).min(Integer::compare).get();
                int maxY = map.stream().map(DungeonMap::getPosY).max(Integer::compare).get();
                int maxTilesX = maxX-minX+1;
                int maxTilesY = maxY-minY+1;
                String out = "Dungeon #%s has X: %s to %s (%s) and Y: %s to %s (%s)";
                System.out.println(String.format(out, dungeonId, minX, maxX, maxTilesX, minY, maxY, maxTilesY));

                // Generate Input Map
                final Map<Pair<Integer, Integer>, DungeonMap> cellMap = new HashMap<>();
                for(DungeonMap dMap : map) {
                    int offsetX = dMap.getPosX()-minX;
                    int offsetY = dMap.getPosY()-minY;
                    cellMap.putIfAbsent(Pair.of(offsetX, offsetY), dMap);
                }
                // Call Image Processor
                BufferedImage finalMapImage = ImageProcessor.generateImage(maxTilesX, maxTilesY, cellMap);

                // Output File
                File outputfile = new File(String.format("dungeon_map_%s.png", dungeonId));
                ImageIO.write(finalMapImage, "png", outputfile);
                System.out.println(outputfile.getAbsolutePath());

        }
    }
}