package com.gamepub.princesstale.data.princess;

import com.gamepub.princesstale.data.CSVLoader;
import com.gamepub.princesstale.data.localization.model.Localization;
import com.gamepub.princesstale.data.princess.data.PrincessBean202109;
import com.gamepub.princesstale.data.princess.model.*;

import java.util.List;
import java.util.stream.Collectors;

// Remember to modify any header values in the csv that has values overlapping with java reserved names.
public class PrincessLoader {
    public List<PrincessBean202109> princesses;

    public PrincessLoader() {
        final String resource = "UnitBase_202109.csv";
        princesses = CSVLoader.loadCSV(PrincessBean202109.class, resource);
    }

    public List<Princess> getPrincesses(Localization localization) {
        return princesses.stream()
                .map(p -> map202109ToPrincess(p, localization))
                .collect(Collectors.toList());
    }

    private Princess map202109ToPrincess(PrincessBean202109 princess, Localization localization) {
        return Princess.builder()
                .id(Integer.parseInt(princess.getID()))
                .unitType(UnitType.valueOf(princess.getUnitType()))
                .name(localization.getLocalization(princess.getName()))
                .nickname(localization.getLocalization(princess.getNick()))
                .attackType(AttackType.valueOf(princess.getAttackType()))
                .unitClass(UnitClass.valueOf(princess.getClassName()))
                .equipmentType(EquipmentType.valueOf(princess.getEquipmentType()))
                .faction(Faction.factionMap.get(Integer.parseInt(princess.getFactionID())))
                .initialGrade(Grade.gradeMap.get(Integer.parseInt(princess.getINITGrade())))
                .maxGrade(Grade.gradeMap.get(Integer.parseInt(princess.getMaxGrade())))
                .story(localization.getLocalization(princess.getStory()))
                .description(localization.getLocalization(princess.getUnitDESC()))
                .build();
    }
}
