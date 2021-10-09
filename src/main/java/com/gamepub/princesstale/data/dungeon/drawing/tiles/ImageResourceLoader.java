package com.gamepub.princesstale.data.dungeon.drawing.tiles;

import java.io.InputStream;

public class ImageResourceLoader {
    public static InputStream getDungeonTileResource(String resource) {
        return ImageResourceLoader.class.getResourceAsStream(resource);
    }
    public static InputStream getBaseDungeonTileResource() {
        return getDungeonTileResource("DungeonFloorTile.png");
    }
    public static InputStream get1Random() {
        return getDungeonTileResource("1_Random.png");
    }
}
