package campaign.monsters.groups;

import campaign.Group;
import unit.enemy.Enemy;
import unit.enemy.skeleton.Skeleton;
import unit.enemy.skeleton.SkeletonArcher;
import unit.enemy.skeleton.SkeletonKnight;

import java.util.ArrayList;

public class SkeletonLegion extends Group
{
    public int getMinDifficulty()
    {
        return 7;
    }

    public int getMaxDifficulty()
    {
        return 14;
    }

    public void buildPool()
    {
        add(SkeletonArcher.class);
        add(SkeletonKnight.class);
    }

    public ArrayList<Enemy> buildEncounter(int difficulty)
    {
        return switch (difficulty)
        {
            case 7, 8 -> list(new SkeletonKnight(), new SkeletonArcher());
            case 9 -> list(new SkeletonKnight(), new SkeletonArcher(), new SkeletonArcher());
            case 10, 11 -> list(new SkeletonKnight(), new SkeletonKnight(), new SkeletonArcher());
            case 12 -> list(new SkeletonKnight(), new Skeleton(), new SkeletonArcher(), new SkeletonArcher());
            case 13 -> list(new SkeletonKnight(), new SkeletonArcher(), new SkeletonArcher(), new SkeletonArcher());
            case 14 -> list(new SkeletonKnight(), new SkeletonKnight(), new SkeletonArcher(), new SkeletonArcher());
            default -> null;
        };
    }

}
