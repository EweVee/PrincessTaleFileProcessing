package com.gamepub.princesstale.data.dungeon.parsing.group.data;

import lombok.Data;

@Data
public class DungeonGroup202109 {
    private String index,DungeonID,DungeonLevel,
            BattleStage,EnemyLvMin,EnemyLvMax,
            Enemy1Grade,Enemy2Grade,Enemy3Grade,Enemy4Grade,Enemy5Grade,
            MonsterAddHP,MonsterAddDMG,
            EquipGrade1Min,EquipGrade1Max,EquipGrade2Min,EquipGrade2Max,EquipGrade3Min,EquipGrade3Max,EquipGrade4Min,EquipGrade4Max,
            Element,
            KnightLimit,WarriorLimit,SupporterLimit,RangerLimit,MageLimit;
}
