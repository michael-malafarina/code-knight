package campaign.monsters.groups;

import campaign.Group;
import unit.enemy.Enemy;
import unit.enemy.skeleton.Skeleton;
import unit.enemy.skeleton.SkeletonArcher;

import java.util.ArrayList;
import java.util.Arrays;

public class SkeletonCrew extends Group
{
    public int getMinDifficulty()
    {
        return 5;
    }

    public int getMaxDifficulty()
    {
        return 10;
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
            case 5, 6 -> list(new Skeleton(), new SkeletonArcher());
            case 7 -> list(new Skeleton(), new Skeleton(), new SkeletonArcher());
            case 8 -> list(new Skeleton(), new SkeletonArcher(), new SkeletonArcher());
            case 9 -> list(new Skeleton(), new Skeleton(), new Skeleton(), new SkeletonArcher());
            case 10 -> list(new Skeleton(), new Skeleton(), new SkeletonArcher(), new SkeletonArcher());
            default -> null;
        };
    }
}
