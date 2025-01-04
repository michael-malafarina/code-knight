package campaign.monsters.groups;

import campaign.Group;
import unit.enemy.Enemy;
import unit.enemy.skeleton.Skeleton;
import unit.enemy.skeleton.SkeletonArcher;

import java.util.ArrayList;
import java.util.Arrays;

public class SkeletonsBasic extends Group
{
    public int getMinDifficulty()
    {
        return 2;
    }

    public int getMaxDifficulty()
    {
        return 8;
    }

    public void buildPool()
    {
        add(Skeleton.class);
        add(SkeletonArcher.class);
    }

    public ArrayList<Enemy> buildEncounter(int difficulty)
    {
        return switch (difficulty)
        {
            case 2, 3 -> list(new Skeleton());
            case 4, 5 -> list(new Skeleton(), new Skeleton());
            case 6, 7 -> list(new Skeleton(), new Skeleton(), new Skeleton());
            case 8 -> list(new Skeleton(), new Skeleton(), new Skeleton(), new Skeleton());
            default -> null;
        };
    }


}
