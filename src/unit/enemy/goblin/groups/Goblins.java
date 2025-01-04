package unit.enemy.goblin.groups;

import campaign.Group;
import unit.enemy.Enemy;
import unit.enemy.goblin.*;
import unit.enemy.skeleton.Skeleton;
import unit.enemy.skeleton.SkeletonArcher;

import java.util.ArrayList;

public class Goblins extends Group
{
    public int getMinDifficulty()
    {
        return 4;
    }

    public int getMaxDifficulty()
    {
        return 22;
    }

    public void buildPool()
    {
        add(unit.enemy.goblin.GoblinBasic.class);
    }

    public ArrayList<Enemy> buildEncounter(int difficulty)
    {
        return switch (difficulty)
        {
            case 4 -> list(new GoblinBasic(), new GoblinBasic());
            case 5 -> list(new GoblinBasic(), new GoblinArcher());
            case 6 -> list(new GoblinBasic(), new GoblinBasic(), new GoblinBasic());
            case 7 -> list(new GoblinBasic(), new GoblinBasic(), new GoblinBasic());
            case 8 -> list(new GoblinWarrior(), new GoblinBasic(), new GoblinArcher());
            case 9 -> list(new GoblinWarrior(), new GoblinWarrior(), new GoblinArcher());
            case 10 -> list(new GoblinWarrior(), new GoblinRogue(), new GoblinArcher());
            case 11 -> list(new GoblinWarrior(), new GoblinRogue(), new GoblinRogue());
            case 12 -> list(new GoblinWarrior(), new GoblinWarrior(), new GoblinArcher(), new GoblinArcher());
            case 13 -> list(new GoblinWarrior(), new GoblinRogue(), new GoblinShaman());
            case 14 -> list(new GoblinReaver(), new GoblinWarrior(), new GoblinArcher(), new GoblinArcher());
            case 15 -> list(new GoblinReaver(), new GoblinRogue(), new GoblinShaman());
            case 16 -> list(new GoblinReaver(), new GoblinShaman(), new GoblinShaman());
            case 17 -> list(new GoblinWarrior(), new GoblinReaver(), new GoblinRogue(), new GoblinRogue());
            case 18 -> list(new GoblinWarrior(), new GoblinReaver(), new GoblinArcher(), new GoblinShaman());
            case 19 -> list(new GoblinWarrior(), new GoblinReaver(), new GoblinShaman(), new GoblinShaman());
            case 20 -> list(new GoblinReaver(), new GoblinRogue(), new GoblinShaman(), new GoblinShaman());
            case 21 -> list(new GoblinReaver(), new GoblinRogue(), new GoblinShaman(), new GoblinShaman());
            case 22 -> list(new GoblinReaver(), new GoblinReaver(), new GoblinShaman(), new GoblinShaman());
            default -> null;
        };
    }
}
