package unit.enemy.skeleton;

import unit.hero.knight.actions.AttackKnight;
import unit.hero.knight.actions.paladin.HolyShield;
import animation.Animation;
import ui.Images;
import unit.enemy.Enemy;

public class SkeletonKnight extends Enemy
{
    public SkeletonKnight()
    {
        super();
        name = "Skeleton Knight";
        value = 4;

        animation = new Animation(Images.skeletonKnight);

        addMaxHealth(60);
//        addStartingBlock(10);

        algorithm.add(new HolyShield());
        algorithm.add(new AttackKnight());

    }
}
