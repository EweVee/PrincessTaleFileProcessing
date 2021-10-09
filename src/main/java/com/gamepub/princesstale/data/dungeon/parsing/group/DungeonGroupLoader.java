package com.gamepub.princesstale.data.dungeon.parsing.group;

import com.gamepub.princesstale.data.CSVLoader;
import com.gamepub.princesstale.data.dungeon.parsing.group.data.DungeonGroup202109;
import com.gamepub.princesstale.data.dungeon.parsing.group.model.DungeonGroup;

import java.util.List;

public class DungeonGroupLoader {
    private List<DungeonGroup202109> dungeonGroup;

    public DungeonGroupLoader() {
        final String resource = "DungeonGroup_202109.csv";
        this.dungeonGroup = CSVLoader.loadCSV(DungeonGroup202109.class, resource);
    }

    public List<DungeonGroup> getDungeonGroup() {
        throw new UnsupportedOperationException("TODO: Implement");
    }
}
