package com.gamepub.princesstale.data.dungeon.drawing;

import com.gamepub.princesstale.data.dungeon.drawing.tiles.DungeonTileResource;
import com.gamepub.princesstale.data.dungeon.parsing.map.model.DungeonMap;
import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Map;

public class ImageProcessor {
    private static final int FLOOR_TILE_X = 275;
    private static final int FLOOR_TILE_Y = 150;

    public static BufferedImage generateImage(
            int maxTilesX, int maxTilesY, final Map<Pair<Integer, Integer>, DungeonMap> cellMap) {
        final BufferedImage finalMapImage = generateMapImageBase(maxTilesX, maxTilesY);
        final Graphics2D finalMapGraphics = finalMapImage.createGraphics();

        for(int i = 0; i < maxTilesX; i++) {
            for(int j = maxTilesY-1; j >= 0 ; j--) {
                if (cellMap.containsKey(Pair.of(i, j))) {
                    drawFloorTile(
                            finalMapGraphics,
                            i, j,
                            FLOOR_TILE_X, FLOOR_TILE_Y,
                            maxTilesX, maxTilesY,
                            cellMap.get(Pair.of(i, j)));
                }
            }
        }
        return finalMapImage;
    }

    /** Diagonal drawing is a pain
     *   Example Data: Assume image is 200x100 and the grid is 3x3
     *
     *   1. (0,0) - x:0   y:200
     *   2. (0,1) - x:50  y:100
     *   3. (0,2) - x:100 y:0
     *   4. (1,0) - x:50  y:300
     *   5. (1,1) - x:100 y:200
     *   6. (1,2) - x:150 y:100
     *   7. (2,0) - x:100 y:400
     *   8. (2,1) - x:150 y:300
     *   9. (2,2) - x:200 y:200
     *
     *   x: (x+y)*(imgW/2) y:(imgH/2)*((maxY-x-1)+y)
     *   x: (x+y)*(100/2) y:(200/2)*((3-y-1)+x)
     */
    private static void drawFloorTile(
            final Graphics2D map,
            int tileX,
            int tileY,
            int tileWidthOffset,
            int tileHeightOffset,
            int mapMaxX,
            int mapMaxY,
            DungeonMap dungeonMap) {
        // +2 to offset from the edge of the image.
        int tilePosX = ((tileWidthOffset/2) * (tileX+tileY + 2));
        int tilePosY = ((tileHeightOffset/2) * ((mapMaxY-tileY-1) + tileX + 2)) - (2*tileY);
        drawTile(map, tilePosX, tilePosY, dungeonMap);

        addDebugInfoToCell(map, dungeonMap, tileWidthOffset, tileHeightOffset, tilePosX, tilePosY);
    }

    private static void addDebugInfoToCell(
            final Graphics2D map,
            final DungeonMap dungeonMap,
            int tileWidthOffset,
            int tileHeightOffset,
            int tilePosX,
            int tilePosY) {
        map.setColor(Color.red);
        map.setFont(new Font( "SansSerif", Font.BOLD, 12));
        map.drawString(
                String.format("%s,%s",dungeonMap.getPosX(),dungeonMap.getPosY()),
                tilePosX + (tileWidthOffset/2),
                tilePosY+(tileHeightOffset/2));
    }

    private static void drawTile(
            final Graphics2D map,
            int tilePosX,
            int tilePosY,
            DungeonMap dungeonMap
    ) {
        final DungeonTileResource tileResource = DungeonTileResource.tileResourceMap.get(dungeonMap.getTile().getId());

        if (Arrays.asList(DungeonTileResource.RANDOM, DungeonTileResource.NORMAL_A).contains(tileResource)) {
            // If it's 1 or 2, just put it down
            final BufferedImage tileImage = tileResource.getBufferedImage();
            map.drawImage(tileImage, tilePosX, tilePosY, tileImage.getWidth(), tileImage.getHeight(), null);
        } else if (DungeonTileResource.NORMAL_B == tileResource) {
            // The
            final BufferedImage tileImage = tileResource.getBufferedImage();
            map.drawImage(tileImage, tilePosX+25, tilePosY+10, tileImage.getWidth(), tileImage.getHeight(), null);
        } else {
            final BufferedImage baseFloorTile = DungeonTileResource.NORMAL_A.getBufferedImage();
            map.drawImage(baseFloorTile, tilePosX, tilePosY, baseFloorTile.getWidth(), baseFloorTile.getHeight(), null);

            int offsetX;
            int offsetY;
            switch(tileResource) {
                // Square tiles on top
                case BATTLE:
                case DEBUFF_ATK:
                case DEBUFF_DEF:
                case HAIL:
                case MOVE_UP:
                case MOVE_DOWN:
                case MOVE_LEFT:
                case MOVE_RIGHT:
                    offsetX = 29;
                    offsetY = 9;
                    break;
                // Circular tiles on top
                case HEAL:
                case BUFF_ATK:
                case BUFF_DEF:
                case RANDOM_REWARD:
                case MATE:
                case BUFF_RESET:
                    offsetX = 46;
                    offsetY = 18;
                    break;
                // Everything else
                case BATTLE_BOSS:
                    offsetX = 16;
                    offsetY = -80;
                    break;
                case REVIVAL:
                    offsetX = 45;
                    offsetY = -95;
                    break;
                case ESCAPE:
                    offsetX = 74;
                    offsetY = -67;
                    break;
                case EXPLORE:
                    offsetX = 48;
                    offsetY = -65;
                    break;
                case TRAP:
                    offsetY = 15;
                    offsetX = 47;
                    break;
                case COIN_REWARD:
                    offsetX = 45;
                    offsetY = -70;
                    break;
                case CONDITION_REWARD:
                    offsetX = 26;
                    offsetY = -85;
                    break;
                case WALL:
                    offsetX = 20;
                    offsetY = -115;
                    break;
                case START:
                    offsetX = 60;
                    offsetY = -80;
                    break;
                case BLOCK_A:
                    offsetX = 31;
                    offsetY = -30;
                    break;
                case BLOCK_B:
                    offsetX = 55;
                    offsetY = -20;
                    break;
                default:
                    throw new RuntimeException("Tile type not defined for drawing.");
            }
            final BufferedImage tileImage = tileResource.getBufferedImage();
            map.drawImage(tileImage, tilePosX+offsetX, tilePosY+offsetY, tileImage.getWidth(), tileImage.getHeight(), null);
        }
    }

    private static BufferedImage generateMapImageBase(int tileX, int tileY) {
        // Why +10? to cover up for a bug somewhere else I can't quite identify.
        final int width = (FLOOR_TILE_X/2 + 1) * (tileX+tileY+4);
        final int height = (FLOOR_TILE_Y/2 + 1) * (tileX+tileY+4);
        return new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    }
}
