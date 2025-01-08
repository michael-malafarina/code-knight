package unit.enemy.skeleton;

import unit.hero.knight.actions.AttackKnight;
import unit.hero.knight.actions.paladin.HolyShield;
import animation.Animation;
import ui.Images;
import unit.enemy.Enemy;
import unit.hero.mage.actions.fire.Burn;
import unit.hero.mage.actions.fire.Emberstorm;

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



    }

    public void setAbilities()
    {
        addAction(new HolyShield());
        addAction(new AttackKnight());
    }
}
