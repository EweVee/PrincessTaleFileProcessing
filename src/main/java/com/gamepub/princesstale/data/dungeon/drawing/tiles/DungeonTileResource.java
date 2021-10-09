package com.gamepub.princesstale.data.dungeon.drawing.tiles;

import com.gamepub.princesstale.data.princess.model.Faction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DungeonTileResource {
    RANDOM(1, "1_Random.png"),
    NORMAL_A(2, "2_NormalA.png"),
    NORMAL_B(3, "3_NormalB.png"),
    BATTLE(4, "4_Battle.png"),
    BATTLE_BOSS(5, "5_BattleBoss.png"),
    REVIVAL(6, "6_Revival.png"),
    HEAL(7, "7_Heal.png"),
    BUFF_ATK(8, "8_BuffAtk.png"),
    BUFF_DEF(9, "9_BuffDefence.png"),
    DEBUFF_ATK(10, "10_DebuffAtk.png"),
    DEBUFF_DEF(11, "11_DebuffDefence.png"),
    RANDOM_REWARD(12, "12_RandomReward.png"),
    HAIL(13, "13_Hail.png"),
    ESCAPE(14, "14_Escape.png"),
    MOVE_UP(15, "15_MoveUp.png"),
    MOVE_DOWN(16, "16_MoveDown.png"),
    MOVE_LEFT(17, "17_MoveLeft.png"),
    MOVE_RIGHT(18, "18_MoveRight.png"),
    EXPLORE(19, "19_Explore.png"),
    TRAP(20, "20_Trap.png"),
    COIN_REWARD(21, "21_CoinReward.png"),
    CONDITION_REWARD(22, "22_ConditionReward.png"),
    WALL(23, "23_Wall.png"),
    START(24, "24_Start.png"), // This doesn't have an image, but I put Ladriel there.
    BLOCK_A(25, "25_BlockA.png"),
    BLOCK_B(26, "26_BlockB.png"),
    MATE(27, "27_Mate.png"), // Friend Unit
    BUFF_RESET(28, "28_BuffReset.png");

    private int id;
    private String localResource;
    private BufferedImage image;
    DungeonTileResource(int id, String localResource) {
        this.id = id;
        this.localResource = localResource;
        this.image = setDungeonTileResource(localResource);
    }

    public int getId() {
        return this.id;
    }

    public BufferedImage getBufferedImage() {
        return this.image;
    }

    public InputStream getDungeonTileResource() {
        return ImageResourceLoader.class.getResourceAsStream(this.localResource);
    }

    private static BufferedImage setDungeonTileResource(String localResource) {
        try {
            return ImageIO.read(DungeonTileResource.class.getResourceAsStream(localResource));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource. Build is broken.", e);
        }

    }

    public static final Map<Integer, DungeonTileResource> tileResourceMap = Arrays
            .stream(DungeonTileResource.values())
            .collect(Collectors.toMap(DungeonTileResource::getId, Function.identity()));
}
