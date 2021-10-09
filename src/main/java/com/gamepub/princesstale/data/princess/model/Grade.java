package com.gamepub.princesstale.data.princess.model;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Grade {
    C(1),
    B(2),
    B_PLUS(3),
    A(4),
    A_PLUS(5),
    S(6),
    S_PLUS(7),
    SS(8),
    SS_PLUS(9),
    SSS(10),
    SSS_1STAR(11),
    SSS_2STAR(12),
    SSS_3STAR(13),
    SSS_4STAR(14),
    SSS_5STAR(15);

    private int id;

    Grade(int id) {
        this.id = id;
    }

    public static final Map<Integer, Grade> gradeMap = Arrays
            .stream(Grade.values())
            .collect(Collectors.toMap(Grade::getId, Function.identity()));

    public Grade fromId(int id) {
        if (!gradeMap.containsKey(id)) {
            throw new IllegalArgumentException("Cannot find Grade Id: " + id);
        }
        return gradeMap.get(id);
    }

    private Integer getId() {
        return id;
    }
}
