package com.gamepub.princesstale.data.princess.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Princess {
    private int id;
    private UnitType unitType;
    private String name;
    private String nickname;
    private AttackType attackType;
    private UnitClass unitClass;
    private EquipmentType equipmentType;
    private Faction faction;
    private Grade initialGrade;
    private Grade maxGrade;
    private String story;
    private String description;
}
