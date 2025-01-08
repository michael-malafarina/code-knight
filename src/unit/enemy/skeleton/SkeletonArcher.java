package unit.enemy.skeleton;

import unit.enemy.skeleton.actions.skeletonArcher.Aim;
import unit.enemy.skeleton.actions.skeletonArcher.Shoot;
import animation.Animation;
import ui.Images;
import unit.enemy.Enemy;
import unit.hero.mage.actions.fire.Burn;
import unit.hero.mage.actions.fire.Emberstorm;

public class SkeletonArcher extends Enemy
{
    public SkeletonArcher()
    {
       super();

        name = "Skeleton Archer";

        value = 3;

        animation = new Animation(Images.skeletonArcher);

        addMaxHealth(30);



    }

    public void setAbilities()
    {
        addAction(new Shoot());
        addAction(new Aim());
    }
}
