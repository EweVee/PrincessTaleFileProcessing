package com.gamepub.princesstale.data.princess.model;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Faction {
    FIRE(1),
    WIND(2),
    NATURE(3),
    WATER(4),
    LIGHT(5),
    DARK(6);

    private int id;

    Faction(int id) {
        this.id = id;
    }

    public static final Map<Integer, Faction> factionMap = Arrays
            .stream(Faction.values())
            .collect(Collectors.toMap(Faction::getId, Function.identity()));

    public Faction fromId(int id) {
        if (!factionMap.containsKey(id)) {
            throw new IllegalArgumentException("Cannot find Faction Id: " + id);
        }
        return factionMap.get(id);
    }

    private Integer getId() {
        return id;
    }
}
