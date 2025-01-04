package unit.enemy.skeleton;

import unit.enemy.skeleton.actions.skeletonArcher.Aim;
import unit.enemy.skeleton.actions.skeletonArcher.Shoot;
import animation.Animation;
import ui.Images;
import unit.enemy.Enemy;

public class SkeletonArcher extends Enemy
{
    public SkeletonArcher()
    {
       super();

        name = "Skeleton Archer";

        value = 3;

        animation = new Animation(Images.skeletonArcher);

        addMaxHealth(30);

        algorithm.add(new Shoot());
        algorithm.add(new Aim());
        algorithm.add(new Shoot());

    }
}
